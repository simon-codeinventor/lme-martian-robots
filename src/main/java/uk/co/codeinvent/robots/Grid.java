package uk.co.codeinvent.robots;

public interface Grid {

    interface Square {

        Action effect(Action action);

        void addIllegal(Action action);

        Position getPosition();
    }

    int getxMax();

    int getyMax();

    boolean isOffGrid(Position position);

    Action effect(Action action, Position currentPosition);

    void addIllegal(Action action, Position currentPosition);
}
