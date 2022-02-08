// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDrive extends SubsystemBase {
  private SpeedControllerGroup m_left;
  private SpeedControllerGroup m_right;
  private DifferentialDrive m_drive;

  public TankDrive(SpeedController frontLeft, SpeedController frontRight, SpeedController middleLeft, SpeedController middleRight) {
    m_left = new SpeedControllerGroup(frontLeft, middleLeft);
    m_right = new SpeedControllerGroup(frontRight, middleRight);
    m_drive = new DifferentialDrive(m_left, m_right);
  }

  public void setSpeed(double left, double right) {
    m_drive.tankDrive(left, right);
  }
}
