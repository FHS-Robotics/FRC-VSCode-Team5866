package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.utilities.Debugging;
import frc.robot.OI;

/**
 * This command should run during and only during teleop.
 * It connects controller input to the intake, drive and arm of the robot.
 */
public final class TeleOpDrive extends CommandBase {
      Arm m_arm;
      Elevator m_elevator;
      IntakeSystem m_intakeSystem;
      Drive m_drive;

      public TeleOpDrive(Arm arm, Elevator elevator, IntakeSystem intakeSystem, Drive drive) {
            m_arm = arm;
            m_elevator = elevator;
            m_intakeSystem = intakeSystem;
            m_drive = drive;
            addRequirements(arm, elevator, intakeSystem, drive);
      }

      @Override
      public void execute() {
            if (OI.driverController.isConnected()) {
                  Debugging.put("driver_connected", "Yes");

                  double xSpeed = -OI.driverController.getLeftY();
                  double zRotation = OI.driverController.getRightX();
                  m_drive.arcadeDrive(xSpeed, zRotation);
            } else {
                  Debugging.put("driver_connected", "No");

                  m_drive.getDrive().stopMotor();
            }
            

            if (OI.gunnerController.isConnected()) {
                  Debugging.put("gunner_connected", "Yes");

                  double armSpeed = -OI.gunnerController.getLeftY();
                  m_arm.moveSafely(armSpeed);
      
                  if (OI.gunnerController.getAButton()) {
                        m_intakeSystem.move(1);
                  } else if (OI.gunnerController.getBButton()) {
                        m_intakeSystem.move(-1);
                  } else {
                        m_intakeSystem.move(0);
                  }
      
                  int pov = OI.gunnerController.getPOV();
                  if (pov == 0) {
                        m_elevator.move(1);
                  } else if (pov == 180) {
                        m_elevator.move(-1);
                  } else {
                        m_elevator.move(0);
                  }
            } else {
                  Debugging.put("gunner_connected", "No");

                  m_intakeSystem.move(0);
                  m_arm.moveSafely(0);
                  m_elevator.move(0);
            }
      }
}
