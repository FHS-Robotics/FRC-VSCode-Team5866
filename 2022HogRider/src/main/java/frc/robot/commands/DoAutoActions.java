package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.AutoTrajectory;
import static frc.robot.Constants.*;

/**
 * Controls the Intake and Arm according to an {@see AutoTrajectory}.
 * This is used by {@see AutonomousCommand}, and trajectory actions
 * are loaded from {@see Constants}.kAutoTrajectories.
 *
 * @see AutonomousCommand
 */
public final class DoAutoActions extends CommandBase {
    private final Arm m_arm;
    private final Intake m_intake;
    private final AutoTrajectory m_actions;
    private double m_startTime;

    public DoAutoActions(AutoTrajectory actions, Arm arm, Intake intake) {
        m_actions = actions;
        m_arm = arm;
        m_intake = intake;
        addRequirements(arm, intake);
    }

    @Override
    public void initialize() {
        m_startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        // We get the current time since the command started
        // because we use this to decide when to run the intake.
        double time = Timer.getFPGATimestamp() - m_startTime;

        var action = m_actions.sampleNearestAction(time);

        if (time - action.startTime > kAutoActionTime) {
            // This action has already completed.
            return;
        }

        switch(action.type) {
            case DispenseBall:
                m_intake.move(1);
                break;
            case IntakeBall:
                m_intake.move(-1);
            case ArmUp:
                m_arm.moveSafely(1);
            case ArmDown:
                m_arm.moveSafely(-1);
            case NoOp:
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.move(0);
    }

    @Override
    public boolean isFinished() {
        // This command is finished when it's last action is over.
        double time = Timer.getFPGATimestamp() - m_startTime;
        return time - m_actions.getLastActionTime() > kAutoActionTime;
    }
}
