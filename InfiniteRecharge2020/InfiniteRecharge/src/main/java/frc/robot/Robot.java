/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Robot class is the master class of the entire project
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default Cross Line";
  private static final String kBallAuto = "Shoot 3 balls";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kBallAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }


  @Override
  public void robotPeriodic() {
  }


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
