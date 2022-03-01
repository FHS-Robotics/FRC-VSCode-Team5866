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
        m_arm.set(-1); //make sure command terminates after some amount of seconds or that you are using ControlMode.Position or something
        //if you are using the vex 775 motors, then you will have to get the encoder value separately and write a PID loop
    }
}
