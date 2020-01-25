/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.RobotMap;

public class PIDDrive extends PIDSubsystem {

  final double maxValue = 180.0;
  final double minValue = -180.0;
  public double speed;

  public PIDDrive() {
    super(
        // The PIDController used by the subsystem
        new PIDController(3, 0, 0.1));
        m_controller.enableContinuousInput(minValue, maxValue);
        setSetpoint(0);
      }

  @Override
  public void useOutput(double output, double setpoint) {
    speed = output / 180; //remap value from -1 : 1
  }

  @Override
  public double getMeasurement() {
    return RobotMap.gyro.getYaw();
  }

  public double getSetpoint() {
    return m_controller.getSetpoint();
  }

  public void addToSetpoint(double value) {
    double setNew = getSetpoint() + value;
    if(setNew > maxValue) {
      setNew = minValue + (setNew - maxValue);
    }
    else if(setNew < minValue) {
      setNew = maxValue + (setNew - minValue);
    }
    setSetpoint(setNew);
  }
}
