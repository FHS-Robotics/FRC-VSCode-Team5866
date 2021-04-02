package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.simulation.BatterySim;
public class Shooter extends Subsystem {

    public TalonFX m_left;
    public TalonFX m_right;

    public double ks = 0.523;
    public double kv = 1.44;
    public double kp = 3.33;
    public double ka = 1.48;
    public double kf = kv * (6.28*0.0635) / 12;
    //1 m/s
    //15.748 rad/s
    //2.51 rps
    //150.5 rpm --> 724.3 rpm of shaft
    //gear ratio = 77/16 --> 4.8125
    //1 rotation = 0.399 m
    //r=0.0635
    //1 rpm = 0.399 m/m
    //falcon native units = 

    //values for shoot forward and backward
    public double shootForward = 0.5;
    public double shootBackward = -0.25;

    public Shooter(TalonFX left, TalonFX right) {
        m_left = left;
        m_right = right;
        //m_left.getSupplyCurrent();
        //m_left.getMotorOutputVoltage();
        m_left.config_kF(0, kf);
        m_left.config_kP(0, kp);
        m_right.config_kF(0, kf);
        m_right.config_kP(0, kp);
    }

    /**
     * shoot forward or backward at different speeds depending on direction
     * @param direction : direction to run shooter
     */
    public void shoot(boolean direction) {
        //m_left.set(ControlMode.PercentOutput, direction ? shootForward : shootBackward);
        //m_right.set(ControlMode.PercentOutput, direction ? -shootForward : -shootBackward);
        //m_left.set(ControlMode.Velocity, ((shootForward * 5000)/600)*2048/1); //speed in rpm
        //SimpleMotorFeedforward forward = new SimpleMotorFeedforward(ks, kv, ka);
        /*m_left.set(ControlMode.Velocity, 1000 * 2048/600); //speed in rpm  
        m_right.set(ControlMode.Velocity, 1000 * 2048/600); //speed in rpm    
        m_right.set(ControlMode.PercentOutput, DemandType.ArbitraryFeedForward, , demand1);*/
        //System.out.println((forward.calculate(shootForward) * 1023)/12);
        m_left.set(ControlMode.Velocity, -1000 * shootForward);
        m_right.set(ControlMode.Velocity, 1000 * shootForward);
    }
 
    /**
     * Release motors
     */
    public void release() {
        m_left.set(ControlMode.PercentOutput, 0);
        m_right.set(ControlMode.PercentOutput, 0);
    }

    @Override
    protected void initDefaultCommand() {}
}
