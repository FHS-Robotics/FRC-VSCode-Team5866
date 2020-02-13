/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.RobotMap;

public class PIDDrive extends PIDSubsystem {

  final double maxValue = 180.0;
  final double minValue = -180.0;
  public double speed;
  public double errorThreshold = 1; //Threshold of error is good to x degrees

  public PIDDrive() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.025, 0, 0.001));
        m_controller.enableContinuousInput(minValue, maxValue);
        setSetpoint(0);
      }

  @Override
  public void useOutput(double output, double setpoint) {
    speed = speed > 1 ? 1 : speed;
    speed = speed < -1 ? -1 : speed;
    speed = output; //remap value from -1 : 1
  }

  @Override
  public double getMeasurement() {
    return RobotMap.gyro.getYaw();
  }

  /**
   * Returns whether the robot is close enough to the set angle
   * @return
   */
  public boolean onTarget() {
    return Math.abs(getMeasurement() - getSetpoint()) < errorThreshold;
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
