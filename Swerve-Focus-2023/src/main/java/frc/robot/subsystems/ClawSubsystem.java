package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase {
    

    CANSparkMax motor_550; //neo 550 motor
    DigitalInput limit_out;

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Claw Rot", getRotDegrees());
    }

    /**
     * Create a new claw subsystem
     * @param m_value = value for the neo 550 motor
     * @param limit_value = digital input value for the limit switch
     */
    public ClawSubsystem(int m_value, int limit_value) {

        motor_550 = new CANSparkMax(m_value, MotorType.kBrushless);
        motor_550.getEncoder().setPosition(0);
        limit_out = new DigitalInput(limit_value);
    }


    public void stop() {
        motor_550.stopMotor();
    }

    /**
     * Open while pressed but not exceeding the outter limit
     */
    public boolean open() {
        boolean opened  = getRotDegrees() < 0.05;//= limit_out.get();

        if(!opened) {
            motor_550.set(-ClawConstants.claw_speed_open);
        }
        else {
            stop();
            //debug to console that claw is open and report the position in degrees
            System.out.println("Claw is open to open position. POS: " + getRotDegrees());
        }

        return opened;
    }

    /**
     * Close while pressed but do not exceed hard close limit
     */
    public boolean close() {

        //convert native motor units to degrees
        boolean closed = getRotDegrees() > ClawConstants.default_close_degrees;

        System.out.println("Claw ROT: " + getRotDegrees());

        if(!closed) {
            motor_550.set(ClawConstants.claw_speed_close);
        }
        else {
            stop();
            //debug to console that claw is closed and report the position in degrees
            System.out.println("Claw is closed to default closed position. POS: " + getRotDegrees());
        }
        
        return closed;
    }

    /**
     * Close while pressed to a certain angle corresponding to the cone grip strength
     */
    public boolean closeCone() {
        boolean closed = getRotDegrees() > ClawConstants.cone_close_degrees;
        
        return closed;
    }

    /**
     * Close while pressed to a certain angle corresponding to the cube grib strength
     */
    public boolean closeCube() {
        boolean closed = getRotDegrees() > ClawConstants.cube_close_degrees;

    
        
        return closed;
    }

    double getRotDegrees() {
        return 360 * motor_550.getEncoder().getPosition() / motor_550.getEncoder().getCountsPerRevolution();
    }
}
