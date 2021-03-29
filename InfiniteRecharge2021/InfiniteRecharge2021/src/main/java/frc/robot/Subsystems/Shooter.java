package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
public class Shooter extends Subsystem {

    public TalonFX m_left;
    public TalonFX m_right;

    //values for shoot forward and backward
    public static final double shootForward = 0.75;
    public static final double shootBackward = -0.25;

    public Shooter(TalonFX left, TalonFX right) {
        m_left = left;
        m_right = right;
    }

    /**
     * shoot forward or backward at different speeds depending on direction
     * @param direction : direction to run shooter
     */
    public void shoot(boolean direction) {
        m_left.set(ControlMode.PercentOutput, direction ? shootForward : shootBackward);
        m_right.set(ControlMode.PercentOutput, direction ? shootForward : shootBackward);
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
