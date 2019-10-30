package org.usfirst.frc.team5866.robot.commands;

import org.usfirst.frc.team5866.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MoveForTime extends Command {
	double angle, speed, time;
	Timer a;
	
	public MoveForTime(double angle, double speed, double time) {
        requires(RobotMap.swerve);
        this.angle = angle;
        this.speed = speed;
        this.time = time;
        a = new Timer();
    }

    protected void initialize() {
    	a.reset();
    	a.start();
    	System.out.print("init");
    }

    protected void execute() {
    	RobotMap.swerve.autoDrive(angle, speed);
    	SmartDashboard.putNumber("Time", a.get());

    }

    protected boolean isFinished() {
        return a.hasPeriodPassed(time);
    }

    protected void end() {
    	RobotMap.swerve.autoDrive(0, 0);

    }

    protected void interrupted() {
    }
}
