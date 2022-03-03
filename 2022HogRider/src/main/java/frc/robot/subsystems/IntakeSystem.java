package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;

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
            amount = amount * Settings.INTAKE_SPEED();
            if(Math.abs(amount) > 0.05) {
                  Debugging.sendRepeating(Message.IntakeSetAmount, 1, "Running Intake at " + amount);
                  m_motor.set(amount);
            } else {
                  Debugging.sendRepeating(Message.IntakeSetAmount, 1, "Breaking Intake NOW; Would have set at " + amount);
                  m_motor.set(0);
            }
      }
}
