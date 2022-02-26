package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.commands.TeleOpDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.utilities.Settings;

/**
 * This class makes all of the parts and subsystems that make up the
 * robot accessible throughout the code.
 */
public final class RobotMap {
      // region Subsystems
      public static final Drive<WPI_TalonFX> m_drive;
      public static final IntakeSystem m_intake = new IntakeSystem(new CANSparkMax(Settings.CH_INTAKE(), MotorType.kBrushed));
      public static final Arm m_arm = new Arm(new WPI_TalonFX(Settings.CH_ARM()), 0);
      public static final Elevator m_elevator = new Elevator(new WPI_TalonFX(Settings.CH_ELEVATOR()));
      // endregion

      static {
            WPI_TalonFX m_frontLeft = new WPI_TalonFX(Settings.CH_W_FL());
            WPI_TalonFX m_frontRight = new WPI_TalonFX(Settings.CH_W_FR());
            WPI_TalonFX m_backLeft = new WPI_TalonFX(Settings.CH_W_BL());
            WPI_TalonFX m_backRight = new WPI_TalonFX(Settings.CH_W_BR());

            m_drive = new Drive<WPI_TalonFX>(m_frontLeft, m_frontRight, m_backLeft, m_backRight);

            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);
      }

      // region Commands
      public static final TeleOpDrive<WPI_TalonFX> m_teleOpDrive = new TeleOpDrive<WPI_TalonFX>(m_arm, m_elevator, m_intake, m_drive);
      // endregion
}
