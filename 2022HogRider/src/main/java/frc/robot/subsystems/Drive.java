package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.autonomous.DriveForward;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;

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

      /**
       * Drive for a certain distance.
       * Useful for autonomous code.
       * 
       * @param distance meters to straightly travel
       * @see DriveForward
       */
      public void smartDrive(double distance) {
            distance = distance * Settings.DRIVE_SMART_SCALING_FACTOR();
            m_frontLeft.set(ControlMode.Position, distance);
            m_frontRight.set(ControlMode.Position, distance);
            m_backLeft.set(ControlMode.Position, distance);
            m_backRight.set(ControlMode.Position, distance);
      }

      public boolean isSmartDriveFinished() {
            double positionFromFinish = Math.abs(m_frontLeft.getClosedLoopTarget(0));
            Debugging.sendRepeating(
                  Message.SmartDriveProgress, // Message Category
                  1,                          // Message Repeat Period (seconds)
                  "Current position from finish: " + positionFromFinish
            );
            return positionFromFinish < 0.1;
      }
}
