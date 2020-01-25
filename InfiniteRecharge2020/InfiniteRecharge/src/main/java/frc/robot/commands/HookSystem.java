/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.TeleHook;

public class HookSystem extends CommandBase {
  /**
   * Creates a new HookSystem.
   */
  TeleHook m_leftTeleHook;
  TeleHook m_rightTeleHook;


  public HookSystem(boolean extend) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotMap.m_leftTeleHook);
    addRequirements(RobotMap.m_rightTeleHook);
    if(extend){
      m_leftTeleHook.extend();
    }
    else{
      m_leftTeleHook.retract();
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_leftTeleHook = RobotMap.m_leftTeleHook;
    m_rightTeleHook = RobotMap.m_rightTeleHook;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_leftTeleHook.releaseHook();
    m_rightTeleHook.releaseHook();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
