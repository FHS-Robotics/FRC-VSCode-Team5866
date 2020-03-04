/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.VersaDrive.DriveState;

public class DriveForTime extends CommandBase {

  Timer timer;
  double driveTime;

  double xSpeed;
  double ySpeed;
  double rotation;

  public DriveForTime(double _xSpeed, double _ySpeed, double _rotation, double _driveTime) {
    addRequirements(RobotMap.m_drive);
    driveTime = _driveTime;
    xSpeed = _xSpeed;
    ySpeed = -_ySpeed;
    rotation = _rotation;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    RobotMap.m_drive.setState(DriveState.power);
    timer.start();
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotMap.m_drive.m_swiftDrive.driveCartesian(xSpeed, ySpeed, -rotation); //for driving without the gyro
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotMap.m_drive.m_swiftDrive.driveCartesian(0, 0, 0); //for driving without the gyro
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (timer.get() >= driveTime);
  }
}
