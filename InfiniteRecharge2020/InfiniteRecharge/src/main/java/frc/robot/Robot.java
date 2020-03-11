/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.music.Orchestra;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.PlayMusic;
import frc.robot.commands.auto.DriveForTime;
import frc.robot.commands.auto.StillShotMove;

/**
 * The Robot class is the master class of the entire project
 */
public class Robot extends TimedRobot {

  private static final String kDefaultAuto = "Default Cross Line";
  private static final String kBallAuto = "Shoot 3 balls";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static UsbCamera cam1;
  public static UsbCamera cam2;

  OI m_oi;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kBallAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //init our robot map and oi classes
    RobotMap.init();
    m_oi = new OI();
    OI.SetDefaultCommands(); //set all default commands for subsystems

    //start the camera and set its resolution
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam1.setFPS(30);
    cam1.setResolution(310, 240);
    CameraServer.getInstance().startAutomaticCapture(cam1);

    //start the camera and set its resolution
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam2.setFPS(30);
    cam2.setResolution(310, 240);
    CameraServer.getInstance().startAutomaticCapture(cam2);
  }


  @Override
  public void robotPeriodic() {
    OI.PublishData();
    if(DriverStation.getInstance().getAlliance() == Alliance.Red){
      RobotMap.m_ledStrip.setRGB(255, 0, 0);
      //RobotMap.m_ledStrip2.setRGB(255, 0, 0);
      //RobotMap.m_ledStrip.redGold();
    }
    else if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
      //RobotMap.m_ledStrip.setRGB(0, 0, 255);
      RobotMap.m_ledStrip.blueGold();
      //RobotMap.m_ledStrip2.setRGB(0, 0, 255);
    }
    else {
      RobotMap.m_ledStrip.setRGB(255, 255, 0);
      //RobotMap.m_ledStrip2.setRGB(255, 255, 0);
    }
    //RobotMap.m_ledStrip.rainbow();

    double a2 = RobotMap.limeLight.getY(); //set a2 to the angle of the target in the y
    SmartDashboard.putNumber("Distance", ((87 - 14.5) / Math.tan((29 + a2) * (3.14159/180))));
  }


  @Override
  public void autonomousInit() {
    //PlayMusic music = new PlayMusic();
    //music.schedule();
    RobotMap.limeLight.ledOff();
    RobotMap.gyro.reset();

    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    switch (m_autoSelected) {
      case kBallAuto:
        StillShotMove move = new StillShotMove(1.0);
        move.schedule();
        break;
      case kDefaultAuto:
      default:
        DriveForTime drive = new DriveForTime(0, -0.25, 0, 0.5);
        drive.schedule();
        break;
    }
    CommandScheduler.getInstance().run();
  }


  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    System.out.println(RobotMap.orchestra.play());
    RobotMap.limeLight.ledOff();
    /*CommandBase teleOpDrive = new TeleOpDrive();
    teleOpDrive.schedule();
    CommandScheduler.getInstance().run();*/
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledPeriodic() {
    //RobotMap.limeLight.ledOff();
  }
}
