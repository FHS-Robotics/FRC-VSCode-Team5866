package frc.robot.utilities;

import edu.wpi.first.wpilibj.Preferences;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpDrive;
import frc.robot.commands.autonomous.ShootBalls;

/**
 * The settings of the robot that can update realtime by users with ShuffleBoard.
 */
public final class Settings {
      public static void init() {
            // Debugging
            initInteger("debug_level", 2);

            // Channels
            initInteger("ch_arm", 5);
            initInteger("ch_elevator1", 6);
            initInteger("ch_elevator2", 7);
            initInteger("ch_intake", 4);
            initInteger("ch_w_fl", 0);
            initInteger("ch_w_fr", 1);
            initInteger("ch_w_bl", 2);
            initInteger("ch_w_br", 3);

            // Miscellaneous
            initDouble("intake_speed", 0.3);
            initDouble("arm_speed", 0.01);
            initDouble("elevator_speed", 1);
            // (units per wheel rotation) = (units per rotation) * (gear ratio)
            // (wheel rotations per meter) = 1 / (wheel circumference in meters)
            // (units per wheel rotation) * (wheel rotations per meter)
            initDouble("drive_smart_scaling_factor", (2048 * 3.75) * (1 / (.1524 * Math.PI)));
            initDouble("auto_travel_speed", 0.4);
      }

      /**
       * This is the log level of the robot.
       * 
       * @see Debugging
       */
      public static int LOG_LEVEL() {
            return getInteger("debug_level", 0);
      }

      /**
       * Channel of the Arm.
       * 
       * @see RobotMap
       */
      public static int CH_ARM() {
            return getInteger("ch_arm", 0);
      }

      /**
       * Channel of the Elevator.
       */
      public static int CH_ELEVATOR1() {
            return getInteger("ch_e1levator1", 0);
      }

      /**
       * Channel of the Elevator.
       */
      public static int CH_ELEVATOR2() {
            return getInteger("ch_elevator2", 0);
      }

      /**
       * Channel of the Intake.
       * 
       * @see RobotMap
       */
      public static int CH_INTAKE() {
            return getInteger("ch_intake", 0);
      }

      /**
       * Channel of the Front Left Wheel.
       * 
       * @see RobotMap
       */
      public static int CH_W_FL() {
            return getInteger("ch_w_fl", 0);
      }

      /**
       * Channel of the Front Right Wheel.
       * 
       * @see RobotMap
       */
      public static int CH_W_FR() {
            return getInteger("ch_w_fr", 1);
      }

      /**
       * Channel of the Back Left Wheel.
       * 
       * @see RobotMap
       */
      public static int CH_W_BL() {
            return getInteger("ch_w_bl", 2);
      }

      /**
       * Channel of the Back Right Wheel.
       * 
       * @see RobotMap
       */
      public static int CH_W_BR() {
            return getInteger("ch_w_br", 3);
      }

      /**
       * This method controls the speed at which the Intake runs.
       * 
       * @see TeleOpDrive
       * @see ShootBalls
       */
      public static double INTAKE_SPEED() {
            return getDouble("intake_speed", 0.3);
      }

      /**
       * This method controls the speed at which the Arm runs.
       * 
       * @see TeleOpDrive
       */
      public static double ARM_SPEED() {
            return getDouble("arm_speed", 0.01);
      }

      /**
       * This method controls the speed at which the Elevator runs.
       * 
       * @see TeleOpDrive
       */
      public static double ELEVATOR_SPEED() {
            return getDouble("elevator_speed", 1);
      }

      /**
       * The factor that converts meters to motor units.
       * 
       * @see Drive
       */
      public static double DRIVE_SMART_SCALING_FACTOR() {
            return getDouble("drive_smart_scaling_factor", 1);
      }

      public static double AUTO_TRAVEL_SPEED() {
            return getDouble("auto_travel_speed", 1);
      }

      // Past this point are helper methods used internally by

      private static void initDouble(String key, double value) {
            if (!Preferences.containsKey(key)) {
                  Preferences.setDouble(key, value);
            }
      }

      private static void initInteger(String key, int value) {
            if (!Preferences.containsKey(key)) {
                  Preferences.setInt(key, value);
            }
      }

      private static double getDouble(String key, double defaultValue) {
            return Preferences.getDouble(key, defaultValue);
      }

      private static int getInteger(String key, int defaultValue) {
            return Preferences.getInt(key, defaultValue);
      }
}
