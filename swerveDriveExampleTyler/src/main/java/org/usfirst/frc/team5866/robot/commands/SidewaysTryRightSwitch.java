package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SidewaysTryRightSwitch extends CommandGroup {
	String side;
    public SidewaysTryRightSwitch() {
    	try {
    		side = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
    	}catch(StringIndexOutOfBoundsException e){}
    	
    	if(side.charAt(0) == 'r') {
    		addSequential(new MoveForTime(90, 0, 1));
    		addSequential(new MoveForTime(90, .5, 4));
    		addSequential(new MoveForTime(180, 0, .5));
    		addSequential(new MoveForTime(180, .4, 1));
    		addSequential(new LiftUp());
    		addSequential(new OpenClaw());
    	}else {
    		addSequential(new MoveForTime(90, 0, 1));
    		addSequential(new MoveForTime(90, .5, 4));
    	}
    }
}
