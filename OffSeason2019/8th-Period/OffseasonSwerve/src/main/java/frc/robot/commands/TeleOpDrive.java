/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;

public class TeleOpDrive extends Command {
  
  public TeleOpDrive() { }

  @Override
  protected void initialize() {
    RobotMap.m_drive.enable();
  }

  @Override
  protected void execute() {

    double x1 = OI.driverController.getRawAxis(0);
    double y1 = OI.driverController.getRawAxis(1);
    double x2 = OI.driverController.getRawAxis(4);

    System.out.println(x1);
    
    RobotMap.m_drive.drive(x1, y1, x2);
    //RobotMap.m_drive.setSetpoint(0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}