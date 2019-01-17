/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ultrasonic;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.UltrasonicSensor;

import frc.robot.RobotMap;

public class GetRange extends Command {
  
  
  public GetRange() {
    requires(RobotMap.ultraSonicFront);
  }

  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double range = RobotMap.ultraSonicFront.GetRangeInInches();
    System.out.println(range); //print the range
  }

  @Override
  protected boolean isFinished() {
    return true; //the command will execute once
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
