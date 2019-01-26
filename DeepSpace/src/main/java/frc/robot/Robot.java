/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.TeleOpDrive;

import frc.robot.commands.SetLEDColor;
import frc.robot.commands.ultrasonic.*;

import frc.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

/**
 * This class is at the highest level and initializes all of our subsystems, RobotMap, commands, etc
 */
public class Robot extends TimedRobot {

  Timer timer; //for ultrasonic sensor
  double refreshRate = .5; //for ultrasonic

  public static UsbCamera main;

  @Override
  public void robotInit() {
    RobotMap.init();

    timer = new Timer(); //initialize timer for the ultrasonic reading
    timer.start();

    main = CameraServer.getInstance().startAutomaticCapture(); //start camera server
    main.setResolution(310, 240); //set resolution of camera
  }

  @Override
  public void teleopInit() {
    SmartDashboard.putNumber("Joystick Sensitivity", OI.sensitivity); //display current joystick sensitivity to the dashboard
  }

  @Override
  public void teleopPeriodic() {
    //get range of ultrasonic sensor every x seconds
    if(timer.get() > refreshRate)
    {
      Command range = new GetRange(RobotMap.ultraSonicFront);
      range.start();
      timer.reset();
    }
    Command teleOp = new TeleOpDrive();
    teleOp.start();

    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousPeriodic(){
    Scheduler.getInstance().run();
  }

}
