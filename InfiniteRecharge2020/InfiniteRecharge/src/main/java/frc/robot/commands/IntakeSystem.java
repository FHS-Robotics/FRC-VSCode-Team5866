/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Intake;


public class IntakeSystem extends CommandBase {
  /**
   * Creates a new IntakeSystem.
   */
  Intake m_intake;
  public IntakeSystem(boolean forward) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotMap.m_intake);
    if(forward){
      m_intake.setForward();
    }
    else{
      m_intake.setReverse();
    }
  }

  

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake = RobotMap.m_intake;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.release();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
