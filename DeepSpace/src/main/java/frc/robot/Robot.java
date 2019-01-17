/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ultrasonicSensor.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

/**
 * This class is at the highest level and initializes all of our subsystems, RobotMap, commands, etc
 */
public class Robot extends TimedRobot {


  double xSensitivity = 100; //try to stick within 0-100 range 100 being max sensitivity and 0 being no response at all

  @Override
  public void robotInit() {
    RobotMap.init();

    /*UsbCamera mainCam = new UsbCamera("MainCamera", 0);
    mainCam.setFPS(15);
    mainCam.setResolution(320, 240); //width and height
    CameraServer.getInstance().startAutomaticCapture(mainCam); //start camera server*/
    CameraServer.getInstance().startAutomaticCapture(); //start camera server
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    Command command = new GetRange(); //
    command.start();
  }

  @Override
  public void autonomousPeriodic(){
    Scheduler.getInstance().run();
  }

}
