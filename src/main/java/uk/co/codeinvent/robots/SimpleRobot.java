package uk.co.codeinvent.robots;

import static java.util.Objects.requireNonNull;
import static uk.co.codeinvent.robots.Robot.Status.LOST;
import static uk.co.codeinvent.robots.Robot.Status.OK;

class SimpleRobot implements Robot {

    private final Grid grid;
    private final ActionResolver actionResolver;
    private Position position;
    private Status status;

    SimpleRobot(Grid grid, ActionResolver actionResolver, Position position) {
        this.grid = requireNonNull(grid, "grid expected");
        this.actionResolver = requireNonNull(actionResolver, "action resolver expected");
        this.position = requireNonNull(position, "position expected");
        this.status = OK;
    }

    @Override
    public Status follow(Instruction instruction) {
        if (OK == status) {
            Action action = actionResolver.resolve(instruction, getPosition());
            Action effectiveAction = grid.effect(action, getPosition());
            return take(effectiveAction);
        }
        return status;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    private Status take(Action action) {
        Position newPosition = position.resolve(action);
        if (grid.isOffGrid(newPosition)) {
            status = LOST;
            grid.addIllegal(action, position);
        } else {
            position = newPosition;
        }
        return status;
    }

    @Override
    public String toString() {
        return "" +
                position.getX() +
                ' ' +
                position.getY() +
                ' ' +
                position.getOrientation() +
                ((OK != status) ? " " + status : "");
    }
}
