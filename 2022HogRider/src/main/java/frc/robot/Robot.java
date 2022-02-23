// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public final class Robot extends TimedRobot {

  // region general
  @Override
  public void robotInit() {
    Values.init();
  }

  @Override
  public void robotPeriodic() {
    RobotMap.m_arm.periodic();
    CommandScheduler.getInstance().run();
  }
  // endregion

  // region autonomous
  @Override
  public void autonomousInit() {
    RobotMap.m_drive.prepareForAutonomous();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void autonomousExit() {
  }
  // endregion

  // region teleop
  @Override
  public void teleopInit() {
    RobotMap.m_teleOpDrive.schedule();
  }

  @Override
  public void teleopExit() {
    RobotMap.m_teleOpDrive.end(false);
  }

  @Override
  public void teleopPeriodic() {
  }
  // endregion

  // region test
  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }
  // endregion
}