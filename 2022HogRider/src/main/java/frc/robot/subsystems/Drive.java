package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Controls robot movement.
 * WARNING: Depends on phoenix's IMotorController
 * 
 * @see IMotorController
 */
public final class Drive<TMotor extends MotorController & IMotorController> extends SubsystemBase {
      private TMotor m_frontLeft;
      private TMotor m_frontRight;
      private TMotor m_backLeft;
      private TMotor m_backRight;

      private DifferentialDrive m_drive;

      public Drive(TMotor fl, TMotor fr, TMotor bl, TMotor br) {
            m_frontLeft = fl;
            m_frontRight = fr;
            m_backLeft = bl;
            m_backRight = br;

            m_drive = new DifferentialDrive(
                  new MotorControllerGroup(fl, bl),
                  new MotorControllerGroup(fr, br)
            );
      }

      /**
       * This method prepares for autonomous mode.
       * Used by AutonomousDrive.initialize()
       * 
       * @see AutonomousDrive
       */
      public void prepareForAutonomous() {
            m_frontLeft.setInverted(false);
            m_frontLeft.setSelectedSensorPosition(0, 0, 0);
            m_frontRight.setInverted(true);
            m_frontRight.setSelectedSensorPosition(0, 0, 0);
            m_backLeft.setInverted(false);
            m_backLeft.setSelectedSensorPosition(0, 0, 0);
            m_backRight.setInverted(true);
            m_backRight.setSelectedSensorPosition(0, 0, 0);
      }

      public DifferentialDrive getDrive() {
            return m_drive;
      }

      public void arcadeDriveSmart(double speed, double direction) {
      }
}
