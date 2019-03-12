/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargodelivery;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverCargo extends CommandGroup {

  /**
   * Automatically deliver cargo to the specified hatch
   * @param hatch : true for hatch, false for ball
   * @param level : 1-3 based on the level to deliver to
   */
  public DeliverCargo(Boolean hatch, int level) {

    addSequential(new MoveToTarget());
  }
}
