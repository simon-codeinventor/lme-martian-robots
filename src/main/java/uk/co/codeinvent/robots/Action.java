package uk.co.codeinvent.robots;

public interface Action {

    int getDeltaX();

    int getDeltaY();

    Position.Orientation getNextOrientation();

    default boolean isNull() {
        return false;
    }
}
