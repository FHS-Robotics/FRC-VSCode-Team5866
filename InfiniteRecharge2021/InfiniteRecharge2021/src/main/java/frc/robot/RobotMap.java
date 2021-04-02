package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.Subsystems.DriveBase;
import frc.robot.Subsystems.IntakeSystem;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.PIDShooter;
import frc.robot.Subsystems.PIDTurret;
import frc.robot.Subsystems.Shooter;

public class RobotMap {

    
    //#region Motor Constants
    private static final int c_leftFrontNum = 7;
    private static final int c_leftMiddleNum = 8;
    private static final int c_leftBacktNum = 9;
    private static final int c_rightFrontNum = 5;
    private static final int c_rightMiddleNum = 4;
    private static final int c_rightBacktNum = 1;

    private static final int c_skirtNum = 6;
    private static final int c_stageOneNum = 3;
    private static final int c_stageTwoNum = 2;

    private static final int c_shootLeftNum = 10;
    private static final int c_shootRightNum = 11;
    private static final int c_turretNum = 12;

    public static final double ksVolts = 0.22; ////////
    public static final double kvVoltSecondsPerMeter = 1.98; ////////
    public static final double kaVoltSecondsSquaredPerMeter = 0.2; ///////
 
    public static final double kEncoderDistancePerPulse = 0.5; ///////
    public static final double kEncoderVelocityPerRPM = 0.5; ///////

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 8.5; ///////

    public static final double kTrackwidthMeters = 0.69; ///////
    public static final DifferentialDriveKinematics kDriveKinematics =
    new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3; ///////
    public static final double kMaxAccelerationMetersPerSecondSquared = 3; //////

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
    //#endregion

    public static AHRS m_gyro;

    public static CANSparkMax m_leftBack;
    public static CANSparkMax m_leftMiddle;
    public static CANSparkMax m_leftFront;
    public static CANSparkMax m_rightBack;
    public static CANSparkMax m_rightMiddle;
    public static CANSparkMax m_rightFront;

    public static SpeedControllerGroup m_left;
    public static SpeedControllerGroup m_right;

    public static DifferentialDrive m_diffDrive;
    public static DriveBase m_drive;

    public static CANSparkMax in_skirt;
    public static CANSparkMax in_stageOne;
    public static CANSparkMax in_stageTwo;
    public static IntakeSystem m_intake;

    public static TalonFX shoot_right;
    public static TalonFX shoot_left;
    public static Shooter m_shooter;
    public static PIDShooter m_pidShooter;

    public static CANSparkMax t_motor;
    public static PIDTurret m_turret;

    public static Limelight limeLight;    


    public static void init() {
        m_gyro = new AHRS();
        
        m_leftBack = new CANSparkMax(c_leftBacktNum, MotorType.kBrushless);
        m_leftMiddle = new CANSparkMax(c_leftMiddleNum, MotorType.kBrushless);
        m_leftFront = new CANSparkMax(c_leftFrontNum, MotorType.kBrushless);
        m_rightBack = new CANSparkMax(c_rightBacktNum, MotorType.kBrushless);
        m_rightMiddle = new CANSparkMax(c_rightMiddleNum, MotorType.kBrushless);
        m_rightFront = new CANSparkMax(c_rightFrontNum, MotorType.kBrushless);

        m_left = new SpeedControllerGroup(m_leftBack, m_leftMiddle, m_leftFront);
        m_right = new SpeedControllerGroup(m_rightBack, m_rightMiddle, m_rightFront);

        m_diffDrive = new DifferentialDrive(m_left, m_right);
        m_drive = new DriveBase(m_left, m_right, m_gyro, m_leftMiddle, m_rightMiddle);

        in_skirt = new CANSparkMax(c_skirtNum, MotorType.kBrushless);
        in_stageOne = new CANSparkMax(c_stageOneNum, MotorType.kBrushless);
        in_stageTwo = new CANSparkMax(c_stageTwoNum, MotorType.kBrushless);
        m_intake = new IntakeSystem(in_skirt, in_stageOne, in_stageTwo);

        shoot_left = new TalonFX(c_shootLeftNum);
        shoot_right = new TalonFX(c_shootRightNum);
        m_shooter = new Shooter(shoot_left, shoot_right);
        m_pidShooter = new PIDShooter();
        m_pidShooter.enable();

        t_motor = new CANSparkMax(c_turretNum, MotorType.kBrushed);
        m_turret = new PIDTurret(t_motor);
        m_turret.enable();

        limeLight = new Limelight();
    }
}
