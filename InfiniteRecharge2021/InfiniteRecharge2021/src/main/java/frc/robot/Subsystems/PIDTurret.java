package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.TeleOpDrive;
import frc.robot.Commands.TeleOpTurret;

public class PIDTurret extends PIDSubsystem{
    
    public CANSparkMax m_motor;
  
    public double speed;
    private double errorThreshold = 0.5; //Our threshold of error is +-1 degree
  
    public PIDTurret(CANSparkMax m) {
      super(0.01, .000, 0.015);
      setAbsoluteTolerance(errorThreshold);

      m_motor = m;
    }
  
    @Override
    public void usePIDOutput(double output) {
      speed = speed > 1 ? 1 : speed;
      speed = speed < -1 ? -1 : speed;
      speed = output;
    }
  
    @Override
    public double returnPIDInput() {
      // Return the process variable measurement here
      return -RobotMap.limeLight.getX();
    }
  

   /**
     * Returns whether the robot is close enough to the set angle
     * @return
     */
    public boolean onTarget() {
      return Math.abs(returnPIDInput() - getSetpoint()) < errorThreshold && RobotMap.limeLight.getArea() > 5;
    }

    //rotate motor
    public void rotate(double speed) {
        m_motor.set(speed);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleOpTurret());
    }
}
