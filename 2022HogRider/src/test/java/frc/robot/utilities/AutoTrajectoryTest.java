package frc.robot.utilities;

import org.junit.Test;
import frc.robot.utilities.AutoTrajectory.AutoAction;

import static org.junit.Assert.*;

import org.junit.Before;

public final class AutoTrajectoryTest {
    private final static double DELTA = 1e-2;
    private AutoAction intakeAction;
    private AutoAction dispenseAction;
    private AutoAction noopAction;
    private AutoTrajectory trajectory;

    @Before
    public void setup() {
        intakeAction = new AutoAction(AutoAction.Type.IntakeBall, 2);
        dispenseAction = new AutoAction(AutoAction.Type.DispenseBall, 3);
        noopAction = new AutoAction(AutoAction.Type.NoOp, 4);

        trajectory = new AutoTrajectory(
            null, // The filename isn't applicable in this test.
            intakeAction,
            dispenseAction,
            noopAction
        );
    }

    @Test
    public void samplesValidActions() {
        assertEquals(null, trajectory.sampleNearestAction(1));
        assertEquals(null, trajectory.sampleNearestAction(1.999));
        assertEquals(intakeAction, trajectory.sampleNearestAction(2));
        assertEquals(intakeAction, trajectory.sampleNearestAction(2.999));
        assertEquals(dispenseAction, trajectory.sampleNearestAction(3));
        assertEquals(dispenseAction, trajectory.sampleNearestAction(3.999));
        assertEquals(noopAction, trajectory.sampleNearestAction(4));
        assertEquals(noopAction, trajectory.sampleNearestAction(4.999));
        assertEquals(noopAction, trajectory.sampleNearestAction(10));
    }

    @Test
    public void correctLastActionTime() {
        assertEquals(4, trajectory.getLastActionTime(), DELTA);
    }
}
