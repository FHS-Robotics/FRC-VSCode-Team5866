package main.java.frc.robot.commands;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import main.java.frc.robot.subsystems.WristSystem;
import frc.robot.OI;

public class WristController{
    public WristController(){
        requires(RobotMap.WristSystem);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        //64:1 small small numbers
        RobotMap.WristSystem.move(OI.m_rightStick.getX());//right stick controls wrist may need to be changed
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}