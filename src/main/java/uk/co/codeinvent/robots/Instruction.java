package uk.co.codeinvent.robots;

public interface Instruction {

    String getCode();

    Robot.Status execute(Robot robot);
}
