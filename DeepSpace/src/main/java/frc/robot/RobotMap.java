

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.UltrasonicSensor;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

/**
 * This class contains all of the objects for the robot and a map of where they go
 */
public class RobotMap {
    public static DifferentialDrive m_myRobot_Front;
    public static DifferentialDrive m_myRobot_Back;
    
    public static UltrasonicSensor ultraSonicFront;

    public static void init()
    {
        m_myRobot_Front = new DifferentialDrive(new PWMVictorSPX(3), new PWMVictorSPX(0));
        m_myRobot_Back = new DifferentialDrive(new PWMVictorSPX(2), new PWMVictorSPX(1));

        ultraSonicFront = new UltrasonicSensor(0); //pass in analog pin for the sensor
    }
}
