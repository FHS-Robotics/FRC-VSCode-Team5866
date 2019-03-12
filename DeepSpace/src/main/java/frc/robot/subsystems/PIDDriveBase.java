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

/**
 * Add your docs here.
 */
public class PIDDriveBase extends PIDSubsystem {

  SpeedControllerGroup right;
  SpeedControllerGroup left;
  AHRS gyro;

  public double kP = 0.5;
  public double kI = 0.0;
  public double kD = 0.0;
  
  public PIDDriveBase() {
    super("PIDDriveBase", 0.5, 0.0, 0.0);
    setAbsoluteTolerance(3.0);
    getPIDController().setInputRange(-180.0f,  180.0f);
    getPIDController().setOutputRange(-1, 1); //since it is controlling the wheels, it is from -1 to 1
    getPIDController().setContinuous(true);
    LiveWindow.addActuator("PIDDriveBase", "PIDSubsystem Controller", getPIDController());
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
      left.pidWrite(output);
      right.pidWrite(output);
  }
}
