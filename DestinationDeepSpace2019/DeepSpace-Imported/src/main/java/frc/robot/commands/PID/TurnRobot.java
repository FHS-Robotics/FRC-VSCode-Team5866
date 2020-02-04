/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.PID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * This command turns our robot to a specified point using a PID loop with the NavX sensor
 */
public class TurnRobot extends Command {
 
  public double angle;
  private Timer t;

  private double timeToEnd = 3; //time in seconds before the command should just end (this is a safeguard in case the pid fails)

  public TurnRobot(double _angle) {
    requires(RobotMap.pidDriveBase);
    angle = _angle;
  }

  protected void initialize() {

    RobotMap.pidDriveBase.enable();
    RobotMap.pidDriveBase.setSetpoint(angle);
    OI.setPIDActive(true);
    SmartDashboard.putBoolean("PID Driving", true);

    t = new Timer();
    t.reset();
    t.start();
  }

  protected void execute() {}

  protected boolean isFinished() {
    if(RobotMap.pidDriveBase.onTarget() || t.hasPeriodPassed(timeToEnd))
      return true;
    return false;
  } 

  protected void end() {
    OI.setPIDActive(false);
    SmartDashboard.putBoolean("PID Driving", false);

    t.stop();
    RobotMap.pidDriveBase.disable();
  }

  protected void interrupted() {
    end();
  }

}
