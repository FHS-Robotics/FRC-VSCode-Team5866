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
 * Add your docs here.
 */
public class Claw extends Subsystem {

  public DoubleSolenoid clawPiston;
  public DoubleSolenoid ballPushPiston;

  @Override
  public void initDefaultCommand() {  }

  public Claw(DoubleSolenoid _claw, DoubleSolenoid _ballPush)
  {
    clawPiston = _claw;
    ballPushPiston = _ballPush;
  }
  
  public void close() {
    clawPiston.set(DoubleSolenoid.Value.kForward);
  }
  
  public void open() {
    clawPiston.set(DoubleSolenoid.Value.kReverse);
  }
}
