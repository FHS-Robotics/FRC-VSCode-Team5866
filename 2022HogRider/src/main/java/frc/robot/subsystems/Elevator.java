package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;

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
       *
       * @param amount the percent to multiply by Settings.ARM_SPEED()
       */
      public void move(double amount) {
            amount = amount * Settings.ELEVATOR_SPEED();
            if(Math.abs(amount) > 0.05) {
                  Debugging.sendRepeating(Message.ElevatorSetAmount, 1, "Driving Elevator Motor at " + amount);
                  m_motor.set(amount);
            } else {
                  Debugging.sendRepeating(Message.ElevatorSetAmount, 1, "Breaking Elevator Motor NOW; Would have set at " + amount);
                  m_motor.set(0);
            }
      }
}
