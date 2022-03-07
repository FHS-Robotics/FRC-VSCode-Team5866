package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class Debugging {
      public static void put(String key, String value) {
            SmartDashboard.putString("debug/" + key, value);
      }

      public static void put(String key, int value) {
            SmartDashboard.putNumber("debug/" + key, value);
      }

      public static void put(String key, double value) {
            SmartDashboard.putNumber("debug/" + key, value);
      }
}
