/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Actuator;

public class ExtendColorWheel extends CommandBase {
  /**
   * Creates a new Actuator.
   */
  boolean forward = false;


  Actuator m_actuator;

  public ExtendColorWheel(boolean _forward) {
    m_actuator = RobotMap.m_actuator;
    forward = _forward;
  }

  public ExtendColorWheel() {
    m_actuator = RobotMap.m_actuator;
    forward = !forward;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double value = forward ? 1 : -1;
    m_actuator.set(value);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
