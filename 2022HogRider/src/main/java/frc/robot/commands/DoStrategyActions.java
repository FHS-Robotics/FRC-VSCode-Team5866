package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.AutoStrategy;
import static frc.robot.Constants.*;

/**
 * Controls the Intake and Arm according to an {@see AutoTrajectory}.
 * This is used by {@see AutonomousCommand}, and trajectory actions
 * are loaded from {@see Constants}.kAutoTrajectories.
 *
 * @see AutonomousCommand
 */
public final class DoStrategyActions extends CommandBase {
    private final Arm m_arm;
    private final Intake m_intake;
    private final AutoStrategy m_strategy;
    private double m_startTime;

    public DoStrategyActions(AutoStrategy strategy, Arm arm, Intake intake) {
        m_strategy = strategy;
        m_arm = arm;
        m_intake = intake;
        addRequirements(arm, intake);
    }

    @Override
    public void initialize() {
        m_startTime = Timer.getFPGATimestamp();
        if (m_strategy.getFirstActionTime() < 0) {
            m_startTime -= m_strategy.getFirstActionTime();
        }
    }

    @Override
    public void execute() {
        // We get the current time since the command started
        // because we use this to decide when to run the intake.
        double time = Timer.getFPGATimestamp() - m_startTime;

        var action = m_strategy.sampleNearestAction(time);

        if (time - action.startTime > kAutoActionTime) {
            // This action has already completed.
            m_intake.move(0);
            m_arm.moveSafely(0);
            return;
        }

        switch(action.type) {
            case IntakeBall:
                m_intake.move(1);
                m_arm.moveSafely(0);
                break;
            case DispenseBall:
                m_intake.move(-1);
                m_arm.moveSafely(0);
                break;
            case ArmUp:
                m_arm.moveSafely(1);
                m_intake.move(0);
                break;
            case ArmDown:
                m_arm.moveSafely(-1);
                m_intake.move(0);
                break;
            case NoOp:
            default:
                m_intake.move(0);
                m_arm.moveSafely(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.move(0);
        m_arm.moveSafely(0);
    }

    @Override
    public boolean isFinished() {
        // This command is finished when it's last action is over.
        double time = Timer.getFPGATimestamp() - m_startTime;
        return time - m_strategy.getLastActionTime() > kAutoActionTime;
    }
}
