package uk.co.codeinvent.robots;

public interface ActionResolver {

    Action resolve(Instruction instruction, Position position);
}
