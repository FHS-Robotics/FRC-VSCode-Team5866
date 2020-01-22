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
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.VersaDrive;

public class TeleOpDrive extends CommandBase {

  VersaDrive m_drive;
  PIDDrive m_pidDrive;

  /**
   * Creates a new TeleOpDrive.1
   */
  public TeleOpDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotMap.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive = RobotMap.m_drive;
    m_pidDrive = RobotMap.m_pidDrive;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double xSpeed = OI.m_driverControl.getRawAxis(0); 
    double ySpeed = OI.m_driverControl.getRawAxis(1);
    double zRotation = OI.m_driverControl.getRawAxis(4);
    
    //dead spot
    xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0;
    ySpeed = Math.abs(ySpeed) > 0.1 ? ySpeed : 0;
    zRotation = Math.abs(zRotation) > 0.1 ? zRotation : 0;

    System.out.println(xSpeed);
    System.out.println(ySpeed);
    System.out.println(zRotation);

    //set the angle of the z rotation to the position the controller
    m_pidDrive.setSetpoint(m_pidDrive.getSetpoint() + zRotation);

    //move based on the pid setpoint
    double rotation = m_pidDrive.speed;

    if(m_drive.mode == VersaDrive.DriveState.swift) {
      m_drive.m_swiftDrive.driveCartesian(ySpeed, xSpeed, rotation);
    }
    else {
      //basically arcade drive with the mecanum
      m_drive.m_swiftDrive.driveCartesian(ySpeed, 0, rotation);
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
