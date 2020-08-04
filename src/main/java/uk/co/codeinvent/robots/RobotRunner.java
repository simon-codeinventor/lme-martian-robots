package uk.co.codeinvent.robots;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class RobotRunner {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: provide input file");
            return;
        }

        RobotRunner robotRunner = new RobotRunner();

        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            lines.map(String::trim)
                    .map(robotRunner::process)
                    .flatMap(Optional::stream)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final List<Parser<?>> parsers = Arrays.asList(
            new GridParser(this::setGrid),
            new PositionParser(this::setPosition),
            new InstructionParser(this::setInstructions)
    );
    private final ActionResolver actionResolver = new SimpleActionResolver();
    private int currentParserIndex = 0;
    private Grid grid = null;
    private Position currentPosition = null;
    private List<Instruction> currentInstructions = emptyList();

    private Optional<Robot> process(String line) {
        // reset if line is empty
        if (line.isEmpty()) {
            currentPosition = null;
            currentInstructions = emptyList();
            return Optional.empty();
        }
        // parse input
        nextParser().parse(line);
        // ensure sufficient input to act
        if (nonNull(currentPosition)
                && nonNull(currentInstructions)
                && !currentInstructions.isEmpty()) {

            // make a robot and ask it to follow instructions
            Robot robot = new SimpleRobot(grid, actionResolver, currentPosition);
            robot.follow(currentInstructions);
            return Optional.of(robot);
        }
        return Optional.empty();
    }

    private Parser<?> nextParser() {
        final int index = currentParserIndex;
        switch (index) {
            case 0:
            case 2:
                currentParserIndex = 1; // position parser
                return parsers.get(index);
            case 1:
                currentParserIndex = 2; // instruction parser
                return parsers.get(index);
            default:
                throw new IllegalStateException("unexpected parser index " + currentParserIndex);
        }
    }

    void setGrid(Grid grid) {
        this.grid = requireNonNull(grid, "grid expeced");
    }

    void setPosition(Position position) {
        this.currentPosition = requireNonNull(position, "position expected");
    }

    void setInstructions(List<Instruction> instructions) {
        this.currentInstructions = requireNonNull(instructions, "instructions expected");
    }
}
