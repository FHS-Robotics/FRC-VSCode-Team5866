package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.utilities.AutoTrajectory;

/**
 * Magic numbers of the Robot.
 */
public final class Constants {
      public final static int kChArm = 5;
      public final static int kChElevator1 = 6;
      public final static int kChElevator2 = 7;
      public final static int kChIntake = 4;
      public final static SPI.Port kChGyro = SPI.Port.kOnboardCS0;
      public final static int kChWheelFL = 0;
      public final static int kChWheelFR = 1;
      public final static int kChWheelBL = 2;
      public final static int kChWheelBR = 3;
      public final static int kChLimitUp = 0;

      public final static double kDistancePerPulse = (2048 * 3.75) * (1 / (.1524 * Math.PI));
      public final static double kSpeedIntake = 0.3;
      public final static double kSpeedArm = 0.3;
      public final static double kSpeedElevator = .25;

      public final static boolean kInvertEncoderLeft = false;
      public final static boolean kInvertEncoderRight = true;
      public final static boolean kInvertLimitUp = true;

      /**
       * Seconds to spend running intake during autonomous.
       */
      public final static double kAutoActionTime = 1;
      public final static AutoTrajectory[] kAutoTrajectories = new AutoTrajectory[] {
        };

      // TODO: Acquire the gains of the robot.
      // https://docs.wpilib.org/en/stable/docs/software/pathplanning/trajectory-tutorial/entering-constants.html
      public final static double kRamseteB = 0;
      public final static double kRamseteZeta = 0;
      public final static double kPDriveVel = 0;
      // TODO: measure track width.
      public final static DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(1);
      public final static SimpleMotorFeedforward kAutoFeedforward = new SimpleMotorFeedforward(0, 0, 0);
      public final static DifferentialDriveVoltageConstraint kAutoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(kAutoFeedforward, kDriveKinematics, 10);
}
