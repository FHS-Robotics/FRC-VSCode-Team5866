package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Cube extends CommandGroup {

    public Cube() {
    	if(DriverStation.getInstance().getGameSpecificMessage().toLowerCase().charAt(0) == 'l') {
    		addSequential(new MoveForTime(.5, .6, 5));
            addSequential(new LiftUp());
            addSequential(new OpenClaw());
    	}else {
    		addSequential(new MoveForTime(1, .6, 4));

    	}

        
    }
}
