/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {

  Shooter shooter;
  Timer timer = new Timer();

  double cleartime = 1; //time to run backward to clear the balls

  public enum mode {Forward, Reverse};
  mode shootMode;

  public Shoot(mode _shootMode) {
    shootMode = _shootMode;
    shooter = RobotMap.m_shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(shootMode == mode.Forward)
    {
      if(timer.get() < cleartime) {
        RobotMap.shootTemp.set(-0.25);
      }
      else {
        //shooter.setRPM(5500); //set to
        RobotMap.shootTemp.set(1);
      }
    }
    else if(shootMode == mode.Reverse)
    {
      RobotMap.shootTemp.set(-0.25);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotMap.shootTemp.set(0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
