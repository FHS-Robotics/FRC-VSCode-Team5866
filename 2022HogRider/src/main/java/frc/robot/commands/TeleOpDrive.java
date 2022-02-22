package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.Values;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.OI;

/**
 * This command should run during and only during teleop.
 * It connects controller input to the intake, drive and arm of the robot.
 */
public final class TeleOpDrive extends CommandBase {

    IntakeSystem m_intakeSystem;
    DifferentialDrive m_drive;
    XboxController m_driverControl;

    public TeleOpDrive(XboxController driverController, IntakeSystem intakeSystem) {
        m_driverControl = driverController;
        m_intakeSystem = intakeSystem;
        addRequirements(intakeSystem);
    }

    @Override
    public void initialize() {
        m_drive = RobotMap.m_drive;
    }

    @Override
    public void execute() {
        double xSpeed = OI.driverStick.getRawAxis(1) * 0.5;
        double zRotation = OI.driverStick.getRawAxis(4) * 0.5;

        m_drive.arcadeDrive(xSpeed, zRotation);

        if (OI.driverController.getAButton()) {
          RobotMap.m_arm.moveArm(true, Values.ARM_SPEED());
        } else if (OI.driverController.getBButton()) {
          RobotMap.m_arm.moveArm(false, Values.ARM_SPEED());
        } else {
          RobotMap.m_arm.stopArm();
        }

        if (OI.gunnerController.getAButton()) {
            RobotMap.m_intake.set(Values.INTAKE_SPEED());
        } else if (OI.gunnerController.getBButton()) {
            RobotMap.m_intake.set(-Values.INTAKE_SPEED());
        } else {
            RobotMap.m_intake.set(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}