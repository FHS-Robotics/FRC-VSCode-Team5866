package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.OI;
import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOpDrive extends Command {

    public TeleOpDrive() {
        requires(RobotMap.swerve);
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.swerve.drive(OI.rightDriveStick.getRawAxis(0), OI.rightDriveStick.getRawAxis(1), OI.leftDriveStick.getRawAxis(2));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
