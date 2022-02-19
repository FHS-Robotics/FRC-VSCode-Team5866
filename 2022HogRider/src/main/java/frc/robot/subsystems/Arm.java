package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Arm
 */
public class Arm extends SubsystemBase {
    private final CANSparkMax m_arm;

    public Arm(CANSparkMax arm) {
        m_arm = arm;
    }

    public void set(double set) {
        m_arm.set(set);
    }

}