package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class OpenClaw extends Command {

    public OpenClaw() {
    	requires(RobotMap.claw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.claw.open();
    }

    protected boolean isFinished() {
    	if(RobotMap.gripper.get().equals("kReverse")) {
        	System.out.println(RobotMap.gripper.get());
    		return true;
		}else{
        	System.out.println(RobotMap.gripper.get());
    		return false;
    	}
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
