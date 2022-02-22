package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import frc.robot.Values;

public final class Elevator { 
    private final CANSparkMax m_motor;

    public Elevator(CANSparkMax motor) {
        m_motor = motor;
    }

    public void moveUp() {
        m_motor.set(Values.ELEVATOR_SPEED());
    }

    public void moveDown() {
        m_motor.set(-Values.ELEVATOR_SPEED());
    }
}
