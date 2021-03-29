package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class Shoot extends Command{

    boolean direction;

    public Shoot(boolean d) {
        direction = d;
    }

    @Override
    protected void execute() {
        RobotMap.m_shooter.shoot(direction);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end() {
        RobotMap.m_shooter.release();;
    }
    
}
