/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWMTalonSRX;

public class TeleHook extends SubsystemBase {

  PWMTalonSRX m_TeleCables;
  PWMTalonSRX m_TeleHooks;
  PWMTalonSRX  m_TeleCables;
  PWMTalonSRX m_TeleHooks;
  double teleState;
  double cablePull;

  /**
   * Creates a new TeleHook.
   */
  
  public TeleHook(final int portUno,final int portDos) {
    m_TeleCables = new PWMTalonSRX(portUno);
    m_TeleHooks = new PWMTalonSRX(portDos); 

  }

public void extend(){
  m_TeleHooks.set(1);
}
public void retract(){
  m_TeleHooks.set(-1);
}
public void releaseHook(){
  m_TeleHooks.set(0);
}
  public TeleHook(final int portUno,final int portDos , final double state,final double pull ) {
    m_TeleCables = new PWMTalonSRX(portUno);
    m_TeleHooks = new PWMTalonSRX(portDos); 
    cablePull = pull;
    teleState = state;

  }




public void extend(){
  m_TeleHooks.set(1);
}
public void retracte(){
  m_TeleHooks.set(-1);
}

public void up(){
  m_TeleCables.set(1);
}
public void down(){
  m_TeleCables.set(-1);
  
public void releaseCable(){
  m_TeleCables.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
