package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
      private final WPI_TalonFX m_left, m_right;
      private final Gyro m_gyro;
      private final DifferentialDrive m_drive;

      private final DifferentialDriveOdometry m_odometry;

      /**
       * Creates a new drive train.
       * @param left The left motor controller which other controllers follow().
       * @param right The left motor controller which other controllers follow()
       * @param gyro The gyro to orient the robot with.
       */
      public Drive(WPI_TalonFX left, WPI_TalonFX right, Gyro gyro) {
            m_left = left;
            m_right = right;
            m_gyro = gyro;

            m_drive = new DifferentialDrive(
                  left,
                  right
            );

            m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
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

      public void tankDriveVolts(double leftVolts, double rightVolts) {
            m_left.setVoltage(leftVolts);
            m_right.setVoltage(rightVolts);
            m_drive.feed();
      }

      public Pose2d getPose() {
            return m_odometry.getPoseMeters();
      }

      public DifferentialDriveWheelSpeeds getWheelSpeeds() {
            return new DifferentialDriveWheelSpeeds(m_left.getSelectedSensorVelocity() * kDistancePerPulse, m_right.getSelectedSensorVelocity() * kDistancePerPulse);
      }

      public void resetOdometry(Pose2d pose) {
            m_left.setSelectedSensorPosition(0);
            m_right.setSelectedSensorPosition(0);
            m_odometry.resetPosition(pose, m_gyro.getRotation2d());
      }

      public double getAverageEncoderDistance() {
            return (m_left.getSelectedSensorPosition() * kDistancePerPulse + m_right.getSelectedSensorPosition() * kDistancePerPulse) / 2.0;
      }

      public DifferentialDrive getDrive() {
            return m_drive;
      }

      @Override
      public void periodic() {
            m_odometry.update(
                  m_gyro.getRotation2d(),
                  m_left.getSelectedSensorPosition() * kDistancePerPulse,
                  m_right.getSelectedSensorPosition() * kDistancePerPulse
            );
      }
}
