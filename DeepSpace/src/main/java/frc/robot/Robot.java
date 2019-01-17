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
import frc.robot.commands.ultrasonic.*;

import java.sql.Time;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

/**
 * This class is at the highest level and initializes all of our subsystems, RobotMap, commands, etc
 */
public class Robot extends TimedRobot {

  double timer; //for ultrasonic sensor
  double refreshRate = 5; //for ultrasonic

  @Override
  public void robotInit() {
    RobotMap.init();
    timer = 0;

    /*UsbCamera mainCam = new UsbCamera("MainCamera", 0);
    mainCam.setFPS(15);
    mainCam.setResolution(320, 240); //width and height
    CameraServer.getInstance().startAutomaticCapture(mainCam); //start camera server*/
    CameraServer.getInstance().startAutomaticCapture(); //start camera server
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    if(timer >= refreshRate)
    {
      Command range = new GetRange();
      range.start();
      timer = 0;
    }
    else
    {
      timer += .02; //since we are using TimedRobot(), every frame will be 20 milliseconds
    }
  }

  @Override
  public void autonomousPeriodic(){
    Scheduler.getInstance().run();
  }

}
