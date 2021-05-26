package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class Elevate extends Command{
    
    boolean direction;

    public Elevate(boolean d) {
        direction = d;
        requires(RobotMap.m_elevator);
    }
    
    @Override
    protected void execute() {
        if(direction)
            RobotMap.m_elevator.raise();
        else
            RobotMap.m_elevator.lower();
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end() {
        RobotMap.m_elevator.release();
    }
}
