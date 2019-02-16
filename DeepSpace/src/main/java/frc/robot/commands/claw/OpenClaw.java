/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Open the claw
 */
public class OpenClaw extends Command {

  public OpenClaw() {
    requires(RobotMap.m_claw);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    RobotMap.m_claw.open();
  }

  @Override
  protected boolean isFinished() {
    System.out.println(RobotMap.clawPistons.get());
    SmartDashboard.putString("Claw State", "Open"); //publish state to Shuffleboard
    return RobotMap.clawPistons.get().equals(DoubleSolenoid.Value.kForward); //if it's forward than it's being closed
  }
}
