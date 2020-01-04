/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase; //currently unable to import on my computer?

public class VersaDrive extends SubsystemBase {

  private MecanumDrive m_swiftDrive;
  private DifferentialDrive m_powerDrive;

  private Solenoid act_frontLeft;
  private Solenoid act_backLeft;
  private Solenoid act_frontRight;
  private Solenoid act_backRight;

  public enum DriveState { swift, power};
  public DriveState mode;

  /**
   * The Versa drive base is a dual mode drive base for switching between agile mecanum and powerful friction wheels
   */
  public VersaDrive(Solenoid _actFrontLeft, Solenoid _actBackLeft, Solenoid _actFrontRight, Solenoid _actBackRight, 
                    PWMSpeedController _frontLeft, PWMSpeedController _backLeft, PWMSpeedController _frontRight, PWMSpeedController _backRight) {
    act_frontLeft = _actFrontLeft;
    act_backLeft = _actBackLeft;
    act_frontRight = _actFrontRight;
    act_backRight = _actBackRight;

    //create speed controllers for each side of the bot
    SpeedControllerGroup m_left = new SpeedControllerGroup(_frontLeft, _backLeft);
    SpeedControllerGroup m_right = new SpeedControllerGroup(_frontRight, _backRight);

    //pass in motors to the constructors of our swift and power drives
    m_swiftDrive = new MecanumDrive(_frontLeft, _backLeft, _frontRight, _backRight);
    m_powerDrive = new DifferentialDrive(m_left, m_right);

    //set the default mode to the mecanum
    mode = DriveState.swift;
  }

  public void setState(DriveState _mode) {
    mode = _mode;
  }

  public void switchState() {
    mode = mode == DriveState.swift ? DriveState.power : DriveState.swift; //really fancy notation that says if the mode is swift, change to power, and vice versa (haha Versa)
  }
}