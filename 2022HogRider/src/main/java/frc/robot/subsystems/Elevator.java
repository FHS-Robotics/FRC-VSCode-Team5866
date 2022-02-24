package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import frc.robot.utilities.Settings;

/**
 * Controls the elevator on the robot.
 */
public final class Elevator {
      private final CANSparkMax m_motor;

      public Elevator(CANSparkMax motor) {
            m_motor = motor;
      }

      public void moveUp() {
            m_motor.set(Settings.ELEVATOR_SPEED());
      }

      public void moveDown() {
            m_motor.set(-Settings.ELEVATOR_SPEED());
      }
}
