package frc.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Actuator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDStrip;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.PIDVisionDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VersaDrive;
/**
 * RobotMap
 */
public class RobotMap {

    //Climb system
    public static Climber m_climber;

    public static Actuator m_actuator;
  
    //intake motor?
    public static Intake m_intake;

    public static Shooter m_shooter;

    public static ColorWheel m_ColorWheel;

    //#region DriveBase
    /*public static PWMVictorSPX m_frontLeft;
    public static PWMVictorSPX m_backLeft;
    public static PWMVictorSPX m_frontRight;
    public static PWMVictorSPX m_backRight;*/

    public static WPI_TalonFX m_frontLeft;
    public static WPI_TalonFX m_backLeft;
    public static WPI_TalonFX m_frontRight;
    public static WPI_TalonFX m_backRight;

    public static AHRS gyro;

    //Solenoids connected to the wheel actuation pistons
    public static DoubleSolenoid act_solenoidLeft;
    public static DoubleSolenoid act_solenoidRight;

    //the system will utilize a dual drive system to deal with defenders while being agile
    public static VersaDrive m_drive;
    public static PIDDrive m_pidDrive;
    public static PIDVisionDrive m_visionDrive;
    //#endregion

    public static LimeLight limeLight;

    public static Spark shootTemp;

    public static LEDStrip m_ledStrip;
    public static LEDStrip m_ledStrip2;

    public static Orchestra orchestra;

    public static void init() {

        m_climber = new Climber(2, 4, 5);

        m_actuator = new Actuator(8);

        /*m_frontLeft = new PWMVictorSPX(9); //*
        m_backLeft = new PWMVictorSPX(6); //*

        m_frontRight = new PWMVictorSPX(7); //*
        m_backRight = new PWMVictorSPX(5); //** */

        m_frontLeft = new WPI_TalonFX(0);
        m_backLeft = new WPI_TalonFX(1);

        m_frontRight = new WPI_TalonFX(3);
        m_backRight = new WPI_TalonFX(2);
        
        m_intake = new Intake(0);
        //m_shooter = new Shooter(1, 1, 0, 0);
        shootTemp = new Spark(1);

        m_ColorWheel = new ColorWheel(3); //*

        act_solenoidLeft = new DoubleSolenoid(1, 0); //*
        act_solenoidRight = new DoubleSolenoid(2, 3);

        m_drive = new VersaDrive(act_solenoidLeft, act_solenoidRight, m_frontLeft, m_backLeft, m_frontRight, m_backRight);
        m_pidDrive = new PIDDrive();

        m_visionDrive = new PIDVisionDrive();
        
        gyro = new AHRS();
        gyro.zeroYaw();
        limeLight = new LimeLight();
        limeLight.ledOff();

        orchestra = new Orchestra();
                
        orchestra.addInstrument(m_backLeft);
        orchestra.addInstrument(m_frontLeft);
        orchestra.addInstrument(m_backRight);
        orchestra.addInstrument(m_frontRight);

        m_ledStrip = new LEDStrip(4, 180);
        //m_ledStrip2 = new LEDStrip(5, 60);
    }
}