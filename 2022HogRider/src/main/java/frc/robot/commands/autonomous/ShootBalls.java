package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class ShootBalls extends CommandBase {
      private final IntakeSystem m_intakeSystem;

      public ShootBalls(IntakeSystem intakeSystem) {
            m_intakeSystem = intakeSystem;
      }

      @Override
      public void execute() {
            m_intakeSystem.move(1);
      }
}
