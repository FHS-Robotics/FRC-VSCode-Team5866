package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.commands.TeleOpDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.IntakeSystem;

import static frc.robot.Constants.*;

/**
 * This class makes all of the parts and subsystems that make up the
 * robot accessible throughout the code.
 */
public final class RobotMap {
      // region Subsystems
      public static final Drive m_drive; // Initialized below
      public static final IntakeSystem m_intake = new IntakeSystem(new CANSparkMax(kChIntake, MotorType.kBrushless));
      public static final Arm m_arm; // Initialized below
      public static final Elevator m_elevator; // Initialized below
      // endregion

      public static final DigitalInput limitUp = new DigitalInput(kChLimitUp);

      public static WPI_TalonFX m_frontLeft = new WPI_TalonFX(kChWheelFL);
      public static WPI_TalonFX m_frontRight = new WPI_TalonFX(kChWheelFR);
      public static WPI_TalonFX m_backLeft = new WPI_TalonFX(kChWheelBL);
      public static WPI_TalonFX m_backRight = new WPI_TalonFX(kChWheelBR);

      static {
            // Drive
            m_frontLeft.setInverted(true);
            m_backLeft.setInverted(true);

            m_frontLeft.setNeutralMode(NeutralMode.Brake);
            m_frontRight.setNeutralMode(NeutralMode.Brake);
            m_backLeft.setNeutralMode(NeutralMode.Brake);
            m_backRight.setNeutralMode(NeutralMode.Brake);

            m_drive = new Drive(m_frontLeft, m_frontRight, m_backLeft, m_backRight);

            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);

            // Arm
            CANSparkMax armMotor = new CANSparkMax(kChArm, MotorType.kBrushless);
            m_arm = new Arm(armMotor);

            // Elevator
            CANSparkMax elevatorMotor1 = new CANSparkMax(kChElevator1, MotorType.kBrushless);
            CANSparkMax elevatorMotor2 = new CANSparkMax(kChElevator2, MotorType.kBrushless);
            MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotor1, elevatorMotor2);
            m_elevator = new Elevator(elevatorMotors);
      }

      // region Commands
      public static final TeleOpDrive m_teleOpDrive = new TeleOpDrive(m_arm, m_elevator, m_intake, m_drive);
      // endregion
}
