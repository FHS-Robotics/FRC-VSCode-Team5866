/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ultrasonicSensor.GetRange;

public class UltrasonicSensor extends Subsystem {
  public static AnalogInput ai;
  public static double voltage; //defaults at 5v

  public UltrasonicSensor(AnalogInput input, double _voltage)
  {
    ai = input;
    voltage = _voltage;
  }

  public double Range()
  {
    return 1;
  }

  @Override
  public void initDefaultCommand() {

  }
}
