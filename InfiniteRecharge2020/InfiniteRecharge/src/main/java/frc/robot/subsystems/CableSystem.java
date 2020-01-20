/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class CableSystem extends CommandBase {
  /**
   * Creates a new CableSystem.
   */
  TeleHook m_TeleCables;
  public CableSystem(boolean up) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotMap.m_TeleCables);
    if(up){
      m_TeleCables.up();
    }
    else{
      m_TeleCables.down();
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_TeleCables = RobotMap.m_TeleCables;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_TeleCables.releaseCable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
