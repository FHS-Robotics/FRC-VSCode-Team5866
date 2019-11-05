/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.robotLifter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Lower the robot's back end off the ground via lift piston(s)
 */
public class LowerRobot extends Command {

  public LowerRobot() {
    requires(RobotMap.robotLifter);
  }

  @Override
  protected void execute() {
    RobotMap.robotLifter.lower();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putString("Base Lift Pistons State", "Lowered"); //publish state to Shuffleboard
    return RobotMap.baseLiftPistons.get().equals(DoubleSolenoid.Value.kForward); //if it's forward than it's being closed
  }
}
