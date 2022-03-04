// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.Debugging;
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
            Debugging.info("Logging Level: " + Settings.LOG_LEVEL());
            Debugging.info("---");

            Debugging.info("Channel Settings");
            Debugging.info("CH_ARM: " + Settings.CH_ARM());
            Debugging.info("CH_ELEVATOR1: " + Settings.CH_ELEVATOR1());  
            Debugging.info("CH_ELEVATOR2: " + Settings.CH_ELEVATOR2());  
            Debugging.info("CH_INTAKE: " + Settings.CH_INTAKE());
            Debugging.info("CH_W_FL: " + Settings.CH_W_FL());
            Debugging.info("CH_W_FR: " + Settings.CH_W_FR());
            Debugging.info("CH_W_BL: " + Settings.CH_W_BL());
            Debugging.info("CH_W_BR: " + Settings.CH_W_BR());
            Debugging.info("---");

            Debugging.info("Miscellaneous Settings");
            Debugging.info("intake_speed: " + Settings.INTAKE_SPEED());
            Debugging.info("arm_speed: " + Settings.ARM_SPEED());
            Debugging.info("elevator_speed: " + Settings.ELEVATOR_SPEED());
      }

      @Override
      public void robotPeriodic() {
            RobotMap.m_arm.periodic();
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
                  RobotMap.m_intake.move(-0.5);
            } else if (fromStart < 2 + 3) {
                  RobotMap.m_drive.arcadeDrive(-0.5, 0);
            } else if (fromStart < 2 + 3 + 2) {
                  RobotMap.m_arm.moveSafely(-1);
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
