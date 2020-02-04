/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.LEDInterface.ColorMode;

/**
 * Set LED Color Manually
 */
public class SetLEDModeManual extends InstantCommand {

  ColorMode mode;

  /**
   * @param _mode : the mode(red, blue, neutral, showcase)
   */
  public SetLEDModeManual(ColorMode _mode) {
    requires(RobotMap.ledStrip);
    mode = _mode;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    
    RobotMap.ledStrip.setOutput(mode);
    SmartDashboard.putString("LED Mode", mode.toString()); //print our mode
  }

}
