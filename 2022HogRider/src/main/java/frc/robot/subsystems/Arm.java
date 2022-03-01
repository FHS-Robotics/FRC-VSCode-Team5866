package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;

/**
 * Runs the robot's arm. Requires a WPI_TalonFX because
 * of it's integrated sensors.
 */
public final class Arm extends SubsystemBase {
      private final WPI_TalonFX m_arm;
      private final TalonFXSensorCollection m_sensor;

      public Arm(WPI_TalonFX arm) {
            m_arm = arm;
            m_sensor = m_arm.getSensorCollection();
            zeroSensor();
      }

      public void moveUp() {
            double amount = Settings.ARM_SPEED();
            Debugging.sendRepeating(Message.ArmSetAmount, 1, "Driving Arm Motor at " + amount);
            m_arm.set(amount);
      }

      public void moveDown() {
            double amount = -Settings.ARM_SPEED();
            Debugging.sendRepeating(Message.ArmSetAmount, 1, "Driving Arm Motor at " + amount);
            m_arm.set(amount);
      }

      public void stopArm() {
            m_arm.set(0);
      }

      public void zeroSensor() {
            m_sensor.setIntegratedSensorPosition(0, 0);
      }

      @Override
      public void periodic() {
            Debugging.sendRepeating(Message.ArmSensorPosition, 3, "Arm Sensor: " + m_sensor.getIntegratedSensorPosition());
      }
}
