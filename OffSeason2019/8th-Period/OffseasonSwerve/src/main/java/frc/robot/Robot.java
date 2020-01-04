/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TeleOpDrive;

public class Robot extends TimedRobot {

  //AnalogInput encoder0 = new AnalogInput(0);
  //AnalogInput encoder1 = new AnalogInput(1);
  //AnalogInput encoder2 = new AnalogInput(2);

  OI m_oi;

  @Override
  public void robotInit() {
    RobotMap.init(); //initialize all robot components
    m_oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    System.out.println(RobotMap.m_backLeft.getSetpoint());
    //System.out.println(RobotMap.m_backLeft.pidController.getSetpoint() + " " + RobotMap.m_backRight.pidController.getSetpoint());
    //System.out.println(encoder0.getValue() + " " + encoder1.getValue() + " " + encoder2.getValue());
  }

  @Override
  public void autonomousInit() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    TeleOpDrive teleopDrive = new TeleOpDrive();
    teleopDrive.start();
    System.out.println("starting drive");
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
