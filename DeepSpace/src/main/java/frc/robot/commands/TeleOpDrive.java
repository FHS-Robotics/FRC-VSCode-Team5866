/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * This command controls the movement of the tank drive through the operator's joysticks
 */
public class TeleOpDrive extends Command {
  public TeleOpDrive() {
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      //take the y axis values from each joystick and send them to the swervedrive
      RobotMap.driveBase.tankDrive(-OI.m_leftStick.getY(), -OI.m_rightStick.getY());
  }

//#region Unused Methods
  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
  }
  //#endregion
}
