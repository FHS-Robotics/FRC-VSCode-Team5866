/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.OI;


/**
 * This command controls the movement of the tank drive through the operator's joysticks
 */
public class TeleOpDrive extends Command {

  double sensitivity;
  public static double currDriveLeft; //current input being applied to the left motor
  public static double currDriveRight; //current input being applied to the right motor

  public static final double accelThreshold = .025; //the threshold for the interpolation of the acceleration curve to cut out

  public TeleOpDrive() {}

  @Override
  protected void initialize() 
  {
    currDriveLeft = 0;
    currDriveRight = 0;
  }

  @Override
  protected void execute()
  {
    //the joystick values will be modified by the sensitivity of the Joysticks
    sensitivity = (OI.sensitivity + 5) / 10.0; //get sensitivity from OI and divide it by 10 to scale it down, so mode 10 will be 2x speed and mode 5 will be normal speed

    /*
    if(OI.mode)
    {
      //take the y axis values from each joystick and send them to the swervedrive
      RobotMap.driveBase.tankDrive(-OI.m_leftStick.getY() * sensitivity, -OI.m_rightStick.getY() * sensitivity, true);
    }
    else
    {
      RobotMap.driveBase.tankDrive(-OI.secondaryController.getRawAxis(1) * sensitivity, -OI.secondaryController.getRawAxis(5) * sensitivity, true);
    }*/


    /**
     * This section of code uses the input of the joysticks and the input that is currently being applied
     * to the drive motors.  With both of these values, it interpolates between the two over time to make the
     * input for the motors equal to the input of the joysticks smoothly.  That way, instead of having immediate
     * jerking of the robot, it will ease into and out of movements
     */
    double valueL;
    double valueR;

    if(OI.mode)
    {
      valueL = currDriveLeft + (((-OI.m_leftStick.getY() * sensitivity) + currDriveLeft) / Math.sqrt(Math.abs(OI.m_leftStick.getY()))); //interpolate between the current input and the new input
      if(valueL > -OI.m_leftStick.getY())
        valueL = -OI.m_leftStick.getY() * sensitivity;    

      valueR = currDriveRight + (((-OI.m_rightStick.getY() * sensitivity) + currDriveRight) / Math.sqrt(Math.abs(OI.m_rightStick.getY()))); //interpolate between the current input and the new input
      if(valueR >= -OI.m_rightStick.getY())
        valueR = -OI.m_rightStick.getY() * sensitivity;  
    }
    else
    {
      valueL = currDriveLeft + (((-OI.secondaryController.getRawAxis(1) * sensitivity) + currDriveLeft) / Math.sqrt(Math.abs(-OI.secondaryController.getRawAxis(1)))); //interpolate between the current input and the new input
      if(valueL > -OI.secondaryController.getRawAxis(1))
        valueL = -OI.secondaryController.getRawAxis(1) * sensitivity;    
  
      valueR = currDriveRight + (((-OI.secondaryController.getRawAxis(5) * sensitivity) + currDriveRight) / Math.sqrt(Math.abs(-OI.secondaryController.getRawAxis(5)))); //interpolate between the current input and the new input
      if(valueR >= -OI.secondaryController.getRawAxis(5))
        valueR = -OI.secondaryController.getRawAxis(5) * sensitivity;  
    }

    //take the y axis values from each joystick and send them to the swervedrive
    RobotMap.driveBase.tankDrive(valueL, valueR, true);
  }

//#region Unused Methods
  @Override
  protected boolean isFinished() {
    return false;
  }
}
