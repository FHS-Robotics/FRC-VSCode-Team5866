/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.PIDVisionDrive;

public class VisionAlignRotational extends CommandBase {

  private PIDVisionDrive m_visionDrive;

  public VisionAlignRotational() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotMap.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_visionDrive = RobotMap.m_visionDrive;
    m_visionDrive.setSetpoint(0);
    
    RobotMap.limeLight.ledOn();

    //enable PID system
    m_visionDrive.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double xSpeed = -OI.m_driverControl.getRawAxis(0); //move left and right using the joysticks

    RobotMap.m_drive.m_swiftDrive.driveCartesian(xSpeed, 0, -m_visionDrive.speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_visionDrive.disable();
    //RobotMap.limeLight.ledOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
