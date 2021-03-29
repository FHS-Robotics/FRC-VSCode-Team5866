package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;

/**
 * Switches the state of the limelight led
 */
public class SwitchLimelight extends InstantCommand {
    
    @Override
    protected void initialize() {
        if(RobotMap.limeLight.getLed())
            RobotMap.limeLight.ledOff();
        else
            RobotMap.limeLight.ledOn();
    }
}
