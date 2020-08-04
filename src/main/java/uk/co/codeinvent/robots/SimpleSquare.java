package uk.co.codeinvent.robots;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

class SimpleSquare implements Grid.Square {

    private final Position position;
    private final Collection<Action> illegalActions;

    SimpleSquare(Position position) {
        this.position = requireNonNull(position, "position expected");
        illegalActions = new ArrayList<>();
    }

    @Override
    public Action effect(Action action) {
        requireNonNull(action, "action expected");
        if (illegalActions.contains(action)) {
            return new NullAction(action);
        }
        return action;
    }

    @Override
    public void addIllegal(Action action) {
        illegalActions.add(requireNonNull(action, "action expected"));
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
