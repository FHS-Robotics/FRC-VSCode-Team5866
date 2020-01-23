/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class SetDriveAngle extends CommandBase {

  float angle;

  /**
   * Creates a new TurnToAngle.
   */
  public SetDriveAngle(float _angle) {
    angle = _angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotMap.m_pidDrive.setSetpoint(angle);
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
