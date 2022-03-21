package frc.robot.utilities;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class ProxyCommandBase extends CommandBase {
    private Command delegate = null;

    @Override
    public void initialize() {
        delegate = generateDelegate();
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
