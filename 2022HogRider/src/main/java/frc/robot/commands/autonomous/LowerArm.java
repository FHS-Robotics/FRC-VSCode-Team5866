package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class LowerArm extends CommandBase {
    private final Arm m_arm;

    public LowerArm(Arm arm) {
        m_arm = arm;
    }

    @Override
    public void execute() {
        m_arm.set(-1);
    }
}
