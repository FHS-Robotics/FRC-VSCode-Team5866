/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.TeleOpDrive;
import frc.robot.commands.TeleOpLift;
import frc.robot.commands.SetLEDModeAuto;
import frc.robot.commands.SetLEDModeManual;
import frc.robot.commands.ultrasonic.*;
import frc.robot.subsystems.LEDInterface.ColorMode;
import frc.robot.vision.VisionManager;
import frc.robot.RobotMap;

import java.text.DecimalFormat;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

/**
 * This class is at the highest level and initializes all of our subsystems, RobotMap, OI, commands, etc
 */
public class Robot extends TimedRobot {

   //for ultrasonic sensor and navX
  private Timer timer;
  private double refreshRate = .5;

  public OI m_oi;
  public static UsbCamera main;
  public static UsbCamera backCam;


  @Override
  public void robotInit() 
  {
    RobotMap.init();
    m_oi = new OI();
    VisionManager.init();

    try {
      IronDashboardWASD.run();
      } catch (Exception e) {
      System.out.println("Iron Dashboard connection not started");
      System.out.print(e);
  }

    timer = new Timer(); //initialize timer for the ultrasonic reading
    timer.start();

    //make sure the navX sensor is reset
    RobotMap.navX.reset();
    RobotMap.navX.resetDisplacement();

  
    try {
      main = CameraServer.getInstance().startAutomaticCapture(0); //start camera server
      main.setResolution(310, 240); //set resolution of camera
    } catch (Exception e) {
      System.out.println("Warning: Camera(Main) is not available and setResolution() was not run!");
    }

    try {
      backCam = CameraServer.getInstance().startAutomaticCapture(1); //start camera server
      backCam.setResolution(310, 240); //set resolution of camera
    } catch (Exception e) {
      System.out.println("Warning: Camera(backCam) is not available and setResolution() was not run!");
    }

    //set our led color
    Command setAutoLED = new SetLEDModeAuto();
    setAutoLED.start();

    Scheduler.getInstance().run();

    /*Thread t = new Thread(){
      public void run()
      {
        while(true)
        try {
          System.out.println(OI.ironDashboard.getMessage());
        } catch (Exception e) { 
          System.out.println("not connected");}
      }
    };

    t.start();*/
  }

  @Override
  public void teleopInit() 
  {
    //start lift command
    Command lift = new TeleOpLift();
    lift.start();

    //set our led color
    Command setAutoLED = new SetLEDModeAuto();
    setAutoLED.start();

    Command teleOp = new TeleOpDrive();
    teleOp.start();

    /*start finding targets
    Command findTargets = new FindTargetsPeriodic();
    findTargets.start();*/
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() 
  {
    //Display Sensors on a loop
    if(timer.hasPeriodPassed(refreshRate))
    {
      DisplaySensors();
      timer.reset();
    }
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousPeriodic(){
    teleopPeriodic(); //just do the same stuff as teleopPeriodic
  }

  @Override
  public void autonomousInit()
  {
    teleopInit();
  }


  @Override
  public void disabledInit() {
    Command led = new SetLEDModeManual(ColorMode.neutral);
    led.start();
    Scheduler.getInstance().run();
  }

  //get range of ultrasonic sensor  and rotation of the navX every x seconds
  private void DisplaySensors()
  {
    Command range = new GetRange(RobotMap.ultraSonicFront);
      
    //put the current position of the navX sensor to the dashboard
    AHRS navX = RobotMap.navX;
    DecimalFormat df = new DecimalFormat("###.###"); //set format layout
    SmartDashboard.putString("Robot X Pos: ", df.format(navX.getDisplacementX()));
    SmartDashboard.putString("Robot Y Pos: ", df.format(navX.getDisplacementY()));
      
    SmartDashboard.putNumber("Robot Yaw: ", navX.getYaw());
    /*Shuffleboard.getTab("SmartDashboard")
    .add("NavX", RobotMap.navX.getYaw())
    .withWidget(BuiltInWidgets.kGyro) // specify the widget here
    .getEntry();*/
    SmartDashboard.putNumber("NavX", RobotMap.navX.getYaw());
    range.start();
  }

}
