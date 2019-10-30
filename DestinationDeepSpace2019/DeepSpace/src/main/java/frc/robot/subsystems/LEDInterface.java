/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * This class is to control the RGB LEDStrip using DigitalOutputs that are received
 * by the Arduino.  The inputs on the arduino are 0, 1, 3, 4 .
 * The data pin is 6 for the strip itself
 */
public class LEDInterface extends Subsystem {

  //pins for sending out which color to send
  DigitalOutput red;
  DigitalOutput blue;
  DigitalOutput neutral;
  DigitalOutput showcase;

  public enum ColorMode {red, blue, neutral, showcase};

  public LEDInterface(int r, int b, int n, int s)
  {
    red = new DigitalOutput(r);
    blue = new DigitalOutput(b);
    neutral = new DigitalOutput(n);
    showcase = new DigitalOutput(s);
    red.set(false);
    blue.set(false);
    neutral.set(false);
    showcase.set(false);
  }


  @Override
  public void initDefaultCommand() {}

  /**
   * Takes a mode and tells the arduino to set the lights to that mode
   * @param mode : red, blue, neutral, showcase
   */
  public void setOutput(ColorMode mode)
  {
    if(mode == ColorMode.red)
    {
      red.set(true);
      blue.set(false);
      neutral.set(false);
      showcase.set(false);
    }
    else if(mode == ColorMode.blue)
    {
      red.set(false);
      blue.set(true);
      neutral.set(false);
      showcase.set(false);
    }
    else if(mode == ColorMode.showcase)
    {
      red.set(false);
      blue.set(false);
      neutral.set(false);
      showcase.set(true);
    }
    else
    {
      red.set(false);
      blue.set(false);
      neutral.set(true);
      showcase.set(false);
    }
  }
}
