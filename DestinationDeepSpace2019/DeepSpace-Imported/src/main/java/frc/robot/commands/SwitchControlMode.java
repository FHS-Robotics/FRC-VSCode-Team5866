/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OI;

/**
 * Switch driving control to the xbox controller and the elevator to the flight sticks
 */
public class SwitchControlMode extends InstantCommand {

  public SwitchControlMode() {}

  @Override
  protected void initialize() {
    OI.mode = !OI.mode; //switch boolean
  }

}
