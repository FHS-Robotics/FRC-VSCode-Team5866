package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOpLift extends Command {

    public double scaleFactorUp = 1;
     //scale factor for moving up
    public double scaleFactorDown = 1 ; //scale factor for moving down

    public TeleOpLift() {
        requires(RobotMap.liftSystem);
    }

    protected void initialize() {}

    protected void execute() {

        //this if statement checks whether our control mode is in normal control or inverted control
        double value = OI.mode? OI.secondaryController.getRawAxis(1) : OI.driverController.getRawAxis(1);
        if(Math.abs(value) > .1)
        {
            value = (value < 0) ? (value * scaleFactorUp) : (value * scaleFactorDown);  //if moving up, multiply by scaleFactorUp; else multiply by scaleFactorDown; This code is a simplified if else statement
            //value += .5; //add .5 to hold elevator in current position
 
            RobotMap.liftSystem.move(value); //finally plug in the new value into our lift system movement 😂 look emojis
        }
        else
        {
            RobotMap.liftSystem.move(0); //if we aren't greater than .1 then don't move at all
        }
    } 

    protected boolean isFinished() {
        return false;
    }
}