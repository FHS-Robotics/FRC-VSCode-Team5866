// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** A command that runs during Tele-Op period. */
public class TeleOpDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrive m_swerveDrive;

  public TeleOpDrive(SwerveDrive swerveDrive) {
    m_swerveDrive = swerveDrive;
    addRequirements(swerveDrive);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
