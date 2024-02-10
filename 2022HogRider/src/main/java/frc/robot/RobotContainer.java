package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.TeleopCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.AutonomousCommandGenerator;

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
      private final AutonomousCommandGenerator m_autoCmdGenerator;
      private Command m_latestAutoCmd;
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
            m_autoCmdGenerator = new AutonomousCommandGenerator(m_arm, m_drive, m_intake);
            m_teleopCommand = new TeleopCommand(m_driverController, m_gunnerController, m_arm, m_drive, m_intake, m_elevator);
      }

      /**
       * Creates subsystems and stores them to the RobotContainer.
       */
      private void instantiateSubsystems() {
            CANSparkMax armMotor = new CANSparkMax(kChArm, MotorType.kBrushless);
            DigitalInput armLimitUp = new DigitalInput(kChLimitUp);
            m_arm = new Arm(armMotor, armLimitUp);
            m_arm.setDefaultCommand(new RunCommand(() -> m_arm.moveSafely(0), m_arm));

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
            AHRS gyro = new AHRS(kChGyro);
            m_drive = new Drive(driveFl, driveFr, gyro);
            m_drive.setDefaultCommand(new RunCommand(() -> m_drive.arcadeDrive(0, 0), m_drive));
            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);

            CANSparkMax elevatorMotor1 = new CANSparkMax(kChElevator1, MotorType.kBrushless);
            CANSparkMax elevatorMotor2 = new CANSparkMax(kChElevator2, MotorType.kBrushless);
            elevatorMotor1.setInverted(true);
            elevatorMotor2.setInverted(true);
            MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotor1, elevatorMotor2);
            m_elevator = new Elevator(elevatorMotors);
            m_elevator.setDefaultCommand(new RunCommand(() -> m_elevator.move(0), m_elevator));

            var intakeMotor = new CANSparkMax(kChIntake, MotorType.kBrushless);
            intakeMotor.setInverted(true);
            m_intake = new Intake(intakeMotor);
            m_intake.setDefaultCommand(new RunCommand(() -> m_intake.move(0), m_intake));
      }

      /**
       * Creates a new autonomous command that can be fetched
       * later with {@link RobotContainer#fetchAutonomousCommand()}.
       */
      public Command createAutonomousCommand() {
            return m_latestAutoCmd = m_autoCmdGenerator.generateCommand();
      }

      /**
       * Returns the last autonomous command generated
       * by {@link RobotContainer#createAutonomousCommand()}.
       */
      public Command fetchAutonomousCommand() {
            if (m_latestAutoCmd == null) {
                  throw new IllegalStateException("fetchAutonomousCommand() before createAutonomousCommand()");
            }
            return m_latestAutoCmd;
      }

      /**
       * Fetches the final instance of the {@see TeleopCommand}.
       */
      public TeleopCommand getTeleopCommand() {
            return m_teleopCommand;
      }
}
