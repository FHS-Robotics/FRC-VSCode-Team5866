package frc.robot.utilities;

import java.util.HashMap;
import java.util.HashSet;

import edu.wpi.first.wpilibj.Timer;

public final class Debugging {
      private enum Level {
            Warning(0),
            Info(1),
            Debug(2);
            
            public final int value;

            Level(int value) {
                  this.value = value;
            }
      }

      public enum Message {
            SmartDriveProgress(Level.Debug),
            DriverUnplugged(Level.Warning),
            GunnerUnplugged(Level.Warning);

            private final Level level;

            Message(Level level) {
                  this.level = level;
            }
      }

      // region sendRepeating
      private static HashMap<Message, Double> messageTimeMap = new HashMap<Message, Double>();

      /**
       * Logs a message that doesn't bog down the logs.
       * Messages wait to be sent after some time has passed from the
       * last message being sent. 
       * 
       * @param m The message category to repeatedly send
       * @param period The time to wait between sending messages of the category
       * @param text The text to log if the period constraint was met
       */
      public static void sendRepeating(Message m, double period, String text) {
            double time = Timer.getFPGATimestamp();

            if (messageTimeMap.containsKey(m) && time < messageTimeMap.get(m) + period) {
                  return;
            }

            messageTimeMap.put(m, time);
            log(m.level, text);
      }
      // endregion

      // region sendOnce
      private static HashSet<Message> sendOnceSet = new HashSet<Message>();

      /**
       * Sends a message, so that repeated calls of sendOnce don't send additional messages.
       *
       * @param m The category of message
       * @param text The text to send if this message category hasn't been sent before.
       */
      public static void sendOnce(Message m, String text) {
            if (sendOnceSet.contains(m)) {
                  return;
            }

            sendOnceSet.add(m);
            log(m.level, text);
            debug(sendOnceSet.toString());
      }

      /**
       * Allows calls of sendOnce with the message category to work again for one time.
       *
       * @param m The category of message to unblock
       */
      public static void resetSendOnce(Message m) {
            if (sendOnceSet.remove(m)) {
                  debug("resetSendOnce(" + m.toString() + ")");
            }
      }
      // endregion

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
