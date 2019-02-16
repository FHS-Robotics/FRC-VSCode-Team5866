/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.LEDInterface.ColorMode;

/**
 * Sets the color mode of the led strip based on the alliance
 */
public class SetLEDModeAuto extends InstantCommand {

  /**
   * Add your docs here.
   */
  public SetLEDModeAuto() {
    requires(RobotMap.ledStrip);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Alliance alliance = DriverStation.getInstance().getAlliance();
    if(alliance == Alliance.Red)
      RobotMap.ledStrip.setOutput(ColorMode.red);
    else if(alliance == Alliance.Blue)
    RobotMap.ledStrip.setOutput(ColorMode.blue);
    else
      RobotMap.ledStrip.setOutput(ColorMode.neutral);

    SmartDashboard.putString("LED Mode", alliance.toString()); //print what mode we are on
  }

}
