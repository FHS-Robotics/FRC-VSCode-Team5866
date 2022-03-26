package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public final class TimedAutoCommand extends CommandBase {
    /** Time to spend running intake. */
    public static final double kDispenseTime = 2;
    /** Time to spend driving backwards. */
    public static final double kDriveTime = 3;
    /** Time to spend lowering the arm. */
    public static final double kArmLowerTime = 2;

    private final Drive m_drive;
    private final Intake m_intake;
    private final Arm m_arm;

    private final Timer m_timer = new Timer();

    public TimedAutoCommand(Drive drive, Intake intake, Arm arm) {
        m_drive = drive;
        m_intake = intake;
        m_arm = arm;

        addRequirements(drive, intake, arm);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        double elapsed = m_timer.get();

        if (elapsed < kDispenseTime) {
            // Dispense ball for kDispenseTime seconds.
            m_intake.move(-1);
            m_arm.moveSafely(0);
            m_drive.arcadeDrive(0, 0);
            return; // Don't move on to the next step
        }
        m_intake.move(0); // Stop the intake motor.
        elapsed -= kDispenseTime; // Compensate for the time spent dispensing the ball.

        if (elapsed < kDriveTime) {
            // Drive backwards for kDriveTime seconds.
            m_drive.arcadeDrive(-0.3, 0);
            m_arm.moveSafely(0);
            m_intake.move(0);
            return; // Don't move on to the next step
        }
        m_drive.arcadeDrive(0, 0); // Stop the drive base.
        // Compensate for the time spent dispensing the ball.
        elapsed -= kDriveTime;

        if (elapsed < kArmLowerTime) {
            // Lower the arm for kArmLowerTime
            m_arm.moveSafely(-1);
            m_drive.arcadeDrive(0, 0);
            m_intake.move(0);
            return;
        }
        m_arm.moveSafely(0); // Stop the arm.
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.arcadeDrive(0, 0);
        m_intake.move(0);
        m_arm.moveSafely(0);
    }

    @Override
    public boolean isFinished() {
        double elapsed = m_timer.get();
        return elapsed > kDispenseTime + kDriveTime + kArmLowerTime;
    }
}
