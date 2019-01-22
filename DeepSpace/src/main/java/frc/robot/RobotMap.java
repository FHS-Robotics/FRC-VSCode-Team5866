

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.subsystems.UltrasonicSensor;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

import edu.wpi.first.wpilibj.MotorSafety;

/**
 * This class contains all of the objects for the robot and a map of where they go
 */
public class RobotMap {
    //#region DriveBase
    public static DifferentialDrive driveBase;

    //left motors
    private static PWMVictorSPX m_frontLeft;
    private static PWMVictorSPX m_rearLeft;
    private static SpeedControllerGroup m_left;

    //right motors
    private static PWMVictorSPX m_frontRight;
    private static PWMVictorSPX m_rearRight;
    private static SpeedControllerGroup m_right;
    //#endregion
    
    public static UltrasonicSensor ultraSonicFront;

    public static void init()
    {
        m_frontLeft = new PWMVictorSPX(3);
        m_rearLeft = new PWMVictorSPX(2);
        m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    
        m_frontRight = new PWMVictorSPX(0);
        m_rearRight = new PWMVictorSPX(1);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

        driveBase = new DifferentialDrive(m_left, m_right); //create differential drive using the two speed controller groups

        ultraSonicFront = new UltrasonicSensor(0); //pass in analog pin for the sensor
    }
}
