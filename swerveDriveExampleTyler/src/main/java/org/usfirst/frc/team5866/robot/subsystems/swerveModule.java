package org.usfirst.frc.team5866.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class swerveModule extends Subsystem {
    private PIDController pidController;
    private SpeedController speedMotor;
    private final double MAX_VOLTS = 4.987;
    private double setpoint;

	
	public swerveModule (SpeedController speedMotor, PIDController controller) {
	    this.speedMotor = speedMotor;
	    pidController = controller;
	}

	public void drive (double speed, double angle) {
	    if(pidController.getError() < .05)
	    	speedMotor.set (speed);

	    double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
	    if (setpoint < 0) {
	        setpoint = MAX_VOLTS + setpoint;
	    }
	    if (setpoint > MAX_VOLTS) {
	        setpoint = setpoint - MAX_VOLTS;
	    }
	    pidController.setSetpoint (setpoint);
	}
	
	public void autoDrive(double speed, double angle) {
		speedMotor.set(speed);
		pidController.setSetpoint(angle);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
