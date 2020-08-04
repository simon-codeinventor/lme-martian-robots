package uk.co.codeinvent.robots;

import static java.util.Objects.requireNonNull;

class SimpleActionResolver implements ActionResolver {

    @Override
    public Action resolve(Instruction instruction, Position position) {
        requireNonNull(instruction, "instruction expected");
        requireNonNull(position, "position expected");
        switch (instruction.getCode()) {
            case "L":
                return leftMoveAction(position);
            case "R":
                return rightMoveAction(position);
            case "F":
                return forwardMoveAction(position);
            default:
                throw new IllegalArgumentException("unknown instruction " + instruction.getCode());
        }
    }

    private Action forwardMoveAction(Position position) {
        final Position.Orientation orientation = position.getOrientation();
        switch (orientation) {
            case N:
                return new MoveAction(0, 1, orientation);
            case E:
                return new MoveAction(1, 0, orientation);
            case S:
                return new MoveAction(0, -1, orientation);
            case W:
                return new MoveAction(-1, 0, orientation);
            default:
                throw new IllegalStateException("unknown orientation " + orientation);
        }
    }

    private Action leftMoveAction(Position position) {
        final Position.Orientation orientation = position.getOrientation();
        switch (orientation) {
            case N:
                return new MoveAction(0, 0, Position.Orientation.W);
            case E:
                return new MoveAction(0, 0, Position.Orientation.N);
            case S:
                return new MoveAction(0, 0, Position.Orientation.E);
            case W:
                return new MoveAction(0, 0, Position.Orientation.S);
            default:
                throw new IllegalStateException("unknown orientation " + orientation);
        }
    }

    private Action rightMoveAction(Position position) {
        final Position.Orientation orientation = position.getOrientation();
        switch (orientation) {
            case N:
                return new MoveAction(0, 0, Position.Orientation.E);
            case E:
                return new MoveAction(0, 0, Position.Orientation.S);
            case S:
                return new MoveAction(0, 0, Position.Orientation.W);
            case W:
                return new MoveAction(0, 0, Position.Orientation.N);
            default:
                throw new IllegalStateException("unknown orientation " + orientation);
        }
    }
}
