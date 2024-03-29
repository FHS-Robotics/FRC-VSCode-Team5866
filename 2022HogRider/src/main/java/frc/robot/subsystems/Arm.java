package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;

import static frc.robot.Constants.*;

/**
 * Robot Arm, a motor controller and a limit switch for
 * it's high point.
 */
public final class Arm extends SubsystemBase {
      private final MotorController m_arm;
      /**
       * A limit switch that is triggered once the arm moves
       * too far upwards.
       */
      private final DigitalInput m_limitUp;

      public Arm(MotorController arm, DigitalInput limitUp) {
            m_arm = arm;
            m_limitUp = limitUp;
      }

      /**
       * Moves the arm safely, braking the motors when a limit switch is triggered.
       * Positive amounts go upwards.
       *
       * @param amount the percent to multiply by Settings.ARM_SPEED()
       */
      public void moveSafely(double amount) {
            amount = amount * kSpeedArm;

            if (amount < .1 && amount > -.1)
                  amount = 0;

            Debugging.put("arm_limit_up", limitUpHit() ? "Yes" : "No");
            Debugging.put("arm_current_speed", amount);

            boolean hitLimit = limitUpHit() && amount >= 0;
            if (hitLimit && Settings.get("arm_limits_enabled", true)) {
                  Debugging.put("arm_brakes_on", "Yes");
                  m_arm.set(0);
            } else {
                  Debugging.put("arm_brakes_on", "No");
                  m_arm.set(amount);
            }
      }

      public boolean limitUpHit() {
            // The "^" operator does an X-OR. Normally
            // when we get a true signal, the limit is hit.
            // But if the limit switch is inverted the
            // true signal becomes false, meaning we
            // haven't reached the limit.
            return kInvertLimitUp ^ m_limitUp.get();
      }
}
