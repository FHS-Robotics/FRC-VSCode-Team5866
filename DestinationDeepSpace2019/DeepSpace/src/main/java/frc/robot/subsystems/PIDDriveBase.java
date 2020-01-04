/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.RobotMap;

/**
 * This drive base class is for the automatic turning of the drive
 * base to a specified angle using closed-control feedback with the
 * gyro sensor
 */
public class PIDDriveBase extends PIDSubsystem {

  SpeedControllerGroup right;
  SpeedControllerGroup left;
  AHRS gyro;

  //The proportional, integral, and differential values (not tuned)
  //Static because it is the same throughout all instances (though there should only be one instance)
  //public static double kP = 0.01; //default .5
  //public static double kI = 0.002; //default 0
  //public static double kD = 0.0; //default 0
  public static double kP = 0.5; //default .5
  public static double kI = 0.0; //default 0
  public static double kD = 0.0; //default 0
  
  public PIDDriveBase() {
    super("PIDDriveBase", kP, kI, kD);
    setAbsoluteTolerance(2.0f); //the angle of tolerance which the controller can read on target if it reaches
    getPIDController().setInputRange(-180.0f,  180.0f);
    getPIDController().setOutputRange(-1.0, 1.0); //since it is controlling the wheels, it is from -1 to 1
    getPIDController().setContinuous(true); //false because we do not continuously add or subtract yaw, just stay between -180 and 180
    //LiveWindow.addActuator("PIDDriveBase", "PIDSubsystem Controller", getPIDController());
  }

  public void setSpeedControllers(SpeedControllerGroup l, SpeedControllerGroup r)
  {
    right = r;
    left = l;
  }

  public void setGyro(AHRS g)
  {
    gyro = g;
  }
  
  public void initDefaultCommand() {}

  public double getGyroAngle(){
    return gyro.getYaw();
  }

  public void resetGyro(){
      gyro.reset();
  }

  protected double returnPIDInput() {
      // Return your input value for the PID loop
      // e.g. a sensor, like a potentiometer:
      // yourPot.getAverageVoltage() / kYourMaxVoltage
      return gyro.getYaw();
  }
  
  protected void usePIDOutput(double output) {
      // Use output to drive your system, like a motor
      // e.g. yourMotor.set(output);
      //left.pidWrite(output);
      //right.pidWrite(output);
      left.pidWrite(output);
      right.pidWrite(output);
  }
}
