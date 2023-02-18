// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ClawConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ClawClose;
import frc.robot.commands.ClawOpen;
import frc.robot.commands.TeleOpArm;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

import com.fasterxml.jackson.core.io.IOContext;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's and operator's controllers
  public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static XboxController m_opController = new XboxController(OIConstants.kOperatorControllerPort);

  //Claw operation buttons
  JoystickButton closeClaw = new JoystickButton(m_opController, OIConstants.c_b);
  JoystickButton openClaw = new JoystickButton(m_opController, OIConstants.c_a);

  public static ArmSubsystem m_arm = new ArmSubsystem(ArmConstants.shoulderCanID, ArmConstants.elbowCanID,
                                        ArmConstants.elbow_en_ID, ArmConstants.e_reset_id, ArmConstants.s_reset_id);
  public static ClawSubsystem m_claw = new ClawSubsystem(ClawConstants.clawCanID, ClawConstants.c_reset_id);


  //The Arm/Claw Controller
 

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));

    //assign buttons to corresponding commands
    closeClaw.onTrue(new ClawClose(m_claw));
    openClaw.onTrue(new ClawOpen(m_claw));

    m_arm.setDefaultCommand(new TeleOpArm(m_arm));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // 
    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(DriveConstants.kDriveKinematics);

    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          new Translation2d(Units.inchesToMeters(177), Units.inchesToMeters(0)),  
          new Translation2d(Units.inchesToMeters(0), Units.inchesToMeters(65)), 
          new Translation2d(Units.inchesToMeters(80), Units.inchesToMeters(0))),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(Units.inchesToMeters(80), Units.inchesToMeters(0), new Rotation2d(90)),
        config);
        

        PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
        PIDController yController = new PIDController(AutoConstants.kPXController, 0, 0);
        ProfiledPIDController thetaController = new ProfiledPIDController(
                          AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

    /*var thetaController = new ProfiledPIDController(
        AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);*/

    //this controls the swerve drive
    SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
        exampleTrajectory,
        m_robotDrive::getPose, // Functional interface to feed supplier
        DriveConstants.kDriveKinematics,
        xController, 
        yController, 
        thetaController,
        m_robotDrive::setModuleStates,
        m_robotDrive);

        // Position controllers
        /*new PIDController(AutoConstants.kPXController, 0, 0), 
         new PIDController(AutoConstants.kPYController, 0, 0), 
        thetaController,
        m_robotDrive::setModuleStates,
        m_robotDrive);*/

        //te commands will hppen in this order
    return new SequentialCommandGroup(
      new InstantCommand(()-> m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose())),
      swerveControllerCommand,
      new InstantCommand(() -> m_robotDrive.stopModules())
    );

    // Reset odometry to the starting pose of the trajectory.
    /*m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false, false));*/
  }
}