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

  private double currentXSpeed = 0;
  private double currentYSpeed = 0;
  private double currentZSpeed = 0;



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

    m_pidDrive.setSetpoint(RobotMap.gyro.getYaw()); //set yaw to the current yaw to start
    m_pidDrive.enable();
    currentXSpeed = 0;
    currentYSpeed = 0;
    currentZSpeed = 0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double xSpeed = OI.m_driverControl.getRawAxis(0); 
    double ySpeed = OI.m_driverControl.getRawAxis(1);
    double zRotation = OI.m_driverControl.getRawAxis(4);
    //double zRotation = 180; //for testing a 180 degree turn


    //dead spot
    xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0;
    ySpeed = Math.abs(ySpeed) > 0.1 ? ySpeed : 0;
    zRotation = Math.abs(zRotation) > 0.2 ? zRotation : 0;


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

    /*make all of our values safe for the motors.  If we are going full speed
      in one direction and all of a sudden try to completely reverse the speed, the
      motors will die.  Instead, we'll zero them first
    */
    if(Math.abs(xSpeed - currentXSpeed) > 1) {
      xSpeed = 0;
    }
    if(Math.abs(ySpeed - currentYSpeed) > 1) {
      ySpeed = 0;
    }
    if(Math.abs(rotation - currentZSpeed) > 1) {
      rotation = 0;
    }

    currentXSpeed = xSpeed;
    currentYSpeed = ySpeed;
    currentZSpeed = rotation;

    if(m_drive.mode == VersaDrive.DriveState.swift) {
      //m_drive.m_swiftDrive.driveCartesian(xSpeed, ySpeed, rotation); //for driving using the gyro
      m_drive.m_swiftDrive.driveCartesian(xSpeed, ySpeed, -zRotation); //for driving without the gyro
    }
    else {
      //basically arcade drive with the mecanum
      //m_drive.m_swiftDrive.driveCartesian(0, ySpeed, rotation); //for driving using the gyro
      m_drive.m_swiftDrive.driveCartesian(0, ySpeed, -zRotation); //for driving without the gyro
      currentXSpeed = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pidDrive.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
