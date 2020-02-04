/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Lower the claw
 */
public class LowerClaw extends Command {

  public LowerClaw() {
    requires(RobotMap.m_claw);
  }
  
  @Override
  protected void execute() {
    RobotMap.m_claw.lower();
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putString("Wrist State", "Lowered"); //publish state to Shuffleboard
    return RobotMap.wristPiston.get().equals(DoubleSolenoid.Value.kReverse); //if it's reverse than it's being raised
  }
}
