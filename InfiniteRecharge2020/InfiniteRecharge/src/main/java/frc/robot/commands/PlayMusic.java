/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

/**
 * Plays music using the Falcon Motors
 */
public class PlayMusic extends CommandBase {

  public PlayMusic(){
    addRequirements(RobotMap.m_drive);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotMap.m_backLeft.set(TalonFXControlMode.MusicTone, 0);
    RobotMap.m_frontLeft.set(TalonFXControlMode.MusicTone, 0);
    RobotMap.m_backRight.set(TalonFXControlMode.MusicTone, 0);
    RobotMap.m_frontRight.set(TalonFXControlMode.MusicTone, 0);

    RobotMap.orchestra.loadMusic("ImperialMarch3.chrp");

    RobotMap.orchestra.addInstrument(RobotMap.m_frontLeft);
    RobotMap.orchestra.addInstrument(RobotMap.m_backLeft);
    RobotMap.orchestra.addInstrument(RobotMap.m_frontRight);
    RobotMap.orchestra.addInstrument(RobotMap.m_backRight);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotMap.orchestra.play();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !RobotMap.orchestra.isPlaying();
  }
}
