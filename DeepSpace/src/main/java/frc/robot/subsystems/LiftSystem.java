package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.LiftController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;


public class LiftSystem extends Subsystem{

    PWMTalonSRX liftMotor;
    DigitalInput limitUpDI;
    DigitalInput limitDownDI;

    @Override
    protected void initDefaultCommand() {}

    public LiftSystem(int channel, int _limitUpDI, int _limitDownDI)
    {
        liftMotor = new PWMTalonSRX(channel);
        limitUpDI = new DigitalInput(_limitUpDI);
        limitDownDI = new DigitalInput(_limitDownDI);
    }

    public void move(double speed){
        liftMotor.set(speed);
    }

    public boolean getLimitUp()
    {
        if(limitUpDI.get()){
            return true;
        }
        return false;
    }

    public boolean getLimitDown()
    {
        if(limitDownDI.get()){
            return true;
        }
        return false;
    }
}