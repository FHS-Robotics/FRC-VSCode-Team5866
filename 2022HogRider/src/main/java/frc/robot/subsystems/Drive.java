package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

import static frc.robot.Constants.*;

/**
 * Controls robot movement.
 * WARNING: Depends on phoenix's IMotorController
 * 
 * @see IMotorController
 */
public final class Drive extends SubsystemBase {
      private WPI_TalonFX m_fl, m_fr, m_bl, m_br;

      private DifferentialDrive m_drive;

      public Drive(WPI_TalonFX fl, WPI_TalonFX fr, WPI_TalonFX bl, WPI_TalonFX br) {
            m_fl = fl;
            m_fr = fr;
            m_bl = bl;
            m_br = br;

            m_drive = new DifferentialDrive(
                  new MotorControllerGroup(fl, bl),
                  new MotorControllerGroup(fr, br)
            );
      }

      /**
       * Arcade drives, stopping when xSpeed is too close to zero
       *
       * @param xSpeed driving speed
       * @param zRotation turning speed
       */
      public void arcadeDrive(double xSpeed, double zRotation) {
            Debugging.put("drive_speed", "spd: " + xSpeed + ", rot: " + zRotation);
            if(Math.abs(xSpeed) > 0.05) {
                  Debugging.put("drive_brakes_on", "No");

                  m_drive.arcadeDrive(xSpeed, zRotation);
            } else {
                  Debugging.put("drive_brakes_on", "Yes");

                  m_drive.stopMotor();
            }
      }

      public void zeroPosition() {
            m_fl.setSelectedSensorPosition(0);
            m_fr.setSelectedSensorPosition(0);
            m_bl.setSelectedSensorPosition(0);
            m_br.setSelectedSensorPosition(0);
      }

      public void driveMeters(double meters) {
            double sensorUnits = meters * kEncoderToMeterFactor;

            m_fl.set(ControlMode.Position, sensorUnits);
            m_fr.set(ControlMode.Position, sensorUnits);
            m_bl.set(ControlMode.Position, sensorUnits);
            m_br.set(ControlMode.Position, sensorUnits);

            // Differiential Drive has a built-in safety mechanism
            // that stops it's motors when it has not been updated recently
            //
            // feed() tells the MotorSafety that we have updated motor values.
            m_drive.feed();
      }

      public DifferentialDrive getDrive() {
            return m_drive;
      }
}
