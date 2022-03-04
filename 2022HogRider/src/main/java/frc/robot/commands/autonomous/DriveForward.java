package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveForward extends CommandBase {
      private final Drive m_drive;
      private final double m_speed;

      public DriveForward(Drive drive, double speed) {
            m_drive = drive;
            m_speed = speed;
      }

      @Override
      public void execute() {
            m_drive.arcadeDrive(m_speed, 0);
      }
}
           




