package frc.robot.utilities;

import java.util.ArrayList;
import java.util.List;

public final class AutoStrategy {
      public static final class Action implements Comparable<Action> {
            public final ActionType type;
            public final double startTime;

            public enum ActionType {
                  DispenseBall, // Drop ball
                  IntakeBall, // Pickup ball
                  ArmUp, // Lift Arm
                  ArmDown, // Lower Arm
                  NoOp,
            }

            public Action(ActionType type, double startTime) {
                  this.type = type;
                  this.startTime = startTime;
            }

          @Override
            public int compareTo(Action other) {
                  return Double.compare(startTime, other.startTime);
            }
      }

      /**
       * Filename of the trajectory JSON file.
       */
      public final List<String> files;
      /**
       * List of actions, sorted by time ascending.
       */
      private final List<Action> actions;

      public AutoStrategy(List<String> files, Action ...actions) {
            this.files = files;
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
      public Action sampleNearestAction(double time) {
            Action result = null;
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
       * first action
       *
       * @return first action's start time in seconds.
       */
      public double getFirstActionTime() {
            if (this.actions.size() == 0) {
                  return 0;
            }

            return this.actions.get(0).startTime;
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
