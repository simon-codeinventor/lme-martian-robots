package uk.co.codeinvent.robots;

import static java.util.Objects.requireNonNull;

public interface Instruction {

    String getCode();

    default Robot.Status execute(Robot robot) {
        return requireNonNull(robot, "robot expected")
                .follow(this);
    }
}
