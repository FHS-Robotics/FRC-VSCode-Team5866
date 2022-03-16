package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

import static frc.robot.Constants.*;

/**
 * Controls the elevator on the robot.
 */
public final class Elevator extends SubsystemBase {
      private final MotorController m_motor;

      public Elevator(MotorController motor) {
            m_motor = motor;
      }


      /**
       * Moves the elevator, stopping when amount is too close to zero.
       * Positive amounts go upwards.
       *
       * @param amount the percent to multiply by Settings.ELEVATOR_SPEED()
       */
      public void move(double amount) {
            amount = amount * kSpeedElevator;
            Debugging.put("elevator_current_speed", amount);
            if(Math.abs(amount) > 0.05) {
                  Debugging.put("elevator_brakes_on", "No");
                  m_motor.set(amount);
            } else {
                  Debugging.put("elevator_brakes_on", "Yes");
                  m_motor.set(0);
            }
      }
}
