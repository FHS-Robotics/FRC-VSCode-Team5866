package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
public class Shooter extends Subsystem {

    public TalonFX m_left;
    public TalonFX m_right;

    public double ks = 0.523; //min voltage required to move motor
    public double kv = 1.44; //volt seconds per meter.  When multiplied by m/s, it converts to volts. --> min volts requiret to maintain speed
    public double kp = 3.33;
    public double ka = 1.48;
    public double kf = kv * 10 * 1023/12 * 6.28*0.0635 * 16.0/77.0 * 1.0/2048; //kv times 10deciseconds/second times 2*pi*r times 16/77 gear ratio times 1 input rotation/2048 native units
    //1 m/s
    //15.748 rad/s
    //2.51 rps
    //150.5 rpm --> 724.3 rpm of shaft
    //gear ratio = 77/16 --> 4.8125
    //1 rotation = 0.399 m
    //r=0.0635
    //1 rpm = 0.399 m/m
    //falcon native units = 2048u/1rot
    double rotationspermeter = 2.51; //rotation of flywheel per meter
    double gearFlyWheelPerMotor = 16/77; //16 flywheel rotations per 77 motor rotations
    double gearMotorPerFlyWheel = 77/16;
    double nativeUnitPerRotation = 2048;

    //values for shoot forward and backward
    //public double shootForward = 0.5;
    //public double shootBackward = -0.25;

        //values for shoot forward and backward in meters per second
        public double shootForward = 5;
        public double shootBackward = -2.5;

    public Shooter(TalonFX left, TalonFX right) {
        m_left = left;
        m_right = right;
        m_left.configFactoryDefault();
        m_right.configFactoryDefault();
        m_right.setInverted(true);
        m_left.selectProfileSlot(0, 0);
        m_right.selectProfileSlot(0, 0);
        m_left.configVoltageCompSaturation(12); //set voltage to 12
        m_right.configVoltageCompSaturation(12);
        m_left.enableVoltageCompensation(true);
        m_right.enableVoltageCompensation(true);
        m_left.configPeakOutputReverse(0.0);
        m_right.configPeakOutputReverse(0.0);
        //m_left.getSupplyCurrent();
        //m_left.getMotorOutputVoltage();
        m_left.config_kF(0, kf);
        m_left.config_kP(0, kp);
        m_right.config_kF(0, kf);
        m_right.config_kP(0, kp);
    }

    /**
     * shoot forward or backward at different speeds depending on direction
     * @param direction : direction to run shooter
     */
    public void shoot(boolean direction) {
        //m_left.set(ControlMode.PercentOutput, direction ? shootForward : shootBackward);
        //m_right.set(ControlMode.PercentOutput, direction ? -shootForward : -shootBackward);
        //m_left.set(ControlMode.Velocity, ((shootForward * 5000)/600)*2048/1); //speed in rpm
        //SimpleMotorFeedforward forward = new SimpleMotorFeedforward(ks, kv, ka);
        /*m_left.set(ControlMode.Velocity, 1000 * 2048/600); //speed in rpm  
        m_right.set(ControlMode.Velocity, 1000 * 2048/600); //speed in rpm    
        m_right.set(ControlMode.PercentOutput, DemandType.ArbitraryFeedForward, , demand1);*/
        //System.out.println((forward.calculate(shootForward) * 1023)/12);
        m_left.set(ControlMode.Velocity, mpsToNativeUnits(shootForward));
        m_right.set(ControlMode.Velocity, mpsToNativeUnits(shootForward));
    }
 
    /**
     * Release motors
     */
    public void release() {
        m_left.set(ControlMode.PercentOutput, 0);
        m_right.set(ControlMode.PercentOutput, 0);
    }

    @Override
    protected void initDefaultCommand() {}

    /**
     * convert meters/second to native units/decisecond (100 ms)
     * @param meterspersecond
     * @return
     */
    public double mpsToNativeUnits(double meterspersecond) {
        //native unit / 100ms is what the output will be
        //the max rpm is 6380 in a falcon 500, so 6380 rpm / 60 = 106.333 rotations per second
        double rps = meterspersecond * rotationspermeter * gearMotorPerFlyWheel; //converts meters per second of flywheel to rotations per second of motor
        double nativePerDecisecond = rps * 0.1 * nativeUnitPerRotation; //converts rps to native units per decisecond
        return nativePerDecisecond;
    }
}
