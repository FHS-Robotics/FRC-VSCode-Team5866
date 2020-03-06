/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
 

public class Intake extends SubsystemBase {
  
  CANSparkMax m_intakeMotor;
  double intakeSpeed = 1.0;//the max speed to run the intake at full throttle
  
  /**
   * Creates a new Intake.
   */
  public Intake(int port) {
    m_intakeMotor = new CANSparkMax(port, MotorType.kBrushless);
  }

  public void setForward(){
    System.out.println("forward");
    //m_intakeMotor.set(intakeSpeed * OI.intakeSens * (1/5));
    m_intakeMotor.set(intakeSpeed);
  }
  
  public void setReverse(){
    System.out.println("reverse");
    //m_intakeMotor.set(-intakeSpeed * OI.intakeSens * (1/5));
    m_intakeMotor.set(-intakeSpeed);
  } 

  public void release(){
    m_intakeMotor.set(0);
  }

  @Override
  public void periodic() {}
}
