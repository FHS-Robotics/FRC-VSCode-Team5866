package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * IntakeSystem
 */
public class IntakeSystem extends SubsystemBase {
    private final CANSparkMax m_intake;

    public IntakeSystem(CANSparkMax intake) {
        m_intake = intake;
    }

    public void set(double percent) {
        if (percent != 0.1 && percent != 0) {
            System.out.println("Intake System setting: " + percent);
        }
        m_intake.set(percent);
    }
}