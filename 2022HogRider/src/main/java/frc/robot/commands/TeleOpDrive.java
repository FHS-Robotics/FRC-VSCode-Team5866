package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.utilities.Settings;
import frc.robot.OI;

/**
 * This command should run during and only during teleop.
 * It connects controller input to the intake, drive and arm of the robot.
 */
public final class TeleOpDrive<TMotor extends MotorController & IMotorController> extends CommandBase {
      Arm m_arm;
      Elevator m_elevator;
      IntakeSystem m_intakeSystem;
      Drive<TMotor> m_drive;

      public TeleOpDrive(Arm arm, Elevator elevator, IntakeSystem intakeSystem, Drive<TMotor> drive) {
            m_arm = arm;
            m_elevator = elevator;
            m_intakeSystem = intakeSystem;
            m_drive = drive;
            addRequirements(arm, elevator, intakeSystem, drive);
      }

      @Override
      public void execute() {
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

            if (OI.gunnerController.getAButton()) {
                  m_arm.set(Settings.ARM_SPEED());
            } else if (OI.gunnerController.getBButton()) {
                  m_arm.set(-Settings.ARM_SPEED());
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
