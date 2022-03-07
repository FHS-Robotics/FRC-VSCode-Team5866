package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;

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

            if (amount < .1 && amount > -.1)
                  amount = 0;
            
            Debugging.put("arm_limit_up", RobotMap.limitUp.get() ? "Yes" : "No");
            Debugging.put("arm_current_speed", amount);
            if (!RobotMap.limitUp.get() && amount >= 0 && Settings.ARM_LIMITS_ENABLED()) {
                  Debugging.put("arm_brakes_on", "Yes");
                  m_arm.set(0);
            } else {
                  Debugging.put("arm_brakes_on", "No");
                  m_arm.set(amount);
            }
      }
}
