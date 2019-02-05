

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.UltrasonicSensor;
import frc.robot.vision.VisionManager;
import frc.robot.subsystems.BoschMotor;
import frc.robot.subsystems.LEDStrip;
import frc.robot.subsystems.LiftSystem;

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

    //lift system
    public static LiftSystem liftSystem;

    //#endregion
    
    //sensors
    public static AHRS navX;
    public static UltrasonicSensor ultraSonicFront;

    public static LEDStrip ledStrip;

    public static void init()
    {
        m_frontLeft = new PWMVictorSPX(3);
        m_rearLeft = new PWMVictorSPX(2);
        m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    
        m_frontRight = new PWMVictorSPX(0);
        m_rearRight = new PWMVictorSPX(1);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

        driveBase = new DifferentialDrive(m_left, m_right); //create differential drive using the two speed controller groups

        liftSystem = new LiftSystem(7, 8,  4, 5);

        navX = new AHRS(SPI.Port.kMXP); //establish NavX sensor on the MXP port (12 pins on the roborio)
        ultraSonicFront = new UltrasonicSensor(0); //pass in analog pin for the sensor

        //ledStrip = new LEDStrip(4, 5, 6);
    }
}
