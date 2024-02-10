package frc.robot.utilities;

import org.junit.Test;
import frc.robot.utilities.AutoStrategy.Action;

import static org.junit.Assert.*;

import org.junit.Before;

public final class AutoStrategyTest {
    private final static double DELTA = 1e-2;
    private Action intakeAction;
    private Action dispenseAction;
    private Action noopAction;
    private AutoStrategy trajectory;

    @Before
    public void setup() {
        intakeAction = new Action(Action.ActionType.IntakeBall, -1);
        dispenseAction = new Action(Action.ActionType.DispenseBall, 3);
        noopAction = new Action(Action.ActionType.NoOp, 4);

        trajectory = new AutoStrategy(
            null, // The filename isn't applicable in this test.
            intakeAction,
            dispenseAction,
            noopAction
        );
    }

    @Test
    public void samplesValidActions() {
        assertEquals(null, trajectory.sampleNearestAction(-10));
        assertEquals(null, trajectory.sampleNearestAction(-1.001));
        assertEquals(intakeAction, trajectory.sampleNearestAction(2));
        assertEquals(intakeAction, trajectory.sampleNearestAction(2.999));
        assertEquals(dispenseAction, trajectory.sampleNearestAction(3));
        assertEquals(dispenseAction, trajectory.sampleNearestAction(3.999));
        assertEquals(noopAction, trajectory.sampleNearestAction(4));
        assertEquals(noopAction, trajectory.sampleNearestAction(4.999));
        assertEquals(noopAction, trajectory.sampleNearestAction(10));
    }

    @Test
    public void correctFirstActionTime() {
        assertEquals(-1, trajectory.getFirstActionTime(), DELTA);
    }

    @Test
    public void correctLastActionTime() {
        assertEquals(4, trajectory.getLastActionTime(), DELTA);
    }
}
