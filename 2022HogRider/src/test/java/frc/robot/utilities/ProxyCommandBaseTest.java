package frc.robot.utilities;

import org.junit.Test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.junit.Assert.*;

public final class ProxyCommandBaseTest {
    private static class MockCommand extends CommandBase {
            private boolean interrupted = false;
            private boolean markAsFinished = false;
            // Method Call Counters
            private int initialize = 0;
            private int execute = 0;
            private int end = 0;
            private int isFinished = 0;

            @Override
            public void initialize() {
                initialize += 1;
            }

            @Override
            public void execute() {
                execute += 1;
            }

            @Override
            public void end(boolean interrupted) {
                end += 1;
                this.interrupted = interrupted;
            }

            @Override
            public boolean isFinished() {
                isFinished += 1;
                return markAsFinished;
            }
    }

    private static class TestProxy extends ProxyCommandBase {
        private Command delegate;

        private TestProxy(Command delegate) {
            this.delegate = delegate;
        }

        @Override
        protected Command generateDelegate() {
            return delegate;
        }
    }

    @Test
    public void delegateCommandFinishes() {
        final var delegate = new MockCommand();
        final var proxy = new TestProxy(delegate);

        assertMockCommand(0, 0, 0, 0, false, delegate);

        proxy.initialize();
        assertMockCommand(1, 0, 0, 0, false, delegate);
        assertEquals(false, proxy.isFinished());

        proxy.execute();
        assertMockCommand(1, 1, 0, 1, false, delegate);
        assertEquals(false, proxy.isFinished());

        proxy.execute();
        assertMockCommand(1, 2, 0, 2, false, delegate);
        assertEquals(false, proxy.isFinished());

        delegate.markAsFinished = true;
        proxy.execute();
        assertMockCommand(1, 3, 1, 3, false, delegate);
        assertEquals(true, proxy.isFinished());
    }

    @Test
    public void proxyInterrupted() {
        final var delegate = new MockCommand();
        final var proxy = new TestProxy(delegate);

        assertMockCommand(0, 0, 0, 0, false, delegate);

        proxy.initialize();
        assertMockCommand(1, 0, 0, 0, false, delegate);
        assertEquals(false, proxy.isFinished());

        proxy.execute();
        assertMockCommand(1, 1, 0, 1, false, delegate);
        assertEquals(false, proxy.isFinished());

        proxy.execute();
        assertMockCommand(1, 2, 0, 2, false, delegate);
        assertEquals(false, proxy.isFinished());

        proxy.end(true);
        assertMockCommand(1, 2, 1, 2, true, delegate);
        assertEquals(true, proxy.isFinished());
    }

    @Test
    public void invalidExecute() {
        final var delegate = new MockCommand();
        final var proxy = new TestProxy(delegate);

        assertThrows(IllegalStateException.class, () -> proxy.execute());
    }

    @Test
    public void invalidEnd() {
        final var delegate = new MockCommand();
        final var proxy = new TestProxy(delegate);

        assertThrows(IllegalStateException.class, () -> proxy.end(true));
    }

    private static void assertMockCommand(
        int initialize,
        int execute,
        int end,
        int isFinished,
        boolean interrupted,
        MockCommand mock
        ) {
        assertEquals(initialize, mock.initialize);
        assertEquals(execute, mock.execute);
        assertEquals(end, mock.end);
        assertEquals(isFinished, mock.isFinished);
        assertEquals(interrupted, mock.interrupted);
    }
}
