package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;

/**
 * Runs the robot's arm.
 */
public final class Arm extends SubsystemBase {
      private final MotorController m_arm;

      public Arm(MotorController arm) {
            m_arm = arm;
      }

      /**
       * Moves the arm safely, braking the motors when a limit switch is triggered.
       *
       * @param amount the percent to multiply by Settings.ARM_SPEED()
       */
      public void moveSafely(double amount) {
            amount = amount * Settings.ARM_SPEED();

            boolean limitHit = (RobotMap.limitUp.get() && amount > 0) && Settings.ARM_LIMITS_ENABLED();
            if (limitHit) {
                  Debugging.sendRepeating(Message.ArmSetAmount, 1, "[!] Limit Hit--Stopping Arm; Would have set at " + amount);
                  m_arm.set(0);
            } else if(Math.abs(amount) < 0.05) {
                  Debugging.sendRepeating(Message.ArmSetAmount, 1, "[!] No Input--Stopping Arm; Would have set at " + amount);
                  m_arm.set(0);
            } else {
                  Debugging.sendRepeating(Message.ArmSetAmount, 1, "Driving Arm Motor at " + amount);
                  m_arm.set(amount);
            }
      }

      @Override
      public void periodic() {
            if (RobotMap.limitUp.get()) {
                  if (Settings.ARM_LIMITS_ENABLED()) {
                        Debugging.sendOnce(Message.ArmLimitMet, "Hit upper arm limit!");
                        m_arm.set(0);
                  } else {
                        Debugging.sendOnce(Message.ArmLimitMet, "Hit upper arm limit, but limits were disabled...");
                  }
            } else {
                  Debugging.resetSendOnce(Message.ArmLimitMet);
            }
      }
}
