/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargodelivery;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.vision.VisionManager;

public class FindTargets extends Command {

  Timer timer;

  public FindTargets() {}


  @Override
  protected void initialize() 
  {
    timer = new Timer();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(timer.hasPeriodPassed(.5))
    {
      VisionManager.FindTargets();
      
      if(VisionManager.targets[0] != null)
        SmartDashboard.putString("Target 1: ", VisionManager.targets[0].toString());

      if(VisionManager.targets[1] != null)
      SmartDashboard.putString("Target 2: ", VisionManager.targets[1].toString());

      timer.reset();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    timer.stop();
  }

  @Override
  protected void interrupted() {}
}
