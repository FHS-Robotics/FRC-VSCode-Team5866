// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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
      private RobotContainer m_robotContainer = new RobotContainer();

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
      @Override
      public void autonomousInit() {
            m_robotContainer.getAutonomousCommand().schedule();
      }

      @Override
      public void autonomousPeriodic() {
      }

      @Override
      public void autonomousExit() {
            m_robotContainer.getAutonomousCommand().cancel();
      }
      // endregion

      // region teleop
      @Override
      public void teleopInit() {
            m_robotContainer.getTeleopCommand().schedule();
      }

      @Override
      public void teleopPeriodic() {
      }

      @Override
      public void teleopExit() {
            m_robotContainer.getTeleopCommand().cancel();
      }
      // endregion

      // region simulation
      @Override
      public void simulationInit() {
      }

      @Override
      public void simulationPeriodic() {
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
