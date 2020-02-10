/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.RobotMap;

public class PIDVisionDrive extends PIDSubsystem {
  
  public double speed;
  private double errorThreshold = 0.5; //Our threshold of error is +-1 degree

  public PIDVisionDrive() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.1, 0, 0.001));

    getController().setTolerance(errorThreshold);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    speed = speed > 1 ? 1 : speed;
    speed = speed < -1 ? -1 : speed;
    speed = output;
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return -RobotMap.limeLight.getX();
  }

  public double getSetpoint() {
    return m_controller.getSetpoint();
  }

 /**
   * Returns whether the robot is close enough to the set angle
   * @return
   */
  public boolean onTarget() {
    return Math.abs(getMeasurement() - getSetpoint()) < errorThreshold;
  }
}
