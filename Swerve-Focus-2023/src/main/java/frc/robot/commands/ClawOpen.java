package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClawSubsystem;

public class ClawOpen extends CommandBase {
    

    ClawSubsystem claw;
    boolean isOpen;


    /**
     * Initializer where you implement constructor arguments and setup of command
     */
    public ClawOpen(ClawSubsystem _claw) {
        claw = _claw;
        addRequirements(claw); //command will check this subsystem out during run so that nothing else messes with it
    }

    @Override
    public void initialize() {
        isOpen = false;
    }

    //Runs continuously until command is finished
    @Override
    public void execute() {
        isOpen = claw.open();
    }

    //Add logic here to return finished or not.
    @Override
    public boolean isFinished() {
        return isOpen;
    }

    //Final code that command runs when it is finished or interrupted
    @Override
    public void end(boolean interrupted) {
        claw.stop();
    }
}
