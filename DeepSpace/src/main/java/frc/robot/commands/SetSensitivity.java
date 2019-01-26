/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class SetSensitivity extends InstantCommand {
  
  private boolean upOrDown;
  
  /** 
   * true for up, false for down
   * */
  public SetSensitivity(boolean _upOrDown) 
  {
    upOrDown = _upOrDown;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    int sensitivity = OI.sensitivity; //get the sensitivity from OI
    if(sensitivity > 10 || sensitivity < 0)
    {
        sensitivity = 5;
    }
    if(upOrDown == true && sensitivity != 10)
    {
        sensitivity++;
    }
    else if(upOrDown == false && sensitivity != 0)
    {
        sensitivity--;
    }
    OI.sensitivity = sensitivity; //set OI sensitivity to the new value
    System.out.println("Changed sensitivity of joysitcks: " + sensitivity); //print the sensitivity change
    SmartDashboard.putNumber("Joystick Sensitivity", sensitivity); //update the sensitivity on the shuffleboard
  }
}
