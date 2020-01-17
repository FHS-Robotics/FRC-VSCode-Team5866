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

public class PIDDrive extends PIDSubsystem {

  public double speed;

  public PIDDrive() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));  
      }

  @Override
  public void useOutput(double output, double setpoint) {
    speed = output;
  }

  @Override
  public double getMeasurement() {
    return RobotMap.gyro.getYaw();
  }
}
