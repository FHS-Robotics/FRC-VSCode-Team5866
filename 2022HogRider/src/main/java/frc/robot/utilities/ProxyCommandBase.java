package frc.robot.utilities;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class ProxyCommandBase extends CommandBase {
    private Command delegate = null;

    /**
     * Must be called before {@link CommandBase#schedule()} or {@link CommandScheduler#schedule(Command...)}
     *
     * This generates a "real" command that the proxy will run when scheduled.
     */
    public void resolveDelegate() {
        delegate = generateDelegate();
        m_requirements.clear();
        m_requirements.addAll(delegate.getRequirements());
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return m_requirements;
    }

    @Override
    public void initialize() {
        if (delegate == null) {
            throw new IllegalStateException("initialize() before a call to resolveDelegate()");
        }
        delegate.initialize();
    }

    @Override
    public void execute() {
        if (delegate == null) {
            throw new IllegalStateException("Delegate command has already finished.");
        }
        delegate.execute();
        if (delegate.isFinished()) {
            delegate.end(false);
            delegate = null;
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            if (delegate == null) {
                throw new IllegalStateException("Delegated command must be running to interrupt.");
            }
            delegate.end(true);
            delegate = null;
        }
    }

    @Override
    public boolean isFinished() {
        return delegate == null;
    }

    /**
     * Ran when the command is being initialized.
     *
     * @return the command to be substituted for.
     */
    abstract protected Command generateDelegate();
}
