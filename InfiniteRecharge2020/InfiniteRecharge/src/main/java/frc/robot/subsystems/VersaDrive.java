/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase; //currently not importing

/*
Okay let's go over some math:

The Falcon 500 motors have a max RPM of 6380
The gears connected directly to the Falcon shafts have 12 teeth
The large gears connected to the Versa drive have 66 teeth
The large sprockets have 32 teeth
The small sprockets have 18 teeth
The radii of the wheels is 2 inches

Ratio of gears: 12/66 = 0.181818
RPM conversion: 6380 RPM * 0.181818 = 1160 RPM
Ratio of Sprockets: 32/18 = 1.77778
RPM conversion: 1160 RPM * 1.77778 = 2062 RPM

Circumference of the wheels: 2 in * 2 * pi = 12.566 inches
So, 1 rotation = 12.566 inches

So, in meters per second that's: (2062 Revolutions / 1 minute) * (1 minute / 60 seconds) * (12.566 inch / 1 revolution) * (0.0254 meters / 1 inch) = 10.969 m/s

xVelocity = 10.969 m/s //forward velocity

Same for the y velocity as per https://www.vexforum.com/t/mecanum-wheels-calculating-strafe-distance/21977
*/

public class VersaDrive extends SubsystemBase {

  public MecanumDrive m_swiftDrive;

  private DoubleSolenoid act_solenoidLeft;
  private DoubleSolenoid act_solenoidRight;

  double maxVelocityY = 10.969; //spede of strafe
  double maxVelocityX = 10.969; //forward speed

  //MecanumDriveKinematics kinematics = new MecanumDriveKinematics(new Translation2d(0.3683, 0.2286), new Translation2d(0.3683, 0.2286), new Translation2d(0.3683, 0.2286), new Translation2d(0.3683, 0.2286)); //x=14.5in; y=9in

  public enum DriveState { swift, power};
  public DriveState mode;

  /**
   * The Versa drive base is a dual mode drive base for switching between agile mecanum and powerful friction wheels
   */
  public VersaDrive(DoubleSolenoid _actSolenoidLeft, DoubleSolenoid _actSolenoidRight, PWMSpeedController _frontLeft, PWMSpeedController _backLeft, PWMSpeedController _frontRight, PWMSpeedController _backRight) {

    act_solenoidLeft = _actSolenoidLeft;
    act_solenoidRight = _actSolenoidRight;

    //pass in motors to the constructors of our swift and power drives
    m_swiftDrive = new MecanumDrive(_frontLeft, _backLeft, _frontRight, _backRight);

    //set the default mode to the mecanum
    mode = DriveState.power;
    setSolenoids();
  }

    /**
   * The Versa drive base is a dual mode drive base for switching between agile mecanum and powerful friction wheels
   */
  public VersaDrive(DoubleSolenoid _actSolenoidLeft, DoubleSolenoid _actSolenoidRight, WPI_TalonFX _frontLeft, WPI_TalonFX _backLeft, WPI_TalonFX _frontRight, WPI_TalonFX _backRight) {

    act_solenoidLeft = _actSolenoidLeft;
    act_solenoidRight = _actSolenoidRight;

    //pass in motors to the constructors of our swift and power drives
    m_swiftDrive = new MecanumDrive(_frontLeft, _backLeft, _frontRight, _backRight);

    //set the default mode to the tank drive
    mode = DriveState.power;
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
      act_solenoidLeft.set(Value.kForward);
      act_solenoidRight.set(Value.kForward);
    }
    else {
      act_solenoidLeft.set(Value.kReverse);
      act_solenoidRight.set(Value.kReverse);
    }
  }

  @Override
  public void periodic() {
    setSolenoids();
    SmartDashboard.putString("DriveMode", mode.toString());
  }
}