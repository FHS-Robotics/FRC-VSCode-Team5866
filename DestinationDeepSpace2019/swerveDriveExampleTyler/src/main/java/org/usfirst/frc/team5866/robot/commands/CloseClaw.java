package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class CloseClaw extends Command {

    public CloseClaw() {
    	requires(RobotMap.claw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.claw.close();
    }

    protected boolean isFinished() {
    	if(RobotMap.gripper.get().equals("kForward")) {
        	System.out.println(RobotMap.gripper.get());
    		return true;}
    	else {
        	System.out.println(RobotMap.gripper.get());
    		return false;
    	}
    }

    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
