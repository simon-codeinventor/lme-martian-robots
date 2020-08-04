package uk.co.codeinvent.robots;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

class PositionParser implements Parser<Position> {

    private final Consumer<Position> positionConsumer;

    PositionParser(Consumer<Position> positionConsumer) {
        this.positionConsumer = requireNonNull(positionConsumer, "position consumer expected");
    }

    @Override
    public void parse(String line) {
        String[] coOrds = Optional.ofNullable(line)
                .map(String::trim)
                .map(s -> s.split("\\s+"))
                .orElse(new String[]{});
        if (coOrds.length != 3) {
            throw new IllegalArgumentException("unknown co-ordinates " + line);
        }
        Position position = new SimplePosition(
                Integer.parseInt(coOrds[0]),
                Integer.parseInt(coOrds[1]),
                Position.Orientation.valueOf(coOrds[2])
        );
        positionConsumer.accept(position);
    }
}
