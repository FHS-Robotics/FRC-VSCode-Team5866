package frc.robot.subsystems;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.DIOSim;
import frc.robot.Constants;
import frc.robot.MockMotorController;
import org.junit.*;

import static org.junit.Assert.*;

public class ArmTest {
    private final static double DELTA = 1e-2;
    private static Arm m_arm;
    private static MockMotorController m_motorSim;
    private static DIOSim m_limitUpSim;

    /**
     * Initializes testing, is static because only one Digital Input
     * may be made at a time.
     */
    @BeforeClass
    public static void setup() {
        assert HAL.initialize(500, 0);
        // Create a fake MotorController for testing
        m_motorSim = new MockMotorController();
        // Create a "real" Digital Input
        DigitalInput limitUp = new DigitalInput(0);
        m_arm = new Arm(m_motorSim, limitUp);
        // This DIOSim allows us to give "fake" outputs to the
        // "real" DigitalInput.
        // This is pretty nifty for testing.
        m_limitUpSim = new DIOSim(limitUp);
    }

    @Test
    public void upperLimitBreaksUpwards() {
        m_limitUpSim.setValue(!Constants.kInvertLimitUp);
        m_arm.moveSafely(1);

        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(0, m_motorSim.setSpeeds.get(0), DELTA);

        m_motorSim.resetMock();
    }

    @Test
    public void upperLimitMovesDownwards() {
        m_limitUpSim.setValue(!Constants.kInvertLimitUp);
        m_arm.moveSafely(-1);

        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(-Constants.kSpeedArm, m_motorSim.setSpeeds.get(0), DELTA);

        m_motorSim.resetMock();
    }

    @Test
    public void noUpperLimitMovesUpwards() {
        m_limitUpSim.setValue(Constants.kInvertLimitUp);
        m_arm.moveSafely(1);

        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(Constants.kSpeedArm, m_motorSim.setSpeeds.get(0), DELTA);

        m_motorSim.resetMock();
    }

    @Test
    public void noUpperLimitMovesDownwards() {
        m_limitUpSim.setValue(Constants.kInvertLimitUp);
        m_arm.moveSafely(-1);

        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(-Constants.kSpeedArm, m_motorSim.setSpeeds.get(0), DELTA);

        m_motorSim.resetMock();
    }
}
