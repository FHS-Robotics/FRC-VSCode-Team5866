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
 * Close the claw
 */
public class CloseClaw extends Command {

  public CloseClaw() {
    requires(RobotMap.m_claw);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    RobotMap.m_claw.close();
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putString("Claw State", "Closed"); //publish state to Shuffleboard
    return RobotMap.clawPistons.get().equals(DoubleSolenoid.Value.kReverse); //if it's reverse than it's being opened
  }

}
