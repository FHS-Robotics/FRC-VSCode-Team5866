package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.subsystems.TeleHook;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDStrip;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.VersaDrive;
/**
 * RobotMap
 */
public class RobotMap {

    //both teleHook motor
    public static TeleHook m_leftTeleHook;
    public static TeleHook m_rightTeleHook;
  
    //intake motor?
    public static Intake m_intake;

    //#region DriveBase
    public static Spark m_frontLeft;
    public static Spark m_backLeft;
    public static Spark m_frontRight;
    public static Spark m_backRight;

    /*public static PWMVictorSPX m_frontLeft;
    public static PWMVictorSPX m_backLeft;
    public static PWMVictorSPX m_frontRight;
    public static PWMVictorSPX m_backRight;*/

    public static AHRS gyro;

    //Solenoids connected to the wheel actuation pistons
    public static DoubleSolenoid act_solenoid;

    //the system will utilize a dual drive system to deal with defenders while being agile
    public static VersaDrive m_drive;
    public static PIDDrive m_pidDrive;
    //#endregion

    public static LimeLight limeLight;

    public static LEDStrip m_ledStrip;
    public static LEDStrip m_ledStrip2;

    public static void init() {

        m_leftTeleHook = new TeleHook(3,4);
        m_rightTeleHook = new TeleHook(5,6);

        m_frontLeft = new Spark(8); //*
        m_backLeft = new Spark(9); //*

        m_frontRight = new Spark(1); //*
        m_backRight = new Spark(0); //*
        /*m_frontLeft = new PWMVictorSPX(8); //*
        m_backLeft = new PWMVictorSPX(9); //*

        m_frontRight = new PWMVictorSPX(1); //*
        m_backRight = new PWMVictorSPX(0);
*/
        m_intake = new Intake(7,1); //*

        act_solenoid = new DoubleSolenoid(1, 0); //*

        m_drive = new VersaDrive(act_solenoid, m_frontLeft, m_backLeft, m_frontRight, m_backRight);
        m_pidDrive = new PIDDrive();
        m_pidDrive.enable();

        gyro = new AHRS();
        limeLight = new LimeLight();

        m_ledStrip = new LEDStrip(2, 150);
        m_ledStrip.setRGB(255, 255, 255);
    }
}