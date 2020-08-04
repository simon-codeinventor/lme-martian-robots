package uk.co.codeinvent.robots;

public interface Position {

    enum Orientation {
        DISORIENTED, N, S, E, W
    }

    int getX();

    int getY();

    Orientation getOrientation();

    Position resolve(Action action);
}
