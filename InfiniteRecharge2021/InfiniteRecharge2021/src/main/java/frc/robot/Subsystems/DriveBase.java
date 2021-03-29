package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Commands.TeleOpDrive;

public class DriveBase extends Subsystem {

    public DifferentialDrive m_drive;

    @Override
    protected void initDefaultCommand() {
        new TeleOpDrive();
    }

    public DriveBase(DifferentialDrive d) {
        m_drive = d;
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        m_drive.arcadeDrive(xSpeed, zRotation);
    }
    
    
}


//write code for pathfinding and constants