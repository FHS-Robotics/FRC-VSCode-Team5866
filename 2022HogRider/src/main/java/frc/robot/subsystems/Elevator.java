package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Settings;

/**
 * Controls the elevator on the robot.
 */
public final class Elevator extends SubsystemBase {
      private final MotorController m_motor;

      public Elevator(MotorController motor) {
            m_motor = motor;
      }

      public void moveUp() {
            m_motor.set(Settings.ELEVATOR_SPEED());
      }

      public void moveDown() {
            m_motor.set(-Settings.ELEVATOR_SPEED());
      }

      public void stopElevator() {
            m_motor.set(0);
      }
}
