package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.*;

import java.util.function.Function;

/**
 * This class makes all of the parts and subsystems that make up the
 * robot accessible throughout the code.
 */
public final class RobotContainer {
      private final Robot m_robot;

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
      private final AutonomousCommand m_autonomousCommand = new AutonomousCommand();
      // endregion

      public RobotContainer(Robot robot) {
            m_robot = robot;

            configureSubsystems();
            configureButtonBindings();
      }
      
      /**
       * This creates all the motors and sensors objects, and this
       * passes them to subsystems to instantiate them.
       */
      private void configureSubsystems() {
            CANSparkMax armMotor = new CANSparkMax(kChArm, MotorType.kBrushless);
            DigitalInput armLimitUp = new DigitalInput(kChLimitUp);
            m_arm = new Arm(armMotor, armLimitUp);

            WPI_TalonFX driveFl = new WPI_TalonFX(kChWheelFL);
            WPI_TalonFX driveFr = new WPI_TalonFX(kChWheelFR);
            WPI_TalonFX driveBl = new WPI_TalonFX(kChWheelBL);
            WPI_TalonFX driveBr = new WPI_TalonFX(kChWheelBR);
            driveFl.setInverted(true);
            driveBl.setInverted(true);
            driveFl.setNeutralMode(NeutralMode.Brake);
            driveFr.setNeutralMode(NeutralMode.Brake);
            driveBl.setNeutralMode(NeutralMode.Brake);
            driveBr.setNeutralMode(NeutralMode.Brake);
            m_drive = new Drive(driveFl, driveFr, driveBl, driveBr);
            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);

            CANSparkMax elevatorMotor1 = new CANSparkMax(kChElevator1, MotorType.kBrushless);
            CANSparkMax elevatorMotor2 = new CANSparkMax(kChElevator2, MotorType.kBrushless);
            MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotor1, elevatorMotor2);
            m_elevator = new Elevator(elevatorMotors);

            m_intake = new Intake(new CANSparkMax(kChIntake, MotorType.kBrushless));
      }

      /**
       * This hooks up all the buttons to commands for teleop
       */
      private void configureButtonBindings() {
            // inTeleop() creates a new function from a runnable
            // so that the runnable's code will only execute when
            // the robot is in teleop mode.
            Function<Runnable, Runnable> inTeleop = (run) -> {
                  return () -> {
                        if (m_robot.isTeleopEnabled()) {
                              run.run();
                        }
                  };
            };

            // region Driver Controller

            // Lambda (function) that arcade drives
            // according to the driver controller.
            Runnable driveFn = () -> m_drive.arcadeDrive(-m_driverController.getLeftY(), m_driverController.getRightX());
            m_drive.setDefaultCommand(
                  new RunCommand(inTeleop.apply(driveFn), m_drive)
            );

            // endregion

            // region Gunner Controller

            Runnable armFn = () -> m_arm.moveSafely(-m_gunnerController.getLeftY());
            m_arm.setDefaultCommand(new RunCommand(inTeleop.apply(armFn), m_arm));

            Runnable elevatorFn = () -> {
                  switch(m_gunnerController.getPOV()) {
                        case 0:
                              m_elevator.move(1);
                              break;
                        case 180:
                              m_elevator.move(-1);
                              break;
                        default:
                              m_elevator.move(0);
                  }
            };
            m_elevator.setDefaultCommand(new RunCommand(inTeleop.apply(elevatorFn), m_elevator));

            var gunnerBtnA = new JoystickButton(m_gunnerController, Button.kA.value);
            var gunnerBtnB = new JoystickButton(m_gunnerController, Button.kB.value);

            gunnerBtnA
            // Makes an imaginary Trigger that is active when
            // the A button is pressed and the B button is released
                  .and(gunnerBtnB.negate())
                  .whileActiveContinuous(() -> m_intake.move(1), m_intake);
            gunnerBtnB
                  .and(gunnerBtnA.negate()) 
                  .whileActiveContinuous(() -> m_intake.move(-1), m_intake);
                  
            // endregion
      }

      public AutonomousCommand getAutonomousCommand() {
            return m_autonomousCommand;
      }
}
