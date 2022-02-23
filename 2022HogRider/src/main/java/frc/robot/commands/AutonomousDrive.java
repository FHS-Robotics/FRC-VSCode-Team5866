package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.IMotorController;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.IntakeSystem;

/**
 * This command should run during and only during autonomous.
 * It performs automated movement and point scoring
 */
public final class AutonomousDrive<TMotor extends MotorController & IMotorController> extends CommandBase {
    IntakeSystem m_intakeSystem;
    Drive<TMotor> m_drive;

    public AutonomousDrive(IntakeSystem intakeSystem, Drive<TMotor> drive) {
        m_intakeSystem = intakeSystem;
        m_drive = drive;
        addRequirements(intakeSystem, drive);
    }

    @Override
    public void initialize() {
        m_drive.prepareForAutonomous();
    }
}
