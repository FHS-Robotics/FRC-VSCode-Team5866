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

  public static final double accelTime = 1000; //the time in milliseconds to accelerate to full speed from a full stop

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

    /**
     * This section of code uses the input of the joysticks and the input that is currently being applied
     * to the drive motors.  With both of these values, it interpolates between the two over time to make the
     * input for the motors equal to the input of the joysticks smoothly.  That way, instead of having immediate
     * jerking of the robot, it will ease into and out of movements
     */
    double targetL;
    double targetR;
    double valueL;
    double valueR;

    if(OI.mode)
    {
      //take the y axis values from each joystick and send them to the swervedrive
      targetL = -OI.m_leftStick.getY() * sensitivity;
      targetR = -OI.m_rightStick.getY() * sensitivity;
    }
    else
    {
      targetL = -OI.secondaryController.getRawAxis(1) * sensitivity;
      targetR = -OI.secondaryController.getRawAxis(5) * sensitivity;
    }

     
      //bring valueR and valueL closer together
      double l = targetL;
      double r = targetR;
      double average = (l+r)/2;
      double scale = 4; //higher scale is, the less averaging

      targetL += (average - l) / scale; //this code brings our joystick values closer together
      targetR += (average - r) / scale;


    if(targetL > currDriveLeft)
      valueL = currDriveLeft + (1 * sensitivity)/(accelTime/20); //20 represents the milliseconds it takes to complete a frame
    else
      valueL = currDriveLeft - (1 * sensitivity)/(accelTime/20); //The 1 and -1 represent our limit speeds, which should be 1 and -1 factored by the sensitivity


    if(targetR > currDriveRight)
      valueR = currDriveRight + (1 * sensitivity)/(accelTime/20); //20 represents the milliseconds it takes to complete a frame
    else
      valueR = currDriveRight - (1 * sensitivity)/(accelTime/20); //The 1 and -1 represent our limit speeds, which should be 1 and -1


      //if we have gone to far then clamp our value to where we want to be
      if(currDriveLeft <= targetL && valueL >= targetL || currDriveLeft >= targetL && valueL <= targetL)
      {
        valueL = targetL;
      }
      if(currDriveRight <= targetR && valueR >= targetR || currDriveRight >= targetR && valueR <= targetR)
      {
        valueR = targetR;
      }

    //take the y axis values from each joystick and send them to the swervedrive
    RobotMap.driveBase.tankDrive(valueL, valueR, false);
    currDriveLeft = valueL;
    currDriveRight = valueR;
  }

//#region Unused Methods
  @Override
  protected boolean isFinished() {
    return false;
  }
}
