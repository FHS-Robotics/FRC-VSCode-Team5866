// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends TimedRobot {

  private final Timer m_timer = new Timer();

  // Constants for controlling the arm. consider tuning these for your particular
  // robot
  final double armHoldUp = 0.08;
  final double armHoldDown = 0.13;
  final double armTravel = 0.5;

  final double armTimeUp = 0.5;
  final double armTimeDown = 0.35;

  // Varibles needed for the code
  boolean armUp = true; // Arm initialized to up because that's how it would start a match
  boolean burstMode = false;
  double lastBurstTime = 0;

  double autoStart = 0;
  boolean goForAuto = false;

  public static final int c_driveForwardAxis = 1;
  public static final int c_driveRotateAxis = 0;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    Values.init();
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
  }

  @Override
  public void robotPeriodic() {
    RobotMap.m_arm.periodic();
    CommandScheduler.getInstance().run();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    RobotMap.m_frontLeft.setInverted(false);
    RobotMap.m_frontLeft.setSelectedSensorPosition(0);
    RobotMap.m_frontRight.setInverted(true);
    RobotMap.m_frontRight.setSelectedSensorPosition(0);
    RobotMap.m_backLeft.setInverted(false);
    RobotMap.m_backLeft.setSelectedSensorPosition(0);
    RobotMap.m_backRight.setInverted(true);
    RobotMap.m_backRight.setSelectedSensorPosition(0);
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    RobotMap.m_teleOpDrive.schedule();
  }

  @Override
  public void teleopExit() {
    RobotMap.m_teleOpDrive.end(false);
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    RobotMap.m_frontLeft.setInverted(false);
    RobotMap.m_frontLeft.setSelectedSensorPosition(0);
    RobotMap.m_frontRight.setInverted(true);
    RobotMap.m_frontRight.setSelectedSensorPosition(0);
    RobotMap.m_backLeft.setInverted(false);
    RobotMap.m_backLeft.setSelectedSensorPosition(0);
    RobotMap.m_backRight.setInverted(true);
    RobotMap.m_backRight.setSelectedSensorPosition(0);
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    
  }
}