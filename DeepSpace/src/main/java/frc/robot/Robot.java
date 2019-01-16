/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  private DifferentialDrive m_myRobot_Front;
  private DifferentialDrive m_myRobot_Back;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  Ultrasonic ultra = new Ultrasonic(1, 1); //Creating ultrasonic object

  double xSensitivity = 100; //try to stick within 0-100 range 100 being max sensitivity and 0 being no response at all

  @Override
  public void robotInit() {
    m_myRobot_Front = new DifferentialDrive(new PWMVictorSPX(3), new PWMVictorSPX(0));
    m_myRobot_Back = new DifferentialDrive(new PWMVictorSPX(2), new PWMVictorSPX(1));
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);

    /*UsbCamera mainCam = new UsbCamera("MainCamera", 0);
    mainCam.setFPS(15);
    mainCam.setResolution(320, 240); //width and height
    CameraServer.getInstance().startAutomaticCapture(mainCam); //start camera server*/
    CameraServer.getInstance().startAutomaticCapture(); //start camera server
    ultra.setAutomaticMode(true);
  }

  @Override
  public void teleopPeriodic() {
    //if the right joystick is moved in the x position we will move side to side
    if(Math.abs(m_rightStick.getX()) > Math.abs(m_rightStick.getY()) && Math.abs(m_leftStick.getY()) < .2)
    {
      double inverseSensitivity = .01 * xSensitivity; //making sensitivity a decimal so that we can multiply it as a modifier to the x axis
      double leftX = m_rightStick.getX() * inverseSensitivity; //set variable to x axis position of right stick divided by the x sensitivity

      m_myRobot_Front.tankDrive(leftX, leftX * .9);
      m_myRobot_Back.tankDrive(-leftX, -leftX * .9); //inverse the back wheels for the left to right movement to work
    }
    else
    {
      //else we simply take the y axis values from each joystick and send them to the swervedrive
      m_myRobot_Front.tankDrive(-m_leftStick.getY(), -m_rightStick.getY()); //all values are inverted to change which direction is front based on how the motors are wired
      m_myRobot_Back.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
    }

    System.out.print(ultrasonicSample());
  }

  //returns the distance that the ultrasonic sensor is reading
  public double ultrasonicSample()
  {
    return ultra.getRangeInches();
  }
}
