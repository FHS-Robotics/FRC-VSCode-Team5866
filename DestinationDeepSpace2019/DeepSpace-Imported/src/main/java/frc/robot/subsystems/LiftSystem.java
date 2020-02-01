package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lift system consists of 1-2 motors that run synchronously
 * to raise and lower the elevator for the robot
 */
public class LiftSystem extends Subsystem{

    public SpeedControllerGroup liftMotors;
    public DigitalInput limitUpDI;
    public DigitalInput limitDownDI;

    @Override
    protected void initDefaultCommand() {}

    public LiftSystem(PWMSpeedController lift1, PWMSpeedController lift2, int _limitUpDI, int _limitDownDI)
    {
        //initialize lift motors
        PWMSpeedController liftMotor1 = lift1;
        PWMSpeedController liftMotor2 = lift2;
        liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);


        limitUpDI = new DigitalInput(_limitUpDI);
        limitDownDI = new DigitalInput(_limitDownDI);
    }

    public void move(double speed){
        liftMotors.set(speed);
    }

    /**
     * Read the lift system's upper limit switch
     * @return Boolean based on digital input
     */
    public boolean getLimitUp()
    {
        return limitUpDI.get();
    }

    /**
     * Read the lift system's lower limit switch
     * @return Boolean based on digital input
     */
    public boolean getLimitDown()
    {
        return limitDownDI.get();
    }
}