package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SidewaysLeftTrySwitch extends CommandGroup {
	String side;
    public SidewaysLeftTrySwitch() {
    	try {
    		side = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
    	}catch(StringIndexOutOfBoundsException e){
    		System.out.print("");
    	}
    	
    	if(side.charAt(0) == 'l') {
    		addSequential(new MoveForTime(270, 0, 1));
    		addSequential(new MoveForTime(270, .5, 4));
    		addSequential(new MoveForTime(180, 0, .5));
    		addSequential(new MoveForTime(180, .4, 1));
    		addSequential(new LiftUp());
    		addSequential(new OpenClaw());
    	}else {
    		addSequential(new MoveForTime(270, 0, 1));
    		addSequential(new MoveForTime(270, .5, 4));
    	}
    }
}
