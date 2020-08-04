package uk.co.codeinvent.robots;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

class NullAction implements Action {

    private final Position.Orientation orientation;

    NullAction(Action action) {
        requireNonNull(action, "action expected");
        orientation = action.getNextOrientation();
    }

    @Override
    public int getDeltaX() {
        return 0;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }

    @Override
    public Position.Orientation getNextOrientation() {
        return orientation;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NullAction noAction = (NullAction) o;
        return orientation == noAction.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation);
    }
}
