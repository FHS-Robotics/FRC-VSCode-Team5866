/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase; //currently not importing
import frc.robot.commands.TeleOpDrive;

public class VersaDrive extends SubsystemBase {

  public MecanumDrive m_swiftDrive;

  private DoubleSolenoid act_solenoid;

  public enum DriveState { swift, power};
  public DriveState mode;

  /**
   * The Versa drive base is a dual mode drive base for switching between agile mecanum and powerful friction wheels
   */
  public VersaDrive(DoubleSolenoid _actSolenoid, PWMSpeedController _frontLeft, PWMSpeedController _backLeft, PWMSpeedController _frontRight, PWMSpeedController _backRight) {

    //setDefaultCommand(new TeleOpDrive()); //when no command is running, TeleOpDrive() will
    act_solenoid = _actSolenoid;

    //pass in motors to the constructors of our swift and power drives
    m_swiftDrive = new MecanumDrive(_frontLeft, _backLeft, _frontRight, _backRight);

    //set the default mode to the mecanum
    mode = DriveState.swift;
    setSolenoids();
  }

  public void setState(DriveState _mode) {
    mode = _mode;
  }

  public void switchState() {
    mode = mode == DriveState.swift ? DriveState.power : DriveState.swift; //really fancy notation that says if the mode is swift, change to power, and vice versa (haha Versa)
  }

  /**
   * Refreshes the solenoids based on the current drive mode
   */
  public void setSolenoids() {

    if(mode == DriveState.swift) {
      act_solenoid.set(Value.kForward);
    }
    else {
      act_solenoid.set(Value.kReverse);
    }
  }

  @Override
  public void periodic() {
    setSolenoids();
    SmartDashboard.putString("DriveMode", mode.toString());
  }
}