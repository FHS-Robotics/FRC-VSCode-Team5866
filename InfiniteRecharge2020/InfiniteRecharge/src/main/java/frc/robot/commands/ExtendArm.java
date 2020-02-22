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

public class ExtendArm extends CommandBase {

  Climber climber;
  boolean foldOrUnfold;

  public ExtendArm(boolean _foldOrUnfold) {
    foldOrUnfold = _foldOrUnfold;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber = RobotMap.m_climber;
    if(foldOrUnfold) {
      climber.fold();
    }
    else {
      climber.unfold();
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    climber.release(); //release the climber after the command is ended
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
