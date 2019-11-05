package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class TiltDown extends Command {

    public TiltDown() {
    	requires(RobotMap.claw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.claw.down();
    }

    protected boolean isFinished() {
    	if(RobotMap.tilter.get().equals("kForward"))
    		return true;
    	else
    		return false;    }

    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
