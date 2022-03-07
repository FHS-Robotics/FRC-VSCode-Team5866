// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.Settings;

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
            Settings.init();
      }

      @Override
      public void robotPeriodic() {
            CommandScheduler.getInstance().run();
      }
      // endregion

      // region autonomous
      private double autoStart;
      @Override
      public void autonomousInit() {
            autoStart = Timer.getFPGATimestamp();
      }

      @Override
      public void autonomousPeriodic() {
            double fromStart = Timer.getFPGATimestamp() - autoStart;

            if (fromStart < 2) {
                  RobotMap.m_intake.move(-1);
            } else if (fromStart < 3.5) {
                  RobotMap.m_intake.move(0);
                  RobotMap.m_frontLeft.set(-0.25);
                  RobotMap.m_frontRight.set(-0.25);
                  RobotMap.m_backLeft.set(-0.25);
                  RobotMap.m_backRight.set(-0.25);
            } else {
                  RobotMap.m_frontLeft.set(0);
                  RobotMap.m_frontRight.set(0);
                  RobotMap.m_backLeft.set(0);
                  RobotMap.m_backRight.set(0);
                  System.out.println("Waiting for Auto Finish...");
            }
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
