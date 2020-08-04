package uk.co.codeinvent.robots;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

class InstructionParser implements Parser<List<Instruction>> {

    private final long maxInstructions = 100;
    private final Consumer<List<Instruction>> instructionConsumer;

    public InstructionParser(Consumer<List<Instruction>> instructionConsumer) {
        this.instructionConsumer = requireNonNull(instructionConsumer, "instruction consumer expected");
    }

    @Override
    public void parse(String line) {
        List<Instruction> instructionList = Optional.ofNullable(line)
                .map(String::trim)
                .stream()
                .flatMapToInt(String::chars)
                .limit(maxInstructions)
                .mapToObj(Character::toString)
                .map(MoveInstruction::new)
                .collect(toList());
        instructionConsumer.accept(instructionList);
    }
}
