/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Raise the claw
 */
public class RaiseClaw extends Command {

  public RaiseClaw() {
    requires(RobotMap.m_claw);
  }
  
  @Override
  protected void execute() {
    RobotMap.m_claw.raise();
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putString("Wrist State", "Raised"); //publish state to Shuffleboard
    return RobotMap.wristPiston.get().equals(DoubleSolenoid.Value.kReverse); //if it's forward than it's being lowered
  }
}
