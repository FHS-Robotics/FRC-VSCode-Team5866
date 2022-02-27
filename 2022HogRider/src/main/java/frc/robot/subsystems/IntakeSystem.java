package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Encapsulates all to do with taking in balls.
 */
public final class IntakeSystem extends SubsystemBase {
      private final MotorController m_intake;

      public IntakeSystem(MotorController intake) {
            m_intake = intake;
      }

      public void set(double percent) {
            if (percent != 0.1 && percent != 0) {
                  System.out.println("Intake System setting: " + percent);
            }
            m_intake.set(percent);
      }
}
