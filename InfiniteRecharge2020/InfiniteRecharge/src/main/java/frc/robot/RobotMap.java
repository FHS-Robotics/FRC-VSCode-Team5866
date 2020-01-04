package frc.robot;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.subsystems.VersaDrive;

/**
 * RobotMap
 */
public class RobotMap {

    //#region DriveBase
    public static PWMTalonSRX m_frontLeft;
    public static PWMTalonSRX m_backLeft;
    public static PWMTalonSRX m_frontRight;
    public static PWMTalonSRX m_backRight;

    //Solenoids connected to the wheel actuation pistons
    public static Solenoid act_frontLeft;
    public static Solenoid act_backLeft;
    public static Solenoid act_frontRight;
    public static Solenoid act_backRight;

    //the system will utilize a dual drive system to deal with defenders while being agile
    public static VersaDrive m_drive;
    //#endregion

    public static void init() {

        m_frontLeft = new PWMTalonSRX(0); //*
        m_backLeft = new PWMTalonSRX(1); //*

        m_frontRight = new PWMTalonSRX(2); //*
        m_backRight = new PWMTalonSRX(3); //*

        m_drive = new VersaDrive(act_frontLeft, act_backLeft, act_frontRight, act_backRight, m_frontLeft, m_backLeft, m_frontRight, m_backRight);
    }
}