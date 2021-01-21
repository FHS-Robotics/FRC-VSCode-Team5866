package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

    public static CANSparkMax m_leftBack;
    public static CANSparkMax m_leftMiddle;
    public static CANSparkMax m_leftFront;
    public static CANSparkMax m_rightBack;
    public static CANSparkMax m_rightMiddle;
    public static CANSparkMax m_rightFront;

    public static SpeedControllerGroup m_left;
    public static SpeedControllerGroup m_right;

    public static DifferentialDrive m_drive;

    public static void init() {
        m_leftBack = new CANSparkMax(4, MotorType.kBrushless);
        m_leftMiddle = new CANSparkMax(5, MotorType.kBrushless);
        m_leftFront = new CANSparkMax(6, MotorType.kBrushless);
        m_rightBack = new CANSparkMax(3, MotorType.kBrushless);
        m_rightMiddle = new CANSparkMax(2, MotorType.kBrushless);
        m_rightFront = new CANSparkMax(1, MotorType.kBrushless);

        m_left = new SpeedControllerGroup(m_leftBack, m_leftMiddle, m_leftFront);
        m_right = new SpeedControllerGroup(m_rightBack, m_rightMiddle, m_rightFront);

        m_drive = new DifferentialDrive(m_left, m_right);
    }
}
