/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.OI;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ChangeIntakeSensitivity extends InstantCommand {

  boolean upOrDown;

  /**
   * True if up 1, false if down.
   * @param _upOrDown
   */
  public ChangeIntakeSensitivity(boolean _upOrDown) {
    upOrDown = _upOrDown;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(upOrDown && OI.intakeSens < 5)
      OI.intakeSens += 1;
    else if(!upOrDown && OI.intakeSens > 0)
      OI.intakeSens -= 1;
  }
}