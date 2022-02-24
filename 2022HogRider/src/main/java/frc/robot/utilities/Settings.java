package frc.robot.utilities;

import edu.wpi.first.wpilibj.Preferences;
import frc.robot.commands.TeleOpDrive;
import frc.robot.commands.autonomous.ShootBalls;

/**
 * The settings of the robot that can update realtime by users with ShuffleBoard.
 */
public final class Settings {
      public static void init() {
            initInteger("debug_level", 0);
            initDouble("intake_speed", 0.3);
            initDouble("arm_speed", 0.01);
            initDouble("elevator_speed", 1);
            initDouble("auto_travel_distance", 1);
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

      public static double AUTO_TRAVEL_DISTANCE() {
            return getDouble("auto_travel_distance", 1);
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
