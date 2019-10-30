package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class SwitchAuto extends CommandGroup {
	String side;
    public SwitchAuto() {
    	try {
    		side = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
    	}catch(StringIndexOutOfBoundsException e){
    		System.out.print("");
    	}
    	
        if(side.charAt(0) == 'r') {
        	addSequential(new MoveForTime(145 , 0, 1));
        	addSequential(new MoveForTime(145, .5, 3.5));
        	addSequential(new LiftUp());
        	addSequential(new OpenClaw());
        }else{        
        	addSequential(new MoveForTime(215, 0, 1));
        	addSequential(new MoveForTime(215, 0, 3.5));
        	addSequential(new LiftUp());
        	addSequential(new OpenClaw());

        }
    }
}
