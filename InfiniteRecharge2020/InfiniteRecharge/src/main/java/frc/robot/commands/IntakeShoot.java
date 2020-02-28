/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Shoot.mode;
import frc.robot.subsystems.Intake;

public class IntakeShoot extends CommandBase {

  Shoot shoot;
  IntakeSystem intake;
  double shooterChargeUpTime = 2;

  Timer timer = new Timer();

  public IntakeShoot() {
    shoot = new Shoot(mode.Auto);
    intake = new IntakeSystem(true);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shoot.schedule();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get() >= shooterChargeUpTime) {
      intake.schedule();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shoot.end(false);
    intake.end(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (shoot.isFinished());
  }
}
