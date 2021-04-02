package frc.robot.Commands;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Subsystems.DriveBase;

public class TeleOpDrive extends CommandBase {

    DriveBase m_drive;

    /**
     * Creates a new TeleOpDrive.1
     */
    public TeleOpDrive(SubsystemBase requirement) { addRequirements(requirement);}
    

    @Override
    public void initialize() {
        m_drive = RobotMap.m_drive;
        super.initialize();
    }

    @Override
    public void execute() {
        double xSpeed = -OI.m_driverControl.getRawAxis(OI.c_driveForwardAxis) * 0.75;
        double zRotation = OI.m_driverControl.getRawAxis(OI.c_driveRotateAxis) * 0.75;

        // dead spot
        xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0;
        zRotation = Math.abs(zRotation) > 0.1 ? zRotation : 0;

        m_drive.arcadeDrive(xSpeed, zRotation);
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
