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

  public double magnifier = 5;

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


    double rotation;

    //snap to points if POV buttons are pressed
    if(OI.turnForward.get()) {
      rotation = 0;
      m_pidDrive.setSetpoint(rotation);
    }
    else if(OI.turnRight.get()) {
      rotation = 90;
      m_pidDrive.setSetpoint(rotation);
    }
    else if(OI.turnBack.get()) {
      rotation = 180;
      m_pidDrive.setSetpoint(rotation);
    }
    else if(OI.turnLeft.get()) {
      rotation = -90;
      m_pidDrive.setSetpoint(rotation);
    }
    else {
      //set the angle of the z rotation to the position the controller
      m_pidDrive.addToSetpoint(zRotation * magnifier); //remap the zRptation value to a number between -180 : 180
    }

    //System.out.println(xSpeed);
    //System.out.println(ySpeed);
    //System.out.println(zRotation);

    //move based on the pid setpoint
    rotation = m_pidDrive.speed;

    System.out.println(m_pidDrive.getSetpoint());

    if(m_drive.mode == VersaDrive.DriveState.swift) {
      //m_drive.m_swiftDrive.driveCartesian(xSpeed, ySpeed, rotation);
      m_drive.m_swiftDrive.driveCartesian(xSpeed, ySpeed, zRotation);

    }
    else {
      //basically arcade drive with the mecanum
      //m_drive.m_swiftDrive.driveCartesian(0, ySpeed, rotation);
      m_drive.m_swiftDrive.driveCartesian(0, ySpeed, zRotation);
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
