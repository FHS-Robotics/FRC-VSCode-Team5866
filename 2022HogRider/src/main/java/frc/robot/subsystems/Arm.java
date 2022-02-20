package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Arm
 */
public class Arm extends SubsystemBase {
    private final CANSparkMax m_arm;
    // private final Counter m_counter = new Counter(new DigitalInput(0));
    // private int m_position;
    private boolean m_goingForward;

    public Arm(CANSparkMax arm) {
        m_arm = arm;
    }

    public void moveArm(boolean forward, double amount) {
        double trueAmount = forward ? (m_goingForward ? amount : 0) : (m_goingForward ? 0 : -amount);
        // m_arm.set(trueAmount);
        System.out.println("The true amount is " + trueAmount);
        SmartDashboard.putNumber("true-amount", trueAmount);
    }

    public void stopArm() {
        m_arm.set(0);
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Arm.periodic() m_counter.get(): ",
        // m_counter.get());
        // m_position += m_goingForward ? m_counter.get() : -m_counter.get();
        // m_goingForward = m_position <= 90;
    }
}