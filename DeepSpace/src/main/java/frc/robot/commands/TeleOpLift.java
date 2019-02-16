package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOpLift extends Command {

    public double scaleFactorUp = .4; //scale factor for moving up
    public double scaleFactorDown = .25; //scale factor for moving down

    public TeleOpLift() {
        requires(RobotMap.liftSystem);
    }

    protected void initialize() {}

    protected void execute() {
        /*if the limit switches have been hit we can't go that direction anymore
        if((OI.m_leftStick.getX() < 0 && RobotMap.liftSystem.getLimitDown() == false)
        || (OI.m_leftStick.getX() > 0 && RobotMap.liftSystem.getLimitUp() == false ))
        {    
            RobotMap.liftSystem.move(OI.m_leftStick.getX());
            System.out.println("elevator moved");
        }*/

        double value = -OI.secondaryController.getRawAxis(1); //joystick values are negated
        value = (value > 0) ? (value * scaleFactorUp) : (value * scaleFactorDown);  //if moving up, multiply by scaleFactorUp; else multiply by scaleFactorDown; This code is a simplified if else statement

        //value = value * scaleFactorUp;
        RobotMap.liftSystem.move(value); //finally plug in the new value into our lift system movement 😂 look emojis
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}


    protected void interrupted() {}
}