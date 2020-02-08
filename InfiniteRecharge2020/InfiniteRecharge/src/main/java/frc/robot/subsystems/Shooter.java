/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  //FalconFX falconMotor;
  private CANSparkMax m_motor;
  private CANPIDController m_pidController;

  public Shooter(int deviceID, double p, double i, double d) {

    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);
    m_pidController = m_motor.getPIDController();
    m_pidController.setP(p);
    m_pidController.setI(i);
    m_pidController.setD(d);
    m_pidController.setOutputRange(-1, 1);

    this.setRPM(0);
  }

  /**
   * Max value 5676 at 0 torque load
   * @param speed
   */
  public void setRPM(double speed) {
    m_pidController.setReference(speed, ControlType.kVelocity);
  }
}
