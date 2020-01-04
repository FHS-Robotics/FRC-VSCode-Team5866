package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import frc.robot.subsystems.*;

/**
 * RobotMap
 */
public class RobotMap {

    public static double length = 13.5;
    public static double width = 16.5;

    //#region swervedrive
    public static SwerveDrive m_drive;

    public static SwerveModule m_front;
    public static PWMVictorSPX m_frontSpeed;
    public static PWMVictorSPX m_frontAngle;

    public static SwerveModule m_backLeft;
    public static PWMVictorSPX m_backLeftSpeed;
    public static PWMVictorSPX m_backLeftAngle;

    public static SwerveModule m_backRight;
    public static PWMVictorSPX m_backRightSpeed;
    public static PWMVictorSPX m_backRightAngle;
    //#endregion

    public static void init() {

        m_frontSpeed = new PWMVictorSPX(3);
        m_frontAngle = new PWMVictorSPX(2);
        m_front = new SwerveModule(m_frontSpeed, m_frontAngle, 0, -58);

        m_backLeftSpeed = new PWMVictorSPX(5);
        m_backLeftAngle = new PWMVictorSPX(4);
        m_backLeft = new SwerveModule(m_backLeftSpeed, m_backLeftAngle, 1, 14);

        m_backRightSpeed = new PWMVictorSPX(0);
        m_backRightAngle = new PWMVictorSPX(1);
        m_backRight = new SwerveModule(m_backRightSpeed, m_backRightAngle, 2, 77);

        m_drive = new SwerveDrive(length, width, m_front, m_backLeft, m_backRight);
    }
}