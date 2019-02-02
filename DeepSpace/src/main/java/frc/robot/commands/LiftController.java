package frc.robot.commands;

import org.usfirst.frc.team5866.robot.OI;
import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftController extends Command {

    public LiftController() {
        requires(RobotMap.lift);
    }

    protected void initialize() {
    }

    protected void execute() {
		RobotMap.lift.move(OI.m_leftStick.getX());
    }

    protected boolean isFinished() {
        //limt switch will continually read if the arm has reached max height, then will return true when condition is met
        while (limitSwitch.Get()) {
            return false;
        }
        return true;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}