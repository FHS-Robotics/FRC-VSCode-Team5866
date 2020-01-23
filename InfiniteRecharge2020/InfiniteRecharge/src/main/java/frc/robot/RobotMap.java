package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.subsystems.TeleHook;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.VersaDrive;

/**
 * RobotMap
 */
public class RobotMap {

    //both teleHook motor
    public static TeleHook m_TeleHooks;
    public static TeleHook m_TeleCables;
  
    //intake motor
    public static Intake m_intake;

    //#region DriveBase
    public static PWMTalonSRX m_frontLeft;
    public static PWMTalonSRX m_backLeft;
    public static PWMTalonSRX m_frontRight;
    public static PWMTalonSRX m_backRight;

    public static AHRS gyro;

    //Solenoids connected to the wheel actuation pistons
    public static Solenoid act_frontLeft;
    public static Solenoid act_backLeft;
    public static Solenoid act_frontRight;
    public static Solenoid act_backRight;

    //the system will utilize a dual drive system to deal with defenders while being agile
    public static VersaDrive m_drive;
    public static PIDDrive m_pidDrive;
    //#endregion

    public static LimeLight limeLight;

    public static void init() {

        m_TeleHooks = new TeleHook(144 ,0, 0.0 , 0.0);
        m_TeleCables = new TeleHook(9 ,0, 0.0 , 0.0 );

        m_frontLeft = new PWMTalonSRX(0); //*Not real number yet
        m_backLeft = new PWMTalonSRX(1); //*

        m_frontRight = new PWMTalonSRX(2); //*
        m_backRight = new PWMTalonSRX(3); //*

        m_intake = new Intake(4,0); //*

        act_frontLeft = new Solenoid(0); //*
        act_backLeft = new Solenoid(1); //*
        act_frontRight = new Solenoid(2); //*
        act_backRight = new Solenoid(3); //*

        m_drive = new VersaDrive(act_frontLeft, act_backLeft, act_frontRight, act_backRight, m_frontLeft, m_backLeft, m_frontRight, m_backRight);
        m_pidDrive = new PIDDrive();

        gyro = new AHRS();
        limeLight = new LimeLight();
    }
}