package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.TeleOpDrive;

public class DriveBase extends Subsystem {

    @Override
    protected void initDefaultCommand() {
        new TeleOpDrive();
    }
    
    
}


//write code for pathfinding and constants