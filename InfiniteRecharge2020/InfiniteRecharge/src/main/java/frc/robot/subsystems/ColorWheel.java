/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {

  Spark m_motor; //the motor spinning the color wheel
  double speed = 0.1; //speed to run the spinner

  public ColorWheel(int port) {
    m_motor = new Spark(port);
  }

  /**
  * spin the motor to turn the color wheel
  */
  public void spin(boolean direction) {
    m_motor.set(direction ? speed : -speed);
  }

  public void release() {
    m_motor.set(0);
  }
}
