package frc.robot.commands.autonomous;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveForward<TMotor extends MotorController & IMotorController> extends CommandBase {
      private final Drive<TMotor> m_drive;
      private final double m_speed;

      public DriveForward(Drive<TMotor> drive, double speed) {
            m_drive = drive;
            m_speed = speed;
      }

      @Override
      public void execute() {
            m_drive.getDrive().arcadeDrive(m_speed, 0);
      }
}
           




