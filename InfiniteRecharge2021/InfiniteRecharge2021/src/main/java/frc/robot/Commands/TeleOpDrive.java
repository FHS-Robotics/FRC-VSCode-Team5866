package frc.robot.Commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.RobotMap;

public class TeleOpDrive extends Command {

    DifferentialDrive m_drive;

    /**
   * Creates a new TeleOpDrive.1
   */
  public TeleOpDrive() {
  }

    @Override
    protected void initialize() {
        m_drive = RobotMap.m_drive;
        super.initialize();
    }

    @Override
    protected void execute() {
        double xSpeed = -OI.m_driverControl.getRawAxis(1) * 0.75; 
        double zRotation = OI.m_driverControl.getRawAxis(4) * 0.75;
        
        //dead spot
        xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0;
        zRotation = Math.abs(zRotation) > 0.1 ? zRotation : 0;
        
        m_drive.arcadeDrive(xSpeed, zRotation);

        super.execute();
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
