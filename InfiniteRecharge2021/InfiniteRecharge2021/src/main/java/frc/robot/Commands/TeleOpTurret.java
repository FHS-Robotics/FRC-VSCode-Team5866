package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
public class TeleOpTurret extends Command {

    JoystickButton startButton;
    double speed;
    double modifier = 0.1;

    public TeleOpTurret() {
        requires(RobotMap.m_turret);
        startButton = new JoystickButton(OI.m_gunnerControl, OI.c_start);
        RobotMap.m_turret.enable();
    }

    @Override
    protected void initialize() {
        RobotMap.m_turret.enable();
    }

    @Override
    protected void execute() {
        if(RobotMap.m_turret.onTarget())
        {
            OI.m_gunnerControl.setRumble(RumbleType.kLeftRumble, 1);
            OI.m_gunnerControl.setRumble(RumbleType.kRightRumble, 1);
        }
        else {
            OI.m_gunnerControl.setRumble(RumbleType.kLeftRumble, 0);
            OI.m_gunnerControl.setRumble(RumbleType.kRightRumble, 0);
        }

        if(startButton.get()) {
            speed = RobotMap.m_turret.speed;
        }
        else {
            speed = OI.m_gunnerControl.getRawAxis(0) * modifier;
        }
        RobotMap.m_turret.rotate(speed);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}