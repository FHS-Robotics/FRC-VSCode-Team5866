package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Debugging;
import frc.robot.utilities.SimUtils;

import static frc.robot.Constants.*;

import java.util.List;

/**
 * Controls robot movement. Uses WPI_TalonFX's integrated
 * sensors to estimate the robot's position on the field.
 * It also sends SmartDashboard it's estimated location.
 */
public final class Drive extends SubsystemBase {
      private final WPI_TalonFX m_left, m_right;
      private final AHRS m_gyro;
      private final DifferentialDrive m_drive;

      private final DifferentialDriveOdometry m_odometry;
      private final Field2d m_field;

      // Simulation Objects
      private DifferentialDrivetrainSim m_driveSim;

      /**
       * Creates a new drive train.
       * @param left The left motor controller which other controllers follow().
       * @param right The left motor controller which other controllers follow()
       * @param gyro The gyro to orient the robot with.
       */
      public Drive(WPI_TalonFX left, WPI_TalonFX right, AHRS gyro) {
            m_left = left;
            m_right = right;
            m_gyro = gyro;

            m_drive = new DifferentialDrive(
                  left,
                  right
            );

            m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
            m_field = new Field2d();
            SmartDashboard.putData("Robot Field2d", m_field);
            if (RobotBase.isSimulation()) {
                  initSim();
            }
      }

      private void initSim() {
            m_driveSim = new DifferentialDrivetrainSim(
                  DCMotor.getFalcon500(2), // Two Falcon 500s per side of drivetrain
                  kDriveGearReduction,     // Gear Reduction
                                           // TODO: Get real values of MoI and Mass
                  7,                       // Moment of Inertia
                  60,                      // Robot Mass
                  kWheelDiameter / 2,      // Wheel Radius
                  kTrackWidth,             // Track Width
                                           // The standard deviations for measurement noise:
                                           // x and y:          0.001 m
                                           // heading:          0.001 rad
                                           // l and r velocity: 0.1   m/s
                                           // l and r position: 0.005 m
                  VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005)
            );
      }

      /**
       * Arcade drives, stopping when xSpeed is too close to zero
       *
       * @param xSpeed driving speed
       * @param zRotation turning speed
       */
      public void arcadeDrive(double xSpeed, double zRotation) {
            Debugging.put("arcade_drive_speed", "spd: " + xSpeed + ", rot: " + zRotation);
            if(Math.abs(xSpeed) > 0.05) {
                  Debugging.put("drive_brakes_on", "No");

                  m_drive.arcadeDrive(xSpeed, zRotation);
            } else {
                  Debugging.put("drive_brakes_on", "Yes");

                  m_drive.stopMotor();
            }
      }

      public void tankDriveVolts(double leftVolts, double rightVolts) {
            Debugging.put("tank_drive_volts", "left: " + leftVolts + ", right: " + rightVolts);
            m_left.setVoltage(leftVolts);
            m_right.setVoltage(rightVolts);
            m_drive.feed();
      }

      public Pose2d getPose() {
            return m_odometry.getPoseMeters();
      }

      public DifferentialDriveWheelSpeeds getWheelSpeeds() {
            // Note that the left sensor velocity is inverted.
            return new DifferentialDriveWheelSpeeds(-m_left.getSelectedSensorVelocity() / kPulsePerDistance, m_right.getSelectedSensorVelocity() / kPulsePerDistance);
      }

      public void resetOdometry(Pose2d pose) {
            m_left.setSelectedSensorPosition(0);
            m_right.setSelectedSensorPosition(0);
            m_odometry.resetPosition(pose, m_gyro.getRotation2d());
      }

      public double getAverageEncoderDistance() {
            // Note that the left sensor position is inverted.
            return (-m_left.getSelectedSensorPosition() / kPulsePerDistance + m_right.getSelectedSensorPosition() / kPulsePerDistance) / 2.0;
      }

      /**
       * Updates shuffleboard with a field, mainly for autonomous.
       *
       * @param trajectory when null removes trajectory
       */
      public void setTrajectory(Trajectory trajectory) {
            if (trajectory != null) {
                  m_field.getObject("trajectory").setTrajectory(trajectory);
            } else {
                  m_field.getObject("trajectory").setPoses(List.of());
            }
      }

      public DifferentialDrive getDrive() {
            return m_drive;
      }

      @Override
      public void periodic() {
            // Note that the left sensor position is inverted.
            Pose2d pose = m_odometry.update(
                  m_gyro.getRotation2d(),
                  -m_left.getSelectedSensorPosition() / kPulsePerDistance,
                  m_right.getSelectedSensorPosition() / kPulsePerDistance
            );

            m_field.setRobotPose(pose);
      }

      @Override
      public void simulationPeriodic() {
            // Set the inputs to the system. Note that we need to convert
            // the [-1, 1] PWM signal to voltage by multiplying it by the
            // robot controller voltage.
            m_driveSim.setInputs(m_left.get() * RobotController.getInputVoltage(),
            m_right.get() * RobotController.getInputVoltage());

            // Advance the model by 20 ms (default period for TimedRobot).
            m_driveSim.update(0.02);

            // Update all of our sensors.
            m_left.getSimCollection().setIntegratedSensorRawPosition((int) (m_driveSim.getLeftPositionMeters() * kPulsePerDistance));
            m_left.getSimCollection().setIntegratedSensorVelocity((int) (m_driveSim.getLeftVelocityMetersPerSecond() * kPulsePerDistance));
            m_right.getSimCollection().setIntegratedSensorRawPosition((int) (m_driveSim.getRightPositionMeters() * kPulsePerDistance));
            m_right.getSimCollection().setIntegratedSensorVelocity((int) (m_driveSim.getRightVelocityMetersPerSecond() * kPulsePerDistance));
            SimUtils.setGyroAngle(-m_driveSim.getHeading().getDegrees());
      }
}
