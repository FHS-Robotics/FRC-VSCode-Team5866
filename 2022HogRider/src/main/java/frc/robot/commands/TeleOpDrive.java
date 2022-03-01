package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.Settings;
import frc.robot.utilities.Debugging.Message;
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
            // GenericHID.isConnected() is very unreliable, and reports
            // a controller as connected after unplugging it.

            if (OI.driverController.isConnected()) {
                  Debugging.resetSendOnce(Message.DriverUnplugged);
                  doDriver();
            } else {
                  m_drive.getDrive().stopMotor();
                  m_intakeSystem.set(0);
                  Debugging.sendOnce(Message.DriverUnplugged, "The driver's controller is unplugged!");
            }
            

            if (OI.gunnerController.isConnected()) {
                  Debugging.resetSendOnce(Message.GunnerUnplugged);
                  doGunner();
            } else {
                  m_arm.stopArm();
                  m_elevator.stopElevator();
                  Debugging.sendOnce(Message.GunnerUnplugged, "The gunner's controller is unplugged!");
            }
      }

      private void doDriver() {
            double xSpeed = OI.driverController.getLeftY();
            double zRotation = OI.driverController.getRightX();

            m_drive.getDrive().arcadeDrive(xSpeed, zRotation);

            if (OI.driverController.getAButton()) {
                  m_intakeSystem.set(Settings.INTAKE_SPEED());
            } else if (OI.driverController.getBButton()) {
                  m_intakeSystem.set(-Settings.INTAKE_SPEED());
            } else {
                  m_intakeSystem.set(0);
            }
      }

      private void doGunner() {
            if (OI.gunnerController.getAButton()) {
                  m_arm.moveUp();
            } else if (OI.gunnerController.getBButton()) {
                  m_arm.moveDown();
            } else {
                  m_arm.stopArm();
            }

            int pov = OI.gunnerController.getPOV();
            if (pov == 0) {
                  m_elevator.moveUp();
            } else if (pov == 180) {
                  m_elevator.moveDown();
            } else {
                  m_elevator.stopElevator();
            }
      }
}
