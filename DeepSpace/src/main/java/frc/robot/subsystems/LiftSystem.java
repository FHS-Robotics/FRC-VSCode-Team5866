package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.LiftController;

import edu.wpi.first.wpilibj.command.Subsystem;


public class LiftSystem extends Subsystem{

    public LiftSystem(){

    }

    public void initDefaultCommand(){
        setDefaultCommand(new LiftController());
    }

    public void move(double speed){
        RobotMap.liftMotor.set(ControlMode.PercentOutput, speed);
    }

    public void move(){
        RobotMap.liftMotor.setPercentModeOutput(-.7);
    }
}