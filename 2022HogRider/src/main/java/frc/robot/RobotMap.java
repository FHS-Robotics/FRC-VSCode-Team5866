package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.commands.AutonomousDrive;
import frc.robot.commands.TeleOpDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.IntakeSystem;

/**
 * This class makes all of the parts and subsystems that make up the
 * robot accessible throughout the code.
 */
public final class RobotMap {
      // region Subsystems
      public static final Drive<WPI_TalonFX> m_drive;
      public static final IntakeSystem m_intake = new IntakeSystem(new CANSparkMax(4, MotorType.kBrushed));
      public static final Arm m_arm = new Arm(new CANSparkMax(5, MotorType.kBrushless), 0);
      // endregion

      static {
            WPI_TalonFX m_frontLeft = new WPI_TalonFX(0);
            WPI_TalonFX m_frontRight = new WPI_TalonFX(1);
            WPI_TalonFX m_backLeft = new WPI_TalonFX(2);
            WPI_TalonFX m_backRight = new WPI_TalonFX(3);

            m_drive = new Drive<WPI_TalonFX>(m_frontLeft, m_frontRight, m_backLeft, m_backRight);

            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);
      }

      // region Commands
      public static final TeleOpDrive<WPI_TalonFX> m_teleOpDrive = new TeleOpDrive<WPI_TalonFX>(m_intake, m_drive);

      public static final AutonomousDrive<WPI_TalonFX> m_autonomousDrive = new AutonomousDrive<WPI_TalonFX>(m_intake,
                  m_drive);
      // endregion
}
