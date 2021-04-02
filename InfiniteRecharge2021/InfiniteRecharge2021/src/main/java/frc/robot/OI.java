package frc.robot;

import java.util.List;

//import org.graalvm.compiler.lir.aarch64.AArch64AtomicMove.CompareAndSwapOp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Commands.ChangeShootSpeed;
import frc.robot.Commands.Intake;
import frc.robot.Commands.Shoot;
import frc.robot.Commands.SwitchLimelight;
import frc.robot.Subsystems.Shooter;

public class OI {

    public static SendableChooser<String> autoChoice;

    //#region constants
    public static final int c_driver = 0;
    public static final int c_gunner = 0;

    //xbox control axes
    public static final int c_driveForwardAxis = 1;
    public static final int c_driveRotateAxis = 4;
    public static final int c_turretRotateAxis = 0;

    //xbox button values
    public static final int c_a = 1;
    public static final int c_b = 2;
    public static final int c_x = 3;
    public static final int c_y = 4;
    public static final int c_start = 8;
    public static final int c_select = 7;
    public static final int c_bumperLeft = 5;
    public static final int c_bumperRight = 4;
    public static final int c_leftThumb = 9;
    public static final int c_rightThumb = 10;
    //#endregion

    public static XboxController m_driverControl = new XboxController(0);
    public static XboxController m_gunnerControl = new XboxController(1);

    public static JoystickButton ballLock; //lock onto balls

    public static JoystickButton shootForward;
    public static JoystickButton shootBackward;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton turretLock;
    public static JoystickButton changeShootSpeed;

    public OI() {

        autoChoice = new SendableChooser<>();
        autoChoice.setDefaultOption("none", "none");
        autoChoice.addOption("BarrelRacing", "src\\main\\deploy\\paths\\BarrelRacing");
        autoChoice.addOption("BlueA", "src\\main\\deploy\\paths\\BlueA");
        autoChoice.addOption("BlueB", "src\\main\\deploy\\paths\\Blue");
        autoChoice.addOption("Bounce1", "src\\main\\deploy\\paths\\Bounce1");


        shootForward = new JoystickButton(m_gunnerControl, c_x);
        shootBackward = new JoystickButton(m_gunnerControl, c_y);
        intakeForward = new JoystickButton(m_gunnerControl, c_a);
        intakeBackward = new JoystickButton(m_gunnerControl, c_b);
        changeShootSpeed = new JoystickButton(m_gunnerControl, c_rightThumb);

        shootForward.whileHeld(new Shoot(true));
        shootBackward.whileHeld(new Shoot(false));
        intakeForward.whileHeld(new Intake(true));
        intakeBackward.whileHeld(new Intake(false));
        changeShootSpeed.whileHeld(new ChangeShootSpeed());
    }


    /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public edu.wpi.first.wpilibj2.command.Command getAutonomousCommand() {

    if(autoChoice.getSelected() == "none") {
        return null;
    }
    String file = autoChoice.getSelected();

    // Create a voltage constraint to ensure we don't accelerate too fast
    DifferentialDriveVoltageConstraint autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(RobotMap.ksVolts,
                                       RobotMap.kvVoltSecondsPerMeter,
                                       RobotMap.kaVoltSecondsSquaredPerMeter),
                                        RobotMap.kDriveKinematics,
                                        10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(RobotMap.kMaxSpeedMetersPerSecond,
                             RobotMap.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(RobotMap.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );
    //return new SwitchLimelight();

   RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        RobotMap.m_drive::getPose,
        new RamseteController(RobotMap.kRamseteB, RobotMap.kRamseteZeta),
        new SimpleMotorFeedforward(RobotMap.ksVolts,
                                   RobotMap.kvVoltSecondsPerMeter,
                                   RobotMap.kaVoltSecondsSquaredPerMeter),
        RobotMap.kDriveKinematics,
        RobotMap.m_drive::getWheelSpeeds,
        new PIDController(RobotMap.kPDriveVel, 0, 0),
        new PIDController(RobotMap.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        RobotMap.m_drive::tankDriveVolts,
        RobotMap.m_drive
    );

    // Reset odometry to the starting pose of the trajectory.
    RobotMap.m_drive.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> RobotMap.m_drive.tankDriveVolts(0, 0));
  }
}
