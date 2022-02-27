// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.autonomous.DriveForward;
import frc.robot.commands.autonomous.LowerArm;
import frc.robot.commands.autonomous.ShootBalls;
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
            Debugging.info("CH_ARM: " + Settings.CH_ARM());
            Debugging.info("CH_ELEVATOR: " + Settings.CH_ELEVATOR());  
            Debugging.info("CH_INTAKE: " + Settings.CH_INTAKE());
            Debugging.info("CH_W_FL: " + Settings.CH_W_FL());
            Debugging.info("CH_W_FR: " + Settings.CH_W_FR());
            Debugging.info("CH_W_BL: " + Settings.CH_W_BL());
            Debugging.info("CH_W_BR: " + Settings.CH_W_BR());
            Debugging.info("Smart Drive Scaling Factor: " + Settings.DRIVE_SMART_SCALING_FACTOR());
      }

      @Override
      public void robotPeriodic() {
            RobotMap.m_arm.periodic();
            CommandScheduler.getInstance().run();
      }
      // endregion

      // region autonomous
      private Command m_currentAuto;
      @Override
      public void autonomousInit() {
            RobotMap.m_drive.prepareForAutonomous();
            m_currentAuto = new DriveForward<WPI_TalonFX>(RobotMap.m_drive, Settings.AUTO_TRAVEL_DISTANCE()).withTimeout(5)
                  .andThen(new ShootBalls(RobotMap.m_intake))
                  .andThen(
                        new ParallelCommandGroup(
                              new DriveForward<WPI_TalonFX>(RobotMap.m_drive, Settings.AUTO_TRAVEL_DISTANCE()).withTimeout(5),
                              new LowerArm(RobotMap.m_arm)
                        )
                  );
            m_currentAuto.schedule();
      }

      @Override
      public void autonomousPeriodic() {
      }

      @Override
      public void autonomousExit() {
            if (m_currentAuto != null) {
                  m_currentAuto.cancel();
            }
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
            // Drive 2 Phoenix-Units
            RobotMap.m_drive.smartDrive(2);
      }

      @Override
      public void testPeriodic() {
      }

      @Override
      public void testExit() {
      }
      // endregion
}
