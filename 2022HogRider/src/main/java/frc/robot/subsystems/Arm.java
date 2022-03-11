package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;

import static frc.robot.Constants.*;

/**
 * Runs the robot's arm.
 */
public final class Arm extends SubsystemBase {
      private final MotorController m_arm;
      /**
       * A limit switch that is triggered once the arm moves
       * too far upwards.
       * 
       * "get()" calls return true when limit isn't hit...
       * Pretty confusing.
       */
      private final DigitalInput m_limitUp;

      public Arm(MotorController arm, DigitalInput limitUp) {
            m_arm = arm;
            m_limitUp = limitUp;
      }

      /**
       * Moves the arm safely, braking the motors when a limit switch is triggered.
       *
       * @param amount the percent to multiply by Settings.ARM_SPEED()
       */
      public void moveSafely(double amount) {
            amount = amount * kSpeedArm;

            if (amount < .1 && amount > -.1)
                  amount = 0;

            Debugging.put("arm_limit_up", kInvertLimitUp ^ m_limitUp.get() ? "Yes" : "No");
            Debugging.put("arm_current_speed", amount);

            // The "^" operator does an X-OR returning "true" when
            // the booleans are different values.
            // In other words, when kInvertLimitUp is true,
            // m_limitUp.get() is inverted.
            boolean hitLimit = kInvertLimitUp ^ m_limitUp.get() && amount >= 0;
            if (hitLimit && Settings.get("arm_limits_enabled", true)) {
                  Debugging.put("arm_brakes_on", "Yes");
                  m_arm.set(0);
            } else {
                  Debugging.put("arm_brakes_on", "No");
                  m_arm.set(amount);
            }
      }
}
