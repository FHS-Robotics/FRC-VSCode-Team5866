package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CommandTemplate extends CommandBase {
    

    /**
     * Initializer where you implement constructor arguments and setup of command
     */
    public CommandTemplate(SubsystemBase requirement) {
        addRequirements(requirement); //command will check this subsystem out during run so that nothing else messes with it
    }


    //Runs one time when command is activated
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }

    //Runs continuously until command is finished
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        super.execute();
    }

    //Add logic here to return finished or not.
    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return super.isFinished();
    }

    //Final code that command runs when it is finished or interrupted
    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        super.end(interrupted);
    }
}
