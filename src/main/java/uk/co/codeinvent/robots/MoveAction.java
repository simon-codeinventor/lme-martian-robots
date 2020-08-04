package uk.co.codeinvent.robots;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

class MoveAction implements Action {

    private final int dx;
    private final int dy;
    private final Position.Orientation nextOrientation;

    MoveAction(int dx, int dy, Position.Orientation nextOrientation) {
        this.dx = dx;
        this.dy = dy;
        this.nextOrientation = requireNonNull(nextOrientation);
    }

    @Override
    public int getDeltaX() {
        return dx;
    }

    @Override
    public int getDeltaY() {
        return dy;
    }

    @Override
    public Position.Orientation getNextOrientation() {
        return nextOrientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveAction that = (MoveAction) o;
        return dx == that.dx &&
                dy == that.dy &&
                nextOrientation == that.nextOrientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy, nextOrientation);
    }
}
