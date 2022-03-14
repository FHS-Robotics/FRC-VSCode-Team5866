package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.AutoTrajectory;
import static frc.robot.Constants.*;

public final class DoAutoActions extends CommandBase {
    private final Intake m_intake;
    private final AutoTrajectory m_actions;
    private double m_startTime;

    public DoAutoActions(AutoTrajectory actions, Intake intake) {
        m_actions = actions;
        m_intake = intake;
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
        double time = Timer.getFPGATimestamp() - m_startTime;
        return time - m_actions.getLastActionTime() > kAutoActionTime;
    }
}
