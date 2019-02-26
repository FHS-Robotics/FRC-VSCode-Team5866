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

public class LiftRobot extends Command {
  
  public LiftRobot() {
    requires(RobotMap.robotLifter);
  }

  @Override
  protected void execute() {
    RobotMap.robotLifter.lift();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putString("Base Lift Pistons State", "Raised"); //publish state to Shuffleboard
    return RobotMap.baseLiftPistons.get().equals(DoubleSolenoid.Value.kReverse); //if it's forward than it's being closed
  }
}
