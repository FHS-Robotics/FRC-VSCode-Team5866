package frc.robot.Commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Subsystems.Shooter;

public class ChangeShootSpeed extends Command {
    
    public double speed = 0.75;
    Shooter m_shooter;
    NetworkTableEntry tableEntry;

    public ChangeShootSpeed() {
        m_shooter = RobotMap.m_shooter;
        tableEntry = Shuffleboard.getTab("Bridge")
        .add("shooting speed", m_shooter.shootForward)
        .getEntry();
        tableEntry.setNumber(RobotMap.m_shooter.shootForward);
    }

    @Override
    public void execute() {
        double value = -OI.m_gunnerControl.getRawAxis(5) / 50.0;
        m_shooter.shootForward += value;

        /*if(m_shooter.shootForward > 1)
            m_shooter.shootForward = 1;
        else if (m_shooter.shootForward < -1)
            m_shooter.shootForward = -1;*/

        tableEntry.setNumber(RobotMap.m_shooter.shootForward);
        System.out.println("shooting value changed to" + RobotMap.m_shooter.shootForward);
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}