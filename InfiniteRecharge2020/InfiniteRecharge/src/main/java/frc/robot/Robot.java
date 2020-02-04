/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.TeleOpDrive;

/**
 * The Robot class is the master class of the entire project
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default Cross Line";
  private static final String kBallAuto = "Shoot 3 balls";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static UsbCamera cam1;

  OI m_oi;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kBallAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //init our robot map and oi classes
    RobotMap.init();
    m_oi = new OI();

    //start the camera and set its resolution
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam1.setFPS(30);
    cam1.setResolution(310, 240);
    CameraServer.getInstance().startAutomaticCapture(cam1);
  }


  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Gyro Yaw", RobotMap.gyro.getYaw());
    RobotMap.m_ledStrip.rainbow();
  }


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    
    CommandScheduler.getInstance().run();
  }


  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kBallAuto:
        // Put custom auto code here.  This code will include putting into effect the shooting commands
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }

    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    CommandBase teleOpDrive = new TeleOpDrive();
    teleOpDrive.schedule();
    CommandScheduler.getInstance().run();
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
}
