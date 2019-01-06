package org.usfirst.frc.team5866.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightNoSwitch extends CommandGroup {

    public StraightNoSwitch() {
        addSequential(new MoveForTime(180, 0, 1));
        addSequential(new MoveForTime(180, .5, 3.5));
    }
}
