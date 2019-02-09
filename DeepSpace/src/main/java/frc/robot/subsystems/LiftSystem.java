package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;


public class LiftSystem extends Subsystem{

    public SpeedControllerGroup liftMotors;
    public DigitalInput limitUpDI;
    public DigitalInput limitDownDI;

    @Override
    protected void initDefaultCommand() {}

    public LiftSystem(int lift1, int lift2, int _limitUpDI, int _limitDownDI)
    {
        //initialize lift motors
        PWMTalonSRX liftMotor1 = new PWMTalonSRX(lift1);
        PWMTalonSRX liftMotor2 = new PWMTalonSRX(lift2);
        liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);


        limitUpDI = new DigitalInput(_limitUpDI);
        limitDownDI = new DigitalInput(_limitDownDI);
    }

    public void move(double speed){
        liftMotors.set(speed);
    }

    public boolean getLimitUp()
    {
        if(limitUpDI.get() == true){
            return true;
        }
        return false;
    }

    public boolean getLimitDown()
    {
        if(limitDownDI.get() == true){
            return true;
        }
        return false;
    }
}