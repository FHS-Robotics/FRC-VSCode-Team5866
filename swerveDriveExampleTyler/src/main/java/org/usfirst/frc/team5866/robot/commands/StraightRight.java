package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightRight extends CommandGroup {
	String side;
    public StraightRight() {
    	
    	try {
    		side = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
    	}catch(StringIndexOutOfBoundsException e){
    		System.out.print("");
    	}
    	
    	if(side.charAt(0) == 'r') {
        	addSequential(new MoveForTime(180, 0, 1));
            addSequential(new MoveForTime(180, .5, 3.5));
        	addSequential(new LiftUp());
            addSequential(new OpenClaw());
    	}else {
        	addSequential(new MoveForTime(180, 0, 1));
            addSequential(new MoveForTime(180, .5, 4));
    	}

    }
}
