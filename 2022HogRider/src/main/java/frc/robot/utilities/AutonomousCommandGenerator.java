package frc.robot.utilities;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frc.robot.commands.DoStrategyActions;
import frc.robot.commands.TimedAutoCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

/**
 * During autonomous, this command follows paths as listed in
 * {@see Constants}.kAutoTrajectories. A {@see DoAutoActions}
 * manipulates the arm and intake while the robot follows a trajectory.
 */
public final class AutonomousCommandGenerator {
      private final Arm m_arm;
      private final Drive m_drive;
      private final Intake m_intake;

      private final PIDController m_leftPID = new PIDController(kPDriveVel, 0, 0);
      private final PIDController m_rightPID = new PIDController(kPDriveVel, 0, 0);

      private final Map<String, Trajectory> m_file2trajectory = new HashMap<>();

      public AutonomousCommandGenerator(Arm arm, Drive drive, Intake intake) {
            m_arm = arm;
            m_drive = drive;
            m_intake = intake;

            try {
                  for (var entry : kAutoStrategies.entrySet()) {
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

      public Command generateCommand() {
            var strategyName = Settings.get("auto_strategy", "BlueBottom");
            if ("TimeBased".equals(strategyName)) {
                  return new TimedAutoCommand(m_drive, m_intake, m_arm).andThen(new RunCommand(() -> {
                        m_arm.moveSafely(0);
                        m_drive.arcadeDrive(0, 0);
                        m_intake.move(0);
                  }, m_arm, m_drive, m_intake));
            }
            var strategy = kAutoStrategies.get(strategyName);

            List<Command> ramseteCommands = new ArrayList<>();
            for (String file : strategy.files) {
                  var trajectory = m_file2trajectory.get(file);
                  ramseteCommands.add(createRamsete(trajectory));
            }
            if (strategy.getFirstActionTime() < 0) {
                  // Delay ramsete commands from running, so
                  // actions can happen before driving.
                  List<Command> temp = new ArrayList<>();
                  temp.add(new RunCommand(() -> m_drive.tankDriveVolts(0, 0), m_drive).withTimeout(-strategy.getFirstActionTime()));
                  temp.addAll(ramseteCommands);
                  ramseteCommands = temp;
            }
            ramseteCommands.add(new RunCommand(() -> m_drive.tankDriveVolts(0, 0), m_drive));
            Command[] _ramseteCommands = new Command[ramseteCommands.size()];
            ramseteCommands.toArray(_ramseteCommands);

            return new ParallelCommandGroup(
                  new SequentialCommandGroup(_ramseteCommands),
                  new DoStrategyActions(strategy, m_arm, m_intake)
            ).andThen(new RunCommand(() -> {
                  m_arm.moveSafely(0);
                  m_drive.arcadeDrive(0, 0);
                  m_intake.move(0);
            }, m_arm, m_drive, m_intake));
      }

      private Command createRamsete(Trajectory trajectory) {
            RamseteCommand ramseteCommand = new RamseteCommand(
                  trajectory,
                  m_drive::getPose,
                  new RamseteController(kRamseteB, kRamseteZeta),
                  kAutoFeedforward,
                  kDriveKinematics,
                  m_drive::getWheelSpeeds,
                  m_leftPID,
                  m_rightPID,
                  m_drive::tankDriveVolts,
                  m_drive
            );

            return new SequentialCommandGroup(
                  new InstantCommand(() -> {
                        m_leftPID.reset();
                        m_rightPID.reset();
                        m_drive.resetOdometry(trajectory.getInitialPose());
                        m_drive.setTrajectory(trajectory);
                  }, m_drive),
                  ramseteCommand,
                  new InstantCommand(() -> {
                        m_drive.tankDriveVolts(0, 0);
                        m_drive.setTrajectory(null);
                  }, m_drive)
            );
      }
}
