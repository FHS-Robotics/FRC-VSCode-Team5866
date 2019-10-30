package org.usfirst.frc.team5866.robot.subsystems;

import org.usfirst.frc.team5866.robot.RobotMap;
import org.usfirst.frc.team5866.robot.commands.LiftController;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


public class liftSystem extends Subsystem {

    
    public liftSystem() {
    	
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new LiftController());
    }
    
    public void move(double speed) {
    	//RobotMap.liftMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void move() {
    	//RobotMap.liftMotor.set(ControlMode.PercentOutput, -.7);
    }
    
    public void holdSteady() {
    	////RobotMap.liftMotor.set(-.4);
    }
    
    public void stop() {
    	//RobotMap.liftMotor.set(ControlMode.PercentOutput, 0);
    }
}

