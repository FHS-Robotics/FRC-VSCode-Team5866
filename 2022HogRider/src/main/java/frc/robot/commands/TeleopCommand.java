package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.Debugging;

/**
 * During teleop, this command takes input from
 * the controllers and moves the robot's motors
 * accordingly.
 *
 * <ul>
 *  <li>Driver Left Stick Y -> Moves Forwards/Backwards</li>
 *  <li>Driver Right Stick X -> Moves Sideways</li>
 *  <li>Gunner Left Stick Y -> Arm Up</li>
 *  <li>Gunner D-Pad -> Elevator Up</li>
 *  <li>Gunner A/B Buttons -> Intake In/Out</li>
 * </ul>
 */
public final class TeleopCommand extends CommandBase {
      private final XboxController m_driverController;
      private final XboxController m_gunnerController;

      private final Arm m_arm;
      private final Drive m_drive;
      private final Intake m_intake;
      private final Elevator m_elevator;

      public TeleopCommand(XboxController driverController, XboxController gunnerController, Arm arm, Drive drive, Intake intake, Elevator elevator) {
            m_driverController = driverController;
            m_gunnerController = gunnerController;

            m_arm = arm;
            m_drive = drive;
            m_intake = intake;
            m_elevator = elevator;
            addRequirements(arm, drive, intake, elevator);
      }

      @Override
      public void execute() {
            Debugging.put("Driver Connected", m_driverController.isConnected() ? "Yes" : "No");
            Debugging.put("Gunner Connected", m_gunnerController.isConnected() ? "Yes" : "No");

            if (m_driverController.isConnected()) {
                  m_drive.arcadeDrive(-m_driverController.getLeftY(), m_driverController.getRightX());
            } else {
                  m_drive.arcadeDrive(0, 0);
            }

            if (m_gunnerController.isConnected()) {
                  m_arm.moveSafely(-m_gunnerController.getLeftY());
                  switch (m_gunnerController.getPOV()) {
                        case 0:
                              m_elevator.move(1);
                              break;
                        case 180:
                              m_elevator.move(-1);
                              break;
                        default:
                              m_elevator.move(0);
                  }
                  if (m_gunnerController.getAButton()) {
                        m_intake.move(1);
                  } else if (m_gunnerController.getBButton()) {
                        m_intake.move(-1);
                  } else {
                        m_intake.move(0);
                  }
            } else {
                  m_arm.moveSafely(0);
                  m_elevator.move(0);
                  m_intake.move(0);
            }
      }

      @Override
      public void end(boolean interrupted) {
            m_drive.arcadeDrive(0, 0);
            m_arm.moveSafely(0);
            m_elevator.move(0);
            m_intake.move(0);
      }
}
