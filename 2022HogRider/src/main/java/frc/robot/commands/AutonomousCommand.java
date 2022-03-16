package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

/**
 * During autonomous, this command follows paths as listed in
 * {@see Constants}.kAutoTrajectories. A {@see DoAutoActions}
 * manipulates the arm and intake while the robot follows a trajectory.
 *
 * TODO: Use {@see Settings} to change which autonomous strategy runs.
 */
public final class AutonomousCommand extends SequentialCommandGroup {
      public AutonomousCommand(Drive drive, Intake intake) {
            List<Command> commands = new ArrayList<>();
            try {
                  for (var trajectoryDef : kAutoTrajectories) {
                        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryDef.file);
                        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                        commands.add(
                              new ParallelCommandGroup(
                                    getRamsete(drive, trajectory),
                                    new DoAutoActions(trajectoryDef, intake)
                              )
                        );
                  }
            } catch (IOException e) {
                  // Crash program on I/O failure
                  throw new RuntimeException(e);
            }

            // Add our autonomous commands to the sequential command group.
            Command[] _commands = new Command[commands.size()];
            commands.toArray(_commands);
            addCommands(_commands);
      }

      private static Command getRamsete(Drive drive, Trajectory trajectory) {
            RamseteCommand ramseteCommand = new RamseteCommand(
                  trajectory,
                  drive::getPose,
                  new RamseteController(kRamseteB, kRamseteZeta),
                  kAutoFeedforward,
                  kDriveKinematics,
                  drive::getWheelSpeeds,
                  new PIDController(kPDriveVel, 0, 0),
                  new PIDController(kPDriveVel, 0, 0),
                  drive::tankDriveVolts,
                  drive
            );

            return new SequentialCommandGroup(
                  new InstantCommand(() -> {
                        drive.resetOdometry(trajectory.getInitialPose());
                        drive.setTrajectory(trajectory);
                  }, drive),
                  ramseteCommand,
                  new InstantCommand(() -> {
                        drive.tankDriveVolts(0, 0);
                        drive.setTrajectory(null);
                  }, drive)
            );
      }
}
