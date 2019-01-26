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


/**
 * This command controls the movement of the tank drive through the operator's joysticks
 */
public class TeleOpDrive extends Command {

  double sensitivity;

  public TeleOpDrive() {
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() 
  {
    //the joystick values will be modified by the sensitivity of the Joysticks
    sensitivity = OI.sensitivity / 5; //get sensitivity from OI and divide it by 5 to scale it down, so mode 10 will be 2x speed and mode 5 will be normal speed

    //take the y axis values from each joystick and send them to the swervedrive
    RobotMap.driveBase.tankDrive(-OI.m_leftStick.getY() * sensitivity, -OI.m_rightStick.getY() * sensitivity);
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
