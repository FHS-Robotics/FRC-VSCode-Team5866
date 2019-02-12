package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOpLift extends Command {

    public double scaleFactor = .25;

    public TeleOpLift() {
        requires(RobotMap.liftSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        /*if the limit switches have been hit we can't go that direction anymore
        if((OI.m_leftStick.getX() < 0 && RobotMap.liftSystem.getLimitDown() == false)
        || (OI.m_leftStick.getX() > 0 && RobotMap.liftSystem.getLimitUp() == false ))
        {    
            RobotMap.liftSystem.move(OI.m_leftStick.getX());
            System.out.println("elevator moved");
        }*/

        RobotMap.liftSystem.move(OI.secondaryController.getRawAxis(1) * scaleFactor);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}