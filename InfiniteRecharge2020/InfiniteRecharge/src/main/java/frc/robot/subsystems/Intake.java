/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 

public class Intake extends SubsystemBase {
  
  PWMTalonSRX m_intakeMotor;
  double intakeSpeed;
  /**
   * Creates a new Intake.
   */
  public Intake(int port, double speed) {
    m_intakeMotor = new PWMTalonSRX(port);
    intakeSpeed = speed;
  }
  public void setForward(){
    m_intakeMotor.set(intakeSpeed);
  }
  public void setReverse(){
    m_intakeMotor.set(-intakeSpeed);
  }
  public void release(){
    m_intakeMotor.set(0);
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
