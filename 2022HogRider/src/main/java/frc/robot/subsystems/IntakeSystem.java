package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

import static frc.robot.Constants.*;

/**
 * Encapsulates all to do with taking in balls.
 */
public final class IntakeSystem extends SubsystemBase {
      private final MotorController m_motor;

      public IntakeSystem(MotorController motor) {
            m_motor = motor;
      }

      /**
       * Runs the intake, stopping when amount is too close to zero.
       *
       * @param amount the percent to multiply by Settings.INTAKE_SPEED()
       */
      public void move(double amount) {
            amount = amount * kIntakeSpeed;
            Debugging.put("intake_current_speed", amount);
            if(Math.abs(amount) > 0.05) {
                  Debugging.put("intake_brakes_on", "No");
                  m_motor.set(amount);
            } else {
                  Debugging.put("intake_brakes_on", "Yes");
                  m_motor.set(0);
            }
      }
}
