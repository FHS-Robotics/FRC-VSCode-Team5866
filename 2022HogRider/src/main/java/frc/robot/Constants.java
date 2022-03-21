package frc.robot;

import java.util.List;
import java.util.Map;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.utilities.AutoStrategy;
import frc.robot.utilities.AutoStrategy.Action;
import static frc.robot.utilities.AutoStrategy.Action.ActionType.*;

/**
 * Magic numbers of the Robot.
 *
 * <p>
 * Found here are:
 * </p>
 *
 * <ul>
 * <li>Channels of Motor Controllers, Gyros and a Limit Switch</li>
 * <li>Meters per drive train encoder pulse</li>
 * <li>Max speed of Intake, Arm and Elevator</li>
 * <li>Encoder and Limit Switch Invert Settings</li>
 * <li>Autonomous trajectories</li>
 * <li>Robot Gains</li>
 * </ul>
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

      /** Wheel Diameter in Meters */
      public final static double kWheelDiameter = 0.1524;
      public final static double kDrivePulsePerRotation = 2048;
      public final static double kDriveGearReduction = 3.75;
      public final static double kDistancePerRotation = kWheelDiameter * Math.PI;
      public final static double kPulsePerDistance = kDrivePulsePerRotation * kDriveGearReduction / kDistancePerRotation;
      public final static double kSpeedIntake = 0.3;
      public final static double kSpeedArm = 0.3;
      public final static double kSpeedElevator = 0.75;

      public final static boolean kInvertEncoderLeft = false;
      public final static boolean kInvertEncoderRight = true;
      public final static boolean kInvertLimitUp = true;

      /**
       * Seconds to spend running intake during autonomous.
       */
      public final static double kAutoActionTime = 1;
      /**
       * The String key of this map is referenced in the "auto_strategy" setting.
       * The AutoTrajectory hold the file names of trajectory files and the
       * actions to perform throughout the autonomous routine.
       */
      public final static Map<String, AutoStrategy> kAutoStrategies = Map.of(
            "BlueBottom", new AutoStrategy(
                  List.of("FirstBall.wpilib.json", "SecondBall.wpilib.json"),
                  new Action(DispenseBall, -1.2),
                  new Action(ArmDown, 1),
                  new Action(IntakeBall, 2),
                  new Action(ArmUp, 3),
                  new Action(DispenseBall, 4),
                  new Action(ArmDown, 5),
                  new Action(IntakeBall, 6),
                  new Action(ArmUp, 7),
                  new Action(DispenseBall, 8)
            )
      );

      // TODO: Acquire the gains of the robot.
      // https://docs.wpilib.org/en/stable/docs/software/pathplanning/trajectory-tutorial/entering-constants.html
      public final static double kRamseteB = 2;
      public final static double kRamseteZeta = 0.7;
      public final static double kP = 10;
      public final static double kI = 0.0;
      public final static double kD = 0.1;
      // TODO: measure track width.
      public final static DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(1);
      public final static SimpleMotorFeedforward kAutoFeedforward = new SimpleMotorFeedforward(0, 0, 0);
}
