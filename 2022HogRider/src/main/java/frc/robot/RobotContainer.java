package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.*;

/**
 * This class makes all of the parts and subsystems that make up the
 * robot accessible throughout the code.
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
      private final Command m_teleopCommand;
      // endregion

      public RobotContainer() {
            configureSubsystems();

            // We initialize the autonomous command after m_drive
            // has been constructed.
            m_autonomousCommand = new AutonomousCommand(m_drive, m_intake);
            m_teleopCommand = createTeleopCommand();
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
            driveBl.follow(driveFl);
            driveBr.follow(driveFr);
            driveFl.setInverted(true);
            driveBl.setInverted(true);
            driveFl.setNeutralMode(NeutralMode.Brake);
            driveFr.setNeutralMode(NeutralMode.Brake);
            driveBl.setNeutralMode(NeutralMode.Brake);
            driveBr.setNeutralMode(NeutralMode.Brake);
            driveBl.close();
            driveBr.close();
            ADXRS450_Gyro gyro = new ADXRS450_Gyro(kChGyro);
            m_drive = new Drive(driveFl, driveFr, gyro);
            m_drive.getDrive().setMaxOutput(.5);
            m_drive.getDrive().setDeadband(0.5);

            CANSparkMax elevatorMotor1 = new CANSparkMax(kChElevator1, MotorType.kBrushless);
            CANSparkMax elevatorMotor2 = new CANSparkMax(kChElevator2, MotorType.kBrushless);
            MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotor1, elevatorMotor2);
            m_elevator = new Elevator(elevatorMotors);

            m_intake = new Intake(new CANSparkMax(kChIntake, MotorType.kBrushless));
      }

      private Command createTeleopCommand() {
            return new FunctionalCommand(
                  // onInit
                  () -> {},
                  // onExecute
                  () -> {
                        m_drive.arcadeDrive(-m_driverController.getLeftY(), m_driverController.getRightX());
                        m_arm.moveSafely(-m_gunnerController.getLeftY());
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
                        if (m_gunnerController.getAButton()) {
                              m_intake.move(1);
                        } else if (m_gunnerController.getBButton()) {
                              m_intake.move(-1);
                        } else {
                              m_intake.move(0);
                        }
                  },
                  // onEnd
                  (interrupted) -> {
                        m_drive.arcadeDrive(0, 0);
                        m_arm.moveSafely(0);
                        m_elevator.move(0);
                        m_intake.move(0);
                  },
                  // isFinished
                  () -> {
                        return false;
                  },
                  m_arm, m_drive, m_elevator, m_intake
            );
      }

      public AutonomousCommand getAutonomousCommand() {
            return m_autonomousCommand;
      }

      public Command getTeleopCommand() {
            return m_teleopCommand;
      }
}
