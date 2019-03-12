/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.PID;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

/**
 * This command turns our robot to a specified point using a PID loop with the NavX sensor
 */
public class TurnRobot extends Command {
 
  public double angle;

  public TurnRobot(double _angle) {
    requires(RobotMap.pidDriveBase);
    angle = _angle;
  }

  protected void initialize() {

    RobotMap.pidDriveBase.enable();
    RobotMap.pidDriveBase.setSetpoint(-angle);
  }

  protected void execute() {}

  protected boolean isFinished() {
    return RobotMap.pidDriveBase.onTarget();
  } 

  protected void end() {
    RobotMap.pidDriveBase.disable();
  }

  protected void interrupted() {
    end();
  }

}
