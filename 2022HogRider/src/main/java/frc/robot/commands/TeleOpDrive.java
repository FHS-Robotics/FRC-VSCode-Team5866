package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.utilities.Settings;
import frc.robot.OI;

/**
 * This command should run during and only during teleop.
 * It connects controller input to the intake, drive and arm of the robot.
 */
public final class TeleOpDrive<TMotor extends MotorController & IMotorController> extends CommandBase {
      IntakeSystem m_intakeSystem;
      Drive<TMotor> m_drive;

      public TeleOpDrive(IntakeSystem intakeSystem, Drive<TMotor> drive) {
            m_intakeSystem = intakeSystem;
            m_drive = drive;
            addRequirements(intakeSystem, drive);
      }

      @Override
      public void execute() {
            double xSpeed = OI.driverController.getLeftY();
            double zRotation = OI.driverController.getRightX();

            m_drive.getDrive().arcadeDrive(xSpeed, zRotation);

            if (OI.driverController.getAButton()) {
                  RobotMap.m_arm.set(Settings.ARM_SPEED());
            } else if (OI.driverController.getBButton()) {
                  RobotMap.m_arm.set(-Settings.ARM_SPEED());
            } else {
                  RobotMap.m_arm.stopArm();
            }

            if (OI.gunnerController.getAButton()) {
                  RobotMap.m_intake.set(Settings.INTAKE_SPEED());
            } else if (OI.gunnerController.getBButton()) {
                  RobotMap.m_intake.set(-Settings.INTAKE_SPEED());
            } else {
                  RobotMap.m_intake.set(0);
            }
      }
}
