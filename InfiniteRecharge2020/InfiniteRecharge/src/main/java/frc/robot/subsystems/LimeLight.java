/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem for handling the interactions with the LimeLight camera
 */
public class LimeLight extends SubsystemBase {

  //might look at removing the extention of a subsystem for this one, since the limelight isn't really a subsystem
  public NetworkTable table;
  public NetworkTableEntry tx;
  public NetworkTableEntry ty;
  public NetworkTableEntry ta;

  /**
   * Creates a new LimeLight.
   */
  public LimeLight() {
     table = NetworkTableInstance.getDefault().getTable("limelight");
     tx = table.getEntry("tx");
     ty = table.getEntry("ty");
     ta = table.getEntry("ta");
  }

  /**
   * Get the x angle offset from the crosshair of the limelight to the target
   * @return
   */
  public double getX() {
    return tx.getDouble(0.0);
  }

  /**
   * Get the y angle offset from the crosshair of the limelight to the target
   * @return
   */
  public double getY() {
    return ty.getDouble(0.0);
  }

  /**
   * Get the total area of the target; useful for determining the robot's distance from the target
   * @return
   */
  public double getArea() {
    return ta.getDouble(0.0);
  }

  @Override
  public void periodic() {
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", getX());
    SmartDashboard.putNumber("LimelightY", getY());
    SmartDashboard.putNumber("LimelightArea", getArea());
  }
}
