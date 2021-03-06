package uk.co.codeinvent.robots;

import static java.util.Objects.requireNonNull;

class MoveInstruction implements Instruction {

    private final String code;

    public MoveInstruction(String code) {
        this.code = requireNonNull(code, "code expected");
    }

    @Override
    public String getCode() {
        return code;
    }
}
