package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.OI;
import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftController extends Command {

    public LiftController() {
        requires(RobotMap.lift);
    }

    protected void initialize() {
    }

    protected void execute() {
		RobotMap.lift.move(OI.secondaryController.getRawAxis(1));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
