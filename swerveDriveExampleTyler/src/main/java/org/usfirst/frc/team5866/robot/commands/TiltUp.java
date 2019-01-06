package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class TiltUp extends Command {

    public TiltUp() {
    	requires(RobotMap.claw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.claw.up();
    }

    protected boolean isFinished() {
    	if(RobotMap.tilter.get().equals("kReverse"))
    		return true;
    	else
    		return false;    
	}

    protected void end() {
    }


    protected void interrupted() {
    }
}
