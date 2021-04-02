package frc.robot.Commands;

import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class Intake extends Command {

    boolean direction;

    public Intake(boolean _direction) {
        direction = _direction;
    }

    @Override
    protected void execute() {
        RobotMap.m_intake.intake(direction);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end() {
        RobotMap.m_intake.release();
    }
    
}