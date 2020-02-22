/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.ColorWheel;

public class SpinColorWheel extends CommandBase {

  ColorWheel m_colorWheel;
  boolean direction;

  public SpinColorWheel(boolean _direction) {
    direction = _direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_colorWheel = RobotMap.m_ColorWheel;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_colorWheel.spin(direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheel.release();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
