package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

public class TeleOpArm extends CommandBase {

    ArmSubsystem arm;
    XboxController controller;

    /**
     * Constructor that creates a new command to operate the shoulder
     * @param m_arm
     */
    public TeleOpArm(ArmSubsystem m_arm) {
        arm = m_arm;
        controller = RobotContainer.m_opController;
        addRequirements(m_arm);
    }


    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }


    @Override
    public void execute() {
        
        arm.moveElbow(controller.getRightY() * ArmConstants.elbow_speed_multiplier);

        //DPAD UP
        if(controller.getPOV() == 0) {
            arm.moveShoulder(ArmConstants.shoulder_speed);
        }
        //DPAD DOWN
        else if(controller.getPOV() == 180) {
            arm.moveShoulder(-ArmConstants.shoulder_speed);
        }
        else {
            //arm.stopShoulder();
        }
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        
    }
    
}
