package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

import static frc.robot.Constants.*;

/**
 * Moves balls in and out of the robot.
 */
public final class Intake extends SubsystemBase {
      private final MotorController m_motor;

      public Intake(MotorController motor) {
            m_motor = motor;
      }

      /**
       * Runs the intake, stopping when amount is too close to zero.
       * Positive amounts takes balls in.
       *
       * @param amount the percent to multiply by Settings.INTAKE_SPEED()
       */
      public void move(double amount) {
            amount = amount * kSpeedIntake;
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
