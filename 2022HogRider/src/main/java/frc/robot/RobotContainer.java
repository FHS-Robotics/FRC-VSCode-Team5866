package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.TeleopCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the subsystems of the robot, deals
 * with the configuration of robot parts and holds the
 * autonomous and teleop commands.
 */
public final class RobotContainer {
      // region Controllers
      private final XboxController m_driverController = new XboxController(0);
      private final XboxController m_gunnerController = new XboxController(1);
      // endregion

      // region Subsystems
      private Arm m_arm;
      private Drive m_drive;
      private Elevator m_elevator;
      private Intake m_intake;
      // endregion

      // region Commands
      private final AutonomousCommand m_autonomousCommand;
      private final TeleopCommand m_teleopCommand;
      // endregion

      /**
       * Our front wheels are managed by {@see Drive}, but our back
       * wheels are set up to `follow()` our front wheels. This leaves
       * us with two motor controller objects that RobotContainer has
       * to directly manage.
       *
       * We must keep a reference to our back wheels, so they are not
       * garbage collected. If they are garbage collected, they disappear
       * from the simulation GUI and (possibly?) no longer have the effect of
       * follow()'ing the front wheels output.
       */
      private final List<AutoCloseable> m_objectRefs = new ArrayList<>();

      public RobotContainer() {
            instantiateSubsystems();
            CommandScheduler.getInstance().registerSubsystem(m_arm, m_drive, m_elevator, m_intake);

            // configureSubsystems() has made all of the subsystems.
            m_autonomousCommand = new AutonomousCommand(m_arm, m_drive, m_intake);
            m_teleopCommand = new TeleopCommand(m_driverController, m_gunnerController, m_arm, m_drive, m_intake, m_elevator);
      }

      /**
       * Creates subsystems and stores them to the RobotContainer.
       */
      private void instantiateSubsystems() {
            CANSparkMax armMotor = new CANSparkMax(kChArm, MotorType.kBrushless);
            DigitalInput armLimitUp = new DigitalInput(kChLimitUp);
            m_arm = new Arm(armMotor, armLimitUp);

            // Four Motor-Controllers make up our tank-drive.
            WPI_TalonFX driveFl = new WPI_TalonFX(kChWheelFL);
            WPI_TalonFX driveFr = new WPI_TalonFX(kChWheelFR);
            WPI_TalonFX driveBl = new WPI_TalonFX(kChWheelBL);
            WPI_TalonFX driveBr = new WPI_TalonFX(kChWheelBR);
            driveBl.follow(driveFl);
            driveBr.follow(driveFr);
            driveFl.setInverted(true);
            driveBl.setInverted(true);
            driveFl.setNeutralMode(NeutralMode.Brake);
            driveFr.setNeutralMode(NeutralMode.Brake);
            driveBl.setNeutralMode(NeutralMode.Brake);
            driveBr.setNeutralMode(NeutralMode.Brake);
            // We hold a reference to back motor-controllers,
            // so they still alive and able to follow front
            // motor-controllers.
            m_objectRefs.add(driveBl);
            m_objectRefs.add(driveBr);
            ADXRS450_Gyro gyro = new ADXRS450_Gyro(kChGyro);
            m_drive = new Drive(driveFl, driveFr, gyro);
            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);

            CANSparkMax elevatorMotor1 = new CANSparkMax(kChElevator1, MotorType.kBrushless);
            CANSparkMax elevatorMotor2 = new CANSparkMax(kChElevator2, MotorType.kBrushless);
            MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotor1, elevatorMotor2);
            m_elevator = new Elevator(elevatorMotors);

            var intakeMotor = new CANSparkMax(kChIntake, MotorType.kBrushless);
            m_intake = new Intake(intakeMotor);
      }

      /**
       * Fetches the final instance of the {@see AutonomousCommand}.
       */
      public AutonomousCommand getAutonomousCommand() {
            return m_autonomousCommand;
      }

      /**
       * Fetches the final instance of the {@see TeleopCommand}.
       */
      public TeleopCommand getTeleopCommand() {
            return m_teleopCommand;
      }
}
