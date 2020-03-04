/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.PIDVisionDrive;
import frc.robot.subsystems.VersaDrive;

public class VisionAlignHorizontal extends CommandBase {

  private VersaDrive m_drive;
  private PIDVisionDrive m_visionDrive;
  private PIDDrive m_pidDrive;

  private Timer timer = new Timer();
  private Timer waitTimer = new Timer();
  private double waitTime = 3; //wait 3 seconds after passing through the first lining up

  public VisionAlignHorizontal() {
    addRequirements(RobotMap.m_drive);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive = RobotMap.m_drive;
    m_visionDrive = RobotMap.m_visionDrive;
    m_pidDrive = RobotMap.m_pidDrive;

    //set PID's to their 0 horizontal and 0 degree
    m_pidDrive.setSetpoint(0);
    m_visionDrive.setSetpoint(0);
    
    RobotMap.limeLight.ledOn();

    //enable both PID systems
    m_pidDrive.enable();
    m_visionDrive.enable();

    //begin both used timers
    timer.start();
    waitTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if we're already on target angle-wise start moving left and right
    /*if(m_pidDrive.onTarget()){
      m_drive.m_swiftDrive.driveCartesian(m_visionDrive.speed, 0, m_pidDrive.speed);
    }
    else {
      m_drive.m_swiftDrive.driveCartesian(0, 0, m_pidDrive.speed); //otherwise make sure we're on angle first
    }
    if(!m_pidDrive.onTarget() || !m_visionDrive.onTarget()) {
      waitTimer.reset();
    }
    else {
      System.out.println("alligned");
    }*/

    //wait 0.5 second to allow the limelight to turn on
    if(timer.get() > 0.5) {
      m_drive.m_swiftDrive.driveCartesian(m_visionDrive.speed, 0, m_pidDrive.speed);

      System.out.println(m_pidDrive.speed);
      System.out.println(RobotMap.limeLight.getX());
    }
    else {
      m_drive.m_swiftDrive.driveCartesian(m_visionDrive.speed, 0, m_pidDrive.speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_visionDrive.disable();
    m_pidDrive.disable();
    RobotMap.limeLight.ledOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return (waitTimer.get() >= waitTime || timer.get() >= 10); //return finished either after the target is reached or 10 seconds have elapsed
    return false;
  }
}
