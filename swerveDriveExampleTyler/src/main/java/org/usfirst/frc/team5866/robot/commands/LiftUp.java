package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUp extends Command {
	Timer a;
    public LiftUp() {
        requires(RobotMap.lift);
        a = new Timer();
    }

    protected void initialize() {
    	a.reset();
    	a.start();
    }

    protected void execute() {
    	RobotMap.lift.move(-.5);
    }

    protected boolean isFinished() {
        return a.hasPeriodPassed(2);
    }

    protected void end() {
    	RobotMap.lift.move(0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
