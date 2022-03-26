// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.TeleopCommand;
import frc.robot.utilities.Settings;

/**
 * Welcome to 5866 Fe Iron Tiger's robot code for the year 2022,
 * <b>HogRider</b>!
 *
 * <p>
 * Under-the-hood we use the {@see CommandScheduler} to run the periodic()
 * methods of the robot's subsystems and to execute commands. When autonomous/teleop
 * begins and ends, we schedule and cancel the singleton-like commands stored
 * in {@see RobotContainer}.
 * </p>
 *
 * <p>
 * On how our robot is wired up, {@see RobotContainer} is responsible for
 * creating and configuring motor controllers and passing them to subsystems.
 * </p>
 *
 * <p>
 * Also noteworthy, {@see Settings} holds values that may be changed in
 * the middle of a match through a dashboard such as ShuffleBoard or SmartDashboard.
 * </p>
 *
 * <p>
 * Look through these classes to get started.
 * </p>
 *
 * @see RobotContainer
 * @see Settings
 * @see Constants
 * @see AutonomousCommand
 * @see TeleopCommand
 */
public final class Robot extends TimedRobot {
      private final RobotContainer m_robotContainer = new RobotContainer();

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
            var autoCmd = m_robotContainer.getAutonomousCommand();

            // IMPORTANT: We need to call resolveDelegate(), before
            // scheduling the command because auto cmd is a proxy to
            // a "real" command that is picked according to the
            // setting "auto_strategy" on the dashboard.
            autoCmd.resolveDelegate();
            autoCmd.schedule();
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

      // region disabled
      @Override
      public void disabledInit() {
      }

      @Override
      public void disabledPeriodic() {
      }

      @Override
      public void disabledExit() {
      }
      // endregion
}
