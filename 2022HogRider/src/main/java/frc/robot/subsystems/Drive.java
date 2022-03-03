package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Debugging.Message;

/**
 * Controls robot movement.
 * WARNING: Depends on phoenix's IMotorController
 * 
 * @see IMotorController
 */
public final class Drive extends SubsystemBase {
      /*private TalonFX m_frontLeft_1; //falcon motor
      private CANSparkMax m_whatever; //neo motor

      private VictorSPX m_victor; //victor spx*/

      private DifferentialDrive m_drive;

      public Drive(MotorController fl, MotorController fr, MotorController bl, MotorController br) {
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
            if(Math.abs(xSpeed) > 0.05) {
                  Debugging.sendRepeating(Message.DriveSetAmount, 1, "Driving Drive Motors at spd: " + xSpeed + ", rot: " + zRotation);
                  m_drive.arcadeDrive(xSpeed, zRotation);
            } else {
                  Debugging.sendRepeating(Message.DriveSetAmount, 1, "Breaking Drive Motors NOW; Would have set at spd: " + xSpeed + ", rot: " + zRotation);
                  m_drive.stopMotor();
            }
      }

      public DifferentialDrive getDrive() {
            return m_drive;
      }
}
