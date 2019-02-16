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

  public DoubleSolenoid clawPistons; //the solenoid connected to the claw pistons
  public DoubleSolenoid wristPiston;

  @Override
  public void initDefaultCommand() {  }

  public Claw(DoubleSolenoid _claw,  DoubleSolenoid _wrist)
  {
    clawPistons = _claw;
    wristPiston = _wrist;
    clawPistons.set(DoubleSolenoid.Value.kOff);
    wristPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void open() {
    clawPistons.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void close() {
    clawPistons.set(DoubleSolenoid.Value.kForward);
  }

  public void raise(){
    wristPiston.set(DoubleSolenoid.Value.kReverse);
  }

  public void lower(){
    wristPiston.set(DoubleSolenoid.Value.kForward);
  }
}
