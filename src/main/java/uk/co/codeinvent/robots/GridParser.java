package uk.co.codeinvent.robots;

import java.util.Optional;
import java.util.function.Consumer;

import static java.lang.Integer.min;
import static java.util.Objects.requireNonNull;

class GridParser implements Parser<Grid> {

    private final int maxCoOrd = 50;
    private final Consumer<Grid> gridConsumer;

    GridParser(Consumer<Grid> gridConsumer) {
        this.gridConsumer = requireNonNull(gridConsumer, "grid consumer expected");
    }

    @Override
    public void parse(String line) {
        String[] coOrds = Optional.ofNullable(line)
                .map(String::trim)
                .map(s -> s.split("\\s+"))
                .orElse(new String[]{});
        if (coOrds.length != 2) {
            throw new IllegalArgumentException("unknown grid co-ordinates " + line);
        }
        Grid grid = new RectangularGrid(
                min(Integer.parseInt(coOrds[0]), maxCoOrd),
                min(Integer.parseInt(coOrds[1]), maxCoOrd)
        );
        gridConsumer.accept(grid);
    }
}
