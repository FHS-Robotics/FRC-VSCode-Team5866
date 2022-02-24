package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;

/**
 * Runs the robot's arm.
 */
public final class Arm extends SubsystemBase {
      private final WPI_TalonFX m_arm;
      // private final RelativeEncoder m_encoder;
      private final Counter m_counter;
      private int m_position;
      private boolean m_goingForward;

      public Arm(WPI_TalonFX arm, int encoderChanel) {
            m_arm = arm;
            // m_encoder = m_arm.getEncoder();
            m_counter = new Counter(new DigitalInput(encoderChanel));
      }

      public void moveArm(boolean upwards, double amount) {
            double trueAmount = amount * (upwards ? -amount : amount);
            // double trueAmount = forward ? (m_goingForward ? amount : 0) : (m_goingForward
            // ? 0 : -amount);
            m_arm.set(trueAmount);
            Debugging.debug("Driving Arm Motor at " + trueAmount);
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
