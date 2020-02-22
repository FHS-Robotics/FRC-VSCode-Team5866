/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climber;

/**
 * Switches the direction of the hook pistons
 */
public class ActivateHooks extends CommandBase {

  private Climber climber;

  public ActivateHooks() {}

  @Override
  public void initialize() {
    climber = RobotMap.m_climber;
    climber.switchExtention();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  //The command will end immediately
  @Override
  public boolean isFinished() {
    return true;
  }
}
