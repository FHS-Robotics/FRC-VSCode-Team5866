/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.IronDashboardWASD;
import frc.robot.OI;


/**
 * This command controls the movement of the tank drive through the operator's joysticks
 */
public class TeleOpDrive extends Command {

  double sensitivity;
  public static double currDriveLeft; //current input being applied to the left motor
  public static double currDriveRight; //current input being applied to the right motor

  Timer t;
  String m;

  public static final double accelTime = 750; //the time in milliseconds to accelerate to full speed from a full stop
  public static final double accelTurnTime = 250;

  public TeleOpDrive() {}

  @Override
  protected void initialize() 
  {
    System.out.println("initializing");
    currDriveLeft = 0;
    currDriveRight = 0;
    t = new Timer();
    t.start();
    //m = IronDashboardWASD.getMessage(); //if we are using keyboard input
  }

  @Override
  protected void execute()
  {

    //the first step of our driving code is to find what speeds, forward and turn speeds, the driver wants the robot to reach
    double targetL;
    double targetR;

    //check which mode we are using, then decide which controller to read from
    if(OI.mode)
    {
      targetL = -OI.driverController.getRawAxis(1);
      targetR = OI.driverController.getRawAxis(4); //default 4, but if using just one controller to operate the entire bot use 0
    }
    else
    {
      targetL = -OI.secondaryController.getRawAxis(1);
      targetR = -OI.secondaryController.getRawAxis(5);
    } 

    //if we aren't driving using the pid
    if(OI.pidDriving == false)
    {
      /**
      * This section of code uses the input of the xbox joysticks and the input that is currently being applied
      * to the drive motors.  With both of these values, it interpolates between the two over time to make the
      * input for the motors equal to the input of the joysticks smoothly.  That way, instead of having immediate
      * jerking of the robot, it will ease into and out of movements
      */
      accelerateDriveBase(targetL, targetR);
    }

    // ** this section of code is only used if we are using wasd, and the code above would be commented out.
    // ** Currently we do not have a way of receiving both inputs at the same time
    
    /*If the method has not returned, then we can check for the iron dashboard

    m = IronDashboardWASD.getMessage();

    switch (m) {
      case "Forward":
        System.out.println("Forward");
        accelerateDriveBase(.75, .75);
        //RobotMap.driveBase.tankDrive(.75, .75);
        return;
      case "Left":
        System.out.println("Left");
        accelerateDriveBase(-.5, .5);
        //RobotMap.driveBase.tankDrive(-.5, .5);
        return;
      case "Right":
        System.out.println("Right");
        accelerateDriveBase(.5, -.5);
        //RobotMap.driveBase.tankDrive(.5, -.5);
        return;
      case "Backward":
        System.out.println("Backward");
        accelerateDriveBase(-.75, -.75);
        //RobotMap.driveBase.tankDrive(-.75, -.75);
        return;
      case "ForwardRight":
        System.out.println("ForwardRight");
        accelerateDriveBase(.75, .25);
        //RobotMap.driveBase.tankDrive(.75, .5);
        return;
      case "ForwardLeft":
        System.out.println("ForwardLeft");
        accelerateDriveBase(.25, .75);
        //RobotMap.driveBase.tankDrive(.5, .75);
        return;
      case "BackwardRight":
        System.out.println("BackwardRight");
        accelerateDriveBase(-.75, -.25);
        //RobotMap.driveBase.tankDrive(-.75, -.5);
        return;
      case "BackwardLeft":
        System.out.println("BackwardLeft");
        accelerateDriveBase(-.25, -.75);
        //RobotMap.driveBase.tankDrive(-.5, -.75);
        return;
      case "Stop":
        System.out.println("Stop");
        accelerateDriveBase(0, 0);
        //RobotMap.driveBase.tankDrive(0, 0);
        return;
      default:
        System.out.println("No message!");
        break;
    }*/
  }

  /**
   * This method accelerates the drive base to the specified speed and turn speed
   */
  public void accelerateDriveBase(double targetL, double targetR)
  {
    //the joystick values will be modified by the sensitivity of the Joysticks
    sensitivity = (OI.sensitivity + 5) / 10.0; //get sensitivity from OI and divide it by 10 to scale it down, so mode 10 will be 2x speed and mode 5 will be normal speed
    targetL *= sensitivity;
    targetR *= sensitivity;

    double valueL;
    double valueR;
    
    if(targetL > currDriveLeft)
      valueL = currDriveLeft + (1 * sensitivity)/(accelTime/20); //20 represents the milliseconds it takes to complete a frame
    else
      valueL = currDriveLeft - (1 * sensitivity)/(accelTime/20); //The 1 and -1 represent our limit speeds, which should be 1 and -1 factored by the sensitivity
    
    if(targetR > currDriveRight)
      valueR = currDriveRight + (1 * sensitivity)/(accelTurnTime/20); //20 represents the milliseconds it takes to complete a frame
    else
      valueR = currDriveRight - (1 * sensitivity)/(accelTurnTime/20); //The 1 and -1 represent our limit rotational values, which should be 1 and -1
    
    //if we have gone to far then clamp our value to where we want to be
    if(currDriveLeft <= targetL && valueL >= targetL || currDriveLeft >= targetL && valueL <= targetL)
    {
      valueL = targetL;
    }
    if(currDriveRight <= targetR && valueR >= targetR || currDriveRight >= targetR && valueR <= targetR)
    {
      valueR = targetR;
    }
    
    //take the y axis values from each joystick and send them to the arcade drive
    //RobotMap.driveBase.tankDrive(valueL, valueR, true); //deprecated drive (now we are using speed and turn speed)
    RobotMap.driveBase.arcadeDrive(valueL, valueR * .8); //new drive times a dappening value of .8

    currDriveLeft = valueL;
    currDriveRight = valueR;
  }

//#region Unused Methods
  @Override
  protected boolean isFinished() {
    return false;
  }
}
