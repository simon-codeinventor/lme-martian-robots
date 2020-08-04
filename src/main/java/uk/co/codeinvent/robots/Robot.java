package uk.co.codeinvent.robots;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static uk.co.codeinvent.robots.Robot.Status.OK;

public interface Robot {

    enum Status {
        OK, LOST
    }

    Status follow(Instruction instruction);

    default Status follow(List<Instruction> instructions) {
        requireNonNull(instructions, "instructions expected");
        Status currentStatus = getStatus();
        for (Instruction instruction : instructions) {
            if (OK != currentStatus) break;
            currentStatus = follow(instruction);
        }
        return currentStatus;
    }

    Position getPosition();

    Status getStatus();
}
