/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Counter;

public class LidarLiteV3 extends SubsystemBase {
  /**
   * Creates a new LidarLiteV3.
   */
  private Counter counter;
  private int printedWarningCount = 5;
  private static final int calibrationOffset = 0;
   public void LidarPWM(DigitalSource source) {
      counter = new Counter(source);
      counter.setMaxPeriod(1.0);
      counter.setSemiPeriodMode(true);
      counter.reset();
   }
   public double getDistance(){
     double cm;
     if(counter.get()<1){
       if(printedWarningCount-- > 0){
         System.out.println("LidarLitePWM: waiting for distance measurement");
       }
       return 0;
     }
     cm = (counter.getPeriod()*1000000.0/10.0) + calibrationOffset;
     return cm;
   }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
