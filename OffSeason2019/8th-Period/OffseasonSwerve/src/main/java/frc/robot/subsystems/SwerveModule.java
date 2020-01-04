package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * SwerveDrive
 */
public class SwerveModule extends PIDSubsystem {

    PWMSpeedController speedMotor;
    PWMSpeedController angleMotor;
    
    double angleOffset = 0;
    double lastAngle = 0;
    double deadspot = 0.2;

    AnalogInput encoder;
    private final double tolerance = 0.1;
    private final double MAX_VOLTS = 4.95; //varies depending on swerve module

    /**
     * 
     * @param _speedMotor = the speed controller that controls drive
     * @param _angleMotor = the speed controller that controls rotation
     * @param _encoder = analog port the encoder is tied to
     * @param _angleOffset = angle in degrees that the drive is offset (-180 : 180)
     */
    public SwerveModule(PWMSpeedController _speedMotor, PWMSpeedController _angleMotor, int _encoder, double _angleOffset){
        super(1, 0, 0);
        speedMotor = _speedMotor;
        angleMotor = _angleMotor;

        angleOffset = _angleOffset;
        angleOffset = (angleOffset + 180) /180; //convert to -1 : 1

        encoder = new AnalogInput(_encoder);

        setOutputRange(-1, 1);
        setAbsoluteTolerance(tolerance);
        setInputRange(0, MAX_VOLTS); //analog inputs are a 12 bit number 2^12
        getPIDController().setContinuous(true);
        enable();


//please do the math :(

        setSetpoint(angleOffset * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5)); //may be a problem
    }

    /**
     * 
     * @param speed = speed to drive the robot (-1 : 1)
     * @param angle = angle in radians to turn to  (-1 : 1)
     */
    public void drive (double speed, double angle) {
        if(Math.abs(speed) <= deadspot)
        {
            speed = 0;
            angle = lastAngle;
        }

        speedMotor.set (speed); //simple; set the speed motor to the input speed

        System.out.println(angle);
    
        //the angle is a little more complicated
        double setpoint = (angle + angleOffset) * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
        if (setpoint < 0) {
            setpoint = MAX_VOLTS + setpoint;
        }
        if (setpoint > MAX_VOLTS) {
            setpoint = setpoint - MAX_VOLTS;
        }
    
        //System.out.println(setpoint);
        lastAngle = angle;
        setSetpoint(setpoint);
    }

    @Override
    protected double returnPIDInput() {
        return encoder.getAverageVoltage();
    }

    @Override
    protected void usePIDOutput(double output) {
        this.angleMotor.set(output);
    }

    @Override
    protected void initDefaultCommand() {}
}