/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWM;

/**
 * This class is to control the RGB LEDStrip using pulse-width modulation
 */
public class LEDStrip extends Subsystem {

  PWM red;
  PWM green;
  PWM blue;

  public LEDStrip(int r, int g, int b)
  {
    red = new PWM(r);
    green = new PWM(g);
    blue = new PWM(b);
    red.setRaw(0); //setRaw between a value of 0 (always off) and 255 (always on)
    green.setRaw(0);
    blue.setRaw(0);
  }


  @Override
  public void initDefaultCommand() {}

  /**
   * Takes 3 integers between 0-255 and sets the RGB on the LED Strip to those values
   * @param r : Red
   * @param g : Green
   * @param b : Blue
   */
  public void SetColorOutput(int r, int g, int b)
  {
    red.setRaw(r);
    green.setRaw(g);
    blue.setRaw(b);
  }
}
