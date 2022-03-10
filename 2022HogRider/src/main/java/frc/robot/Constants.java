package frc.robot;

/**
 * The settings of the robot that can update realtime by users with ShuffleBoard.
 */
public final class Constants {
      public final static int kChArm = 5;
      public final static int kChElevator1 = 6;
      public final static int kChElevator2 = 7;
      public final static int kChIntake = 4;
      public final static int kChWheelFL = 0;
      public final static int kChWheelFR = 1;
      public final static int kChWheelBL = 2;
      public final static int kChWheelBR = 3;
      public final static int kChLimitUp = 0;

      public final static double kEncoderToMeterFactor = (2048 * 3.75) * (1 / (.1524 * Math.PI));
      public final static double kIntakeSpeed = 0.3;
      public final static double kArmSpeed = 0.3;
      public final static double kElevatorSpeed = .25;

      public final static boolean kInvertLimitUp = true;
}
