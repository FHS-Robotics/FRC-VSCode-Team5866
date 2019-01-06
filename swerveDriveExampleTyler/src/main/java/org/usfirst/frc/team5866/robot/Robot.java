package org.usfirst.frc.team5866.robot;

import org.usfirst.frc.team5866.robot.commands.SidewaysLeftTrySwitch;
import org.usfirst.frc.team5866.robot.commands.SidewaysTryRightSwitch;
import org.usfirst.frc.team5866.robot.commands.StraightLeft;
import org.usfirst.frc.team5866.robot.commands.StraightNoSwitch;
import org.usfirst.frc.team5866.robot.commands.StraightRight;
import org.usfirst.frc.team5866.robot.commands.SwitchAuto;
import org.usfirst.frc.team5866.robot.subsystems.swerveDrive;
import org.usfirst.frc.team5866.robot.subsystems.swerveModule;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	//Making some changes to code for Git Example
	//Trying to make commit without push
	//Trying the previous again and again one more time
	//Adding some code 
	//Some change here 2
	
	public static OI m_oi;
	public static UsbCamera main;
	public static SendableChooser<Command> chooser;
	public static SendableChooser<Integer> chooserN;
	public static Command StraightRightTrySwitch, CenterSwitch, StraightLeftTrySwitch, SNS, SidewaysRight, SidewaysLeft;
	
	@Override
	public void robotInit() {
		RobotMap.init();
		m_oi = new OI();
		main = CameraServer.getInstance().startAutomaticCapture();
		main.setResolution(640, 480);
//		chooser = new SendableChooser<>();
		chooserN = new SendableChooser<>();
//		StraightRightTrySwitch = new StraightRight();
//		StraightLeftTrySwitch = new StraightLeft();
//		CenterSwitch = new SwitchAuto();
//		SNS = new StraightNoSwitch();
//		SidewaysLeft = new SidewaysLeftTrySwitch();
//		SidewaysRight = new SidewaysTryRightSwitch();
		chooserN.addDefault("Straight", 3);
		chooserN.addObject("Straight Right Try Switch", 0);
		chooserN.addObject("Center Switch", 1);
		chooserN.addObject("Straight Left try Switch", 2);
		chooserN.addObject("Sideways Try Right Switch", 4);
		chooserN.addObject("Sideways Try Left Switch", 5);
		SmartDashboard.putData(chooserN);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Command autoCommand;
		if(chooserN.getSelected() == 0)
			autoCommand = new StraightRight();
		else if(chooserN.getSelected() == 1)
			autoCommand = new StraightLeft();
		else if(chooserN.getSelected() == 2)
			autoCommand = new SwitchAuto();
		else if(chooserN.getSelected() == 3)
			autoCommand =  new StraightNoSwitch();
		else if(chooserN.getSelected() == 4)
			autoCommand =  new SidewaysLeftTrySwitch();
		else if(chooserN.getSelected() == 5)
			autoCommand = new SidewaysTryRightSwitch();
		else
			autoCommand = null;
//		Command autoCommand = chooser.getSelected();
		autoCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		
	}
}
