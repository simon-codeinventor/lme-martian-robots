package uk.co.codeinvent.robots;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static uk.co.codeinvent.robots.Position.Orientation.DISORIENTED;

class RectangularGrid implements Grid {

    private final int xMax;
    private final int yMax;
    private final List<List<Square>> grid;

    RectangularGrid(int xMax, int yMax) {
        this.xMax = requireValidDimension(xMax, "xMax");
        this.yMax = requireValidDimension(yMax, "yMax");
        grid = new ArrayList<>(xMax);
        for (int x = 0; x <= xMax; x++) {
            List<Square> yList = new ArrayList<>();
            grid.add(yList);
            for (int y = 0; y <= yMax; y++) {
                yList.add(new SimpleSquare(new SimplePosition(x, y, DISORIENTED)));
            }
        }
    }

    @Override
    public int getxMax() {
        return xMax;
    }

    @Override
    public int getyMax() {
        return yMax;
    }

    @Override
    public boolean isOffGrid(Position position) {
        return position.getX() < 0
                || position.getX() > getxMax()
                || position.getY() < 0
                || position.getY() > getyMax();
    }

    @Override
    public Action effect(Action action, Position currentPosition) {
        return squareAt(action, currentPosition)
                .map(sq -> sq.effect(action))
                .orElse(action);
    }

    @Override
    public void addIllegal(Action action, Position currentPosition) {
        squareAt(action, currentPosition)
                .ifPresent(sq -> sq.addIllegal(action));
    }

    private Optional<Square> squareAt(Action action, Position currentPosition) {
        requireNonNull(currentPosition, "position expected");
        requireNonNull(action, "action expected");
        final int x = currentPosition.getX();
        if (x >= 0 && x <= getxMax()) {
            List<Square> yDimension = grid.get(x);
            final int y = currentPosition.getY();
            if (y >= 0 && y <= getyMax()) {
                return Optional.of(yDimension.get(y));
            }
        }
        return Optional.empty();
    }

    private int requireValidDimension(int dimension, String name) {
        if (dimension < 0) {
            throw new IllegalArgumentException(name + " must be greater than 0");
        }
        if (dimension > 50) {
            throw new IllegalArgumentException(name + " must be less than 50");
        }
        return dimension;
    }
}
