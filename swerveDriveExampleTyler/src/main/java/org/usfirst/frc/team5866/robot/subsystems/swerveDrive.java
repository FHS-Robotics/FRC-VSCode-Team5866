package org.usfirst.frc.team5866.robot.subsystems;

import org.usfirst.frc.team5866.robot.RobotMap;
import org.usfirst.frc.team5866.robot.commands.TeleOpDrive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class swerveDrive extends Subsystem{
	private swerveModule backRight;
	private swerveModule backLeft;
	private swerveModule frontRight;
	private swerveModule frontLeft;

	public swerveDrive () {
			backRight = RobotMap.backRight;
			backLeft = RobotMap.backLeft;
			frontRight = RobotMap.frontRight;
			frontLeft = RobotMap.frontLeft;
	}
	
	public void drive(double x1, double y1, double x2/*, double slider*/) {
		final double L = 20.5; //LENGTH_YOU_WROTE;
		final double W = 23 ; //WIDTH_YOU_WROTE;
		double r = Math.sqrt ((L * L) + (W * W));
		y1 *= -1;
		x1 *= -1;
		x2 *= -.75;
		
		double a = x1 - x2 * (L / r);
	    double b = x1 + x2 * (L / r);
	    double c = y1 - x2 * (W / r);
	    double d = y1 + x2 * (W / r);


	    double backRightSpeed = Math.sqrt ((a * a) + (d * d))/* * Math.abs(slider)*/;
	    double backLeftSpeed = Math.sqrt ((a * a) + (c * c))/* * Math.abs(slider)*/;
	    double frontRightSpeed = Math.sqrt ((b * b) + (d * d))/* * Math.abs(slider)*/;
	    double frontLeftSpeed = Math.sqrt ((b * b) + (c * c))/* * Math.abs(slider)*/;

	    double backRightAngle = Math.atan2 (a, d) / Math.PI;
	    double backLeftAngle = Math.atan2 (a, c) / Math.PI;
	    double frontRightAngle = Math.atan2 (b, d) / Math.PI;
	    double frontLeftAngle = Math.atan2 (b, c) / Math.PI;
	    
//	    double a = x1 * Math.abs(x2);
//	    double b = y1 * Math.abs(x2);
//
//
//	    double backRightSpeed = Math.sqrt ((a * a) + (b * b));
//	    double backLeftSpeed = Math.sqrt ((a * a) + (b * b));
//	    double frontRightSpeed = Math.sqrt ((a * a) + (b * b));
//	    double frontLeftSpeed = Math.sqrt ((a * a) + (b * b));
//
//	    double backRightAngle = Math.atan2 (a, b) / Math.PI;
//	   // System.out.println(backRightAngle);
//	    double backLeftAngle = Math.atan2 (a, b) / Math.PI;
//	    double frontRightAngle = Math.atan2 (a, b) / Math.PI;
//	    double frontLeftAngle = Math.atan2 (a, b) / Math.PI;
	    
	    backRight.drive (backRightSpeed, backRightAngle);
	    backLeft.drive (backLeftSpeed, backLeftAngle);
	    frontRight.drive (frontRightSpeed, frontRightAngle);
	    frontLeft.drive (frontLeftSpeed, frontLeftAngle);
		
	}
	
	public void autoDrive(double angle, double speed) {
//		double a = angle;
		double a = angle * 4.987 / 360;
		backRight.autoDrive (speed, a);
	    backLeft.autoDrive  (speed, a);
	    frontRight.autoDrive  (speed, a);
	    frontLeft.autoDrive  (speed, a);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOpDrive());
	}
}
    

