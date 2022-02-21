package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.commands.TeleOpDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeSystem;

public final class RobotMap {

    public static WPI_TalonFX m_backLeft = new WPI_TalonFX(2);
    public static WPI_TalonFX m_backRight = new WPI_TalonFX(3);
    public static WPI_TalonFX m_frontLeft = new WPI_TalonFX(0);
    public static WPI_TalonFX m_frontRight = new WPI_TalonFX(1);

    public static IntakeSystem m_intake = new IntakeSystem(new CANSparkMax(4, MotorType.kBrushed));
    public static Arm m_arm = new Arm(new CANSparkMax(5, MotorType.kBrushless), 0);

    public static MotorControllerGroup m_left = new MotorControllerGroup(m_backLeft, m_frontLeft);
    public static MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_frontRight);

    public static DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public static TeleOpDrive m_teleOpDrive = new TeleOpDrive(OI.driverController, m_intake);

    static {
        m_drive.setMaxOutput(.5);
        m_drive.setDeadband(0.05);
    }
}