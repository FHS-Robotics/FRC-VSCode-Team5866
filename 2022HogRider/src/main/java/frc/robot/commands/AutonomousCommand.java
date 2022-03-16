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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.utilities.ProxyCommandBase;
import frc.robot.utilities.Settings;

/**
 * During autonomous, this command follows paths as listed in
 * {@see Constants}.kAutoTrajectories. A {@see DoAutoActions}
 * manipulates the arm and intake while the robot follows a trajectory.
 */
public final class AutonomousCommand extends ProxyCommandBase {
      private final Arm m_arm;
      private final Drive m_drive;
      private final Intake m_intake;

      private final Map<String, Trajectory> m_file2trajectory = new HashMap<>();

      public AutonomousCommand(Arm arm, Drive drive, Intake intake) {
            m_arm = arm;
            m_drive = drive;
            m_intake = intake;

            try {
                  for (var entry : kAutoTrajectories.entrySet()) {
                        for (String filename : entry.getValue().files) {
                              if (m_file2trajectory.containsKey(filename)) {
                                    continue;
                              }
                              Path trajectoryPath = Filesystem.getDeployDirectory()
                                    .toPath()
                                    .resolve("paths")
                                    .resolve(filename);
                              m_file2trajectory.put(filename, TrajectoryUtil.fromPathweaverJson(trajectoryPath));
                        }
                  }
            } catch (IOException e) {
                  // Crash program on I/O failure
                  throw new RuntimeException(e);
            }
      }

      @Override
      protected Command generateDelegate() {
            var selectedStrategy = Settings.get("auto_strategy", "BlueBottom");
            var trajectoryDef = kAutoTrajectories.get(selectedStrategy);

            List<Command> ramseteCommands = new ArrayList<>();
            for (String file : trajectoryDef.files) {
                  var trajectory = m_file2trajectory.get(file);
                  ramseteCommands.add(createRamsete(m_drive, trajectory));
            }
            Command[] _ramseteCommands = new Command[ramseteCommands.size()];
            ramseteCommands.toArray(_ramseteCommands);

            return new ParallelCommandGroup(
                  new SequentialCommandGroup(_ramseteCommands),
                  new DoAutoActions(trajectoryDef, m_arm, m_intake)
            );
      }

      private static Command createRamsete(Drive drive, Trajectory trajectory) {
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
