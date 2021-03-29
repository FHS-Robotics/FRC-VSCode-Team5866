package frc.robot.Commands;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Subsystems.PIDTurret;

public class TeleOpTurret extends Command {

    JoystickButton startButton;
    double speed;

    public TeleOpTurret() {
        requires(RobotMap.m_turret);
        startButton = new JoystickButton(OI.m_gunnerControl, OI.c_start);
        RobotMap.m_turret.enable();
    }

    @Override
    protected void execute() {

        if(startButton.get()) {
            speed = RobotMap.m_turret.speed;
        }
        else {
            speed = OI.m_gunnerControl.getRawAxis(0);
        }
        RobotMap.m_turret.rotate(speed);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}