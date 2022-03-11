package frc.robot.subsystems;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import frc.robot.Constants;
import frc.robot.MockMotorController;

public class ElevatorTest {
    private final static double DELTA = 1e-2;
    private final MockMotorController m_motorSim = new MockMotorController();
    private final Elevator m_elevator = new Elevator(m_motorSim);

    @After
    public void teardown() {
        m_motorSim.resetMock();
    }
    
    @Test
    public void movesUpwards() {
        m_elevator.move(1);
        
        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(Constants.kSpeedElevator, m_motorSim.setSpeeds.get(0), DELTA);
    }
    
    @Test
    public void movesDownwards() {
        m_elevator.move(-1);
        
        assertEquals(1, m_motorSim.setSpeeds.size());
        assertEquals(-Constants.kSpeedElevator, m_motorSim.setSpeeds.get(0), DELTA);
    }
    
    @Test
    public void breaks() {
        double[] testValues = new double[] { 0.05, -0.05 };
        for (double test : testValues) {
            m_elevator.move(test);
            
            assertEquals(1, m_motorSim.setSpeeds.size());
            assertEquals(0, m_motorSim.setSpeeds.get(0), 0);
            m_motorSim.resetMock();
        }
    }
}
