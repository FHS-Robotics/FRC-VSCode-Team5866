/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * System of pistons to elevate the robot's base
 */
public class RobotLifter extends Subsystem {
  DoubleSolenoid liftPistons;

  public RobotLifter(DoubleSolenoid _pistons)
  {
    liftPistons = _pistons;
  }

  @Override
  public void initDefaultCommand() {}

  public void lift()
  {
    liftPistons.set(DoubleSolenoid.Value.kForward);
  }

  public void lower()
  {
    liftPistons.set(DoubleSolenoid.Value.kReverse);
  }

  public void release()
  {
    liftPistons.set(DoubleSolenoid.Value.kOff);
  }
}
