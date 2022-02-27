package frc.robot.subsystems;

// import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

/**
 * Runs the robot's arm.
 */
public final class Arm extends SubsystemBase {
      private final MotorController m_arm;
      // private final RelativeEncoder m_encoder;
      private final Counter m_counter;
      private int m_position;
      private boolean m_goingForward;

      public Arm(MotorController arm, int encoderChanel) {
            m_arm = arm;
            // m_encoder = m_arm.getEncoder();
            m_counter = new Counter(new DigitalInput(encoderChanel));
      }

      public void set(double amount) {
            m_arm.set(amount);
            Debugging.debug("Driving Arm Motor at " + amount);
      }

      public void stopArm() {
            m_arm.set(0);
      }

      @Override
      public void periodic() {
            m_position += m_goingForward ? m_counter.get() : -m_counter.get();
            m_goingForward = m_position <= 90;
      }
}
