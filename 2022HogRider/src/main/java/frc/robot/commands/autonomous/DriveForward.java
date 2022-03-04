package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveForward extends CommandBase {
      private final Drive m_drive;
      private final double m_meters;

      public DriveForward(Drive drive, double meters) {
            m_drive = drive;
            m_meters = meters;
      }

      @Override
      public void initialize() {
          m_drive.zeroPosition();
      }

      @Override
      public void execute() {
            m_drive.driveMeters(m_meters);
      }
}
           




