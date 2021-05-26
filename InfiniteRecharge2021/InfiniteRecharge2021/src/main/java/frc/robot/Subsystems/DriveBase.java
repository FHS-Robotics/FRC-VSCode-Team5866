package frc.robot.Subsystems;

import com.ctre.phoenix.CANifierJNI;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Commands.TeleOpDrive;

public class DriveBase extends SubsystemBase {

    private SpeedControllerGroup m_leftMotors;
    private SpeedControllerGroup m_rightMotors;
    private DifferentialDrive m_drive;
    private CANEncoder m_leftEncoder;
    private CANEncoder m_rightEncoder;
    private AHRS m_gyro;

    private DifferentialDriveOdometry m_odometry;

    /*
     * @Override protected void initDefaultCommand() { setDefaultCommand(new
     * TeleOpDrive()); }
     */

    public DriveBase(SpeedControllerGroup left, SpeedControllerGroup right, AHRS g, CANSparkMax left_enc_motor,
            CANSparkMax right_enc_motor) {
        m_leftMotors = left;
        m_rightMotors = right;
        m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

        m_gyro = g;
        m_leftEncoder = left_enc_motor.getEncoder();
        m_rightEncoder = left_enc_motor.getEncoder();
        // Sets the distance per pulse for the encoders
        m_leftEncoder.setPositionConversionFactor(RobotMap.kEncoderDistancePerPulse);
        m_rightEncoder.setPositionConversionFactor(RobotMap.kEncoderDistancePerPulse);
        m_leftEncoder.setVelocityConversionFactor(RobotMap.kEncoderVelocityPerRPM);
        m_rightEncoder.setVelocityConversionFactor(RobotMap.kEncoderVelocityPerRPM);

        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());

        setDefaultCommand(new TeleOpDrive(this));
    }

     @Override
    public void periodic() {
        // Update the odometry in the periodic block
        m_odometry.update(m_gyro.getRotation2d(), m_leftEncoder.getPosition(),
                          m_rightEncoder.getPosition());
    }

    /**
    * Returns the currently-estimated pose of the robot.
    *
    * @return The pose.
    */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
    * Returns the current wheel speeds of the robot.
    *
    * @return The current wheel speeds.
    */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
    }

    /**
    * Resets the odometry to the specified pose.
    *
    * @param pose The pose to which to set the odometry.
    */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, m_gyro.getRotation2d());
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        m_drive.arcadeDrive(-xSpeed, -zRotation);
    }

    /**
    * Controls the left and right sides of the drive directly with voltages.
    *
    * @param leftVolts  the commanded left output
    * @param rightVolts the commanded right output
    */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftMotors.setVoltage(leftVolts);
        m_rightMotors.setVoltage(-rightVolts);
        m_drive.feed();
        System.out.println("driving volts");
    }

    /**
    * Resets the drive encoders to currently read a position of 0.
    */
    public void resetEncoders() {
        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
    }

    /**
    * Gets the average distance of the two encoders.
    *
    * @return the average of the two encoder readings
    */
    public double getAverageEncoderDistance() {
        return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
    }

    /**
    * Gets the left drive encoder.
    *
    * @return the left drive encoder
    */
    public CANEncoder getLeftEncoder() {
        return m_leftEncoder;
    }

    /**
    * Gets the right drive encoder.
    *
    * @return the right drive encoder
    */
    public CANEncoder getRightEncoder() {
        return m_rightEncoder;
    }

    /**
    * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
    *
    * @param maxOutput the maximum output to which the drive will be constrained
    */
    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }

    /**
    * Zeroes the heading of the robot.
    */
    public void zeroHeading() {
        m_gyro.reset();
    }

    /**
    * Returns the heading of the robot.
    *
    * @return the robot's heading in degrees, from -180 to 180
    */
    public double getHeading() {
        return m_gyro.getRotation2d().getDegrees();
    }

    /**
    * Returns the turn rate of the robot.
    *
    * @return The turn rate of the robot, in degrees per second
    */
    public double getTurnRate() {
        return -m_gyro.getRate();
    }
}