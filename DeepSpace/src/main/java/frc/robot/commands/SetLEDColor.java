/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SetLEDColor extends InstantCommand {

  int red;
  int green;
  int blue;

  /**
   * Sets color of the LEDStrip
   * @param r : Amount of red (0-255)
   * @param g : Amount of green (0-255)
   * @param b : Amount of blue (0-255)
   */
  public SetLEDColor(int r, int g, int b) {
    requires(RobotMap.ledStrip);

    //Set all color values based on constructor input
    red = r;
    green = g;
    blue = b;
  }

  // Called once when the command executes
  @Override
  protected void initialize() 
  {
    RobotMap.ledStrip.SetColorOutput(red, green, blue);
  }

}
