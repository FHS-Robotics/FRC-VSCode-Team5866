/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber extends SubsystemBase {

  private Spark m_pivot;
  private DoubleSolenoid m_extender;
  private boolean extended;

  /**
   * Creates a new Climber.
   */
  public Climber(int motor_port, int piston_port1, int piston_port2) {
    m_pivot = new Spark(motor_port);
    m_extender = new DoubleSolenoid(piston_port1, piston_port2); 
    extended = false;
    m_extender.set(Value.kReverse);
  }

  /**
   * Unfold the arm
   */
  public void unfold() {
    m_pivot.set(0.5);
  }

  /**
   * fold the arm
   */
  public void fold() {
    m_pivot.set(-0.5);
  }

  /**
   * release the motor folding the arm
   */
  public void release() {
    m_pivot.set(0);
  }

  /**
   * extend or retract the hook
   */
  public void switchExtention() {
    extended = !extended;
    m_extender.set(extended ? Value.kForward : Value.kReverse); //forward if extended, backward if not
  }
}
