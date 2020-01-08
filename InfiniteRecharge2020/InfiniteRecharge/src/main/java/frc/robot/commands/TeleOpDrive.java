/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase; //currently not importing
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.VersaDrive;

public class TeleOpDrive extends CommandBase {

  VersaDrive m_drive;

  /**
   * Creates a new TeleOpDrive.1
   */
  public TeleOpDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive = RobotMap.m_drive;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(m_drive.mode == VersaDrive.DriveState.swift) {
      double xSpeed = OI.m_driverControl.getRawAxis(0);
      double ySpeed = OI.m_driverControl.getRawAxis(1);
      double zRotation = OI.m_driverControl.getRawAxis(4);
      m_drive.m_swiftDrive.driveCartesian(ySpeed, xSpeed, zRotation);
    }
    else {
      double ySpeed = OI.m_driverControl.getRawAxis(1);
      double zRotation = OI.m_driverControl.getRawAxis(4);
      m_drive.m_powerDrive.arcadeDrive(ySpeed, zRotation);
    }
  }

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
