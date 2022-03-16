package frc.robot.utilities;

import java.util.ArrayList;
import java.util.List;

public final class AutoTrajectory {
      public static final class AutoAction implements Comparable<AutoAction> {
            public final Type type;
            public final double startTime;

            public enum Type {
                  DispenseBall, // Drop ball
                  IntakeBall, // Pickup ball
                  ArmUp, // Lift Arm
                  ArmDown, // Lower Arm
                  NoOp,
            }

            public AutoAction(Type type, double startTime) {
                  this.type = type;
                  this.startTime = startTime;
            }

          @Override
            public int compareTo(AutoAction other) {
                  return Double.compare(startTime, other.startTime);
            }
      }

      /**
       * Filename of the trajectory JSON file.
       */
      public final String file;
      /**
       * List of actions, sorted by time ascending.
       */
      private final List<AutoAction> actions;

      public AutoTrajectory(String file, AutoAction ...actions) {
            this.file = file;
            this.actions = new ArrayList<>(List.of(actions));
            this.actions.sort((a, b) -> a.compareTo(b));
      }

      /**
       * Gets the nearest action to time, or null if there are no actions
       * passed the given time.
       *
       * @param time
       * @return
       */
      public AutoAction sampleNearestAction(double time) {
            AutoAction result = null;
            for (var action : actions) {
                  if (time >= action.startTime) {
                        result = action;
                  } else {
                        break;
                  }
            }

            return result;
      }

      /**
       * Returns the start time of the trajectory's
       * last action
       *
       * @return last action's start time in seconds.
       */
      public double getLastActionTime() {
            if (this.actions.size() == 0) {
                  return 0;
            }

            return this.actions.get(this.actions.size() - 1).startTime;
      }
}
