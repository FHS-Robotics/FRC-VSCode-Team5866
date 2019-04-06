/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.boschmotor.BoschMotorContinuous;

/**
 * Bosch Seat Motor
 * <br/>
 * <br/>
 * (Not a motor used during the 2019 season)
 */
public class BoschMotor extends Subsystem {

  public Counter motorCounter;
  public PWMTalonSRX pwmChannel;

/**
 * Define the pwm channel and the digital input for the encoder
 * @param _pwmChannel
 * @param encoder
 */
  public BoschMotor(int _pwmChannel, int encoder)
  {
    pwmChannel = new PWMTalonSRX(_pwmChannel);
    motorCounter = new Counter(new DigitalInput(encoder));
  }

  /**
   * 
   * @param speed : double beween -1 and 1
   */
  public void setPWM(double speed)
  {
    pwmChannel.set(speed);;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new BoschMotorContinuous());
  }
}
