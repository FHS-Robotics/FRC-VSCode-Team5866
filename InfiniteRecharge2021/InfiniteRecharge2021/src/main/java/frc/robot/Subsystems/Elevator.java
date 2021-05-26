package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    public TalonFX m_left;
    public TalonFX m_right;
    
    public Elevator(TalonFX _left, TalonFX _right) {
        m_left = _left;
        m_right = _right;
    }

    public void raise() {
        m_left.set(ControlMode.PercentOutput, .5);
        m_right.set(ControlMode.PercentOutput, -.5);
    }

    public void lower() {
        m_left.set(ControlMode.PercentOutput, -.5);
        m_right.set(ControlMode.PercentOutput, .5);
    }

    public void release() {
        m_left.set(ControlMode.PercentOutput, 0);
        m_right.set(ControlMode.PercentOutput, 0);
    }

    @Override
    protected void initDefaultCommand() {}
}
