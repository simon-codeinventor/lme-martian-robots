package uk.co.codeinvent.robots;

import static java.util.Objects.requireNonNull;

class SimplePosition implements Position {

    private final int x;
    private final int y;
    private final Orientation orientation;

    SimplePosition(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = requireNonNull(orientation, "orientation expected");
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Position resolve(Action action) {
        return new SimplePosition(
                x + action.getDeltaX(),
                y + action.getDeltaY(),
                action.getNextOrientation());
    }

    @Override
    public String toString() {
        return "SimplePosition{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }
}
