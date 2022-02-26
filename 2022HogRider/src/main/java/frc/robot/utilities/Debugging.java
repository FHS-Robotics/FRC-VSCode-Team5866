package frc.robot.utilities;

public final class Debugging {
      private enum Level {
            Critical(1),
            Error(2),
            Info(3),
            Debug(4);
            
            public final int value;

            Level(int value) {
                  this.value = value;
            }
      }

      public static void critical(String message) {
            log(Level.Critical, message);
      }

      public static void error(String message) {
            log(Level.Error, message);
      }

      public static void info(String message) {
            log(Level.Info, message);
      }

      public static void debug(String message) {
            log(Level.Debug, message);
      }

      private static void log(Level level, String message) {
            if (Settings.LOG_LEVEL() >= level.value) {
                  System.out.println("[" + level.name() + "] " + message);
            }
      }
}
