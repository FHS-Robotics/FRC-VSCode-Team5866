package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

/**
 * Subsystem to control arm (shoulder and elbow)
 */
public class ArmSubsystem extends SubsystemBase {

    CANSparkMax shoulder;
    DigitalInput shoulder_reset;

    CANSparkMax elbow;
    DigitalInput elbow_reset;

    Counter elbowCounter;

    double P_temp;
    double shoulder_set_angle;

    PIDController shoulder_Control;

    @Override
    public void periodic() {

        //Put useful data to the Smart Dashboard / Shuffleboard
        SmartDashboard.putNumber("Shoulder Rot", getRotDegrees(shoulder));
        SmartDashboard.putNumber("SetPoint", shoulder_set_angle);
        //check what pid system wants to set the motor at
        SmartDashboard.putNumber("PID Value: ", shoulder_Control.calculate(getRotDegrees(shoulder)));
        P_temp = SmartDashboard.getNumber("P", 0);


        //periodically update PID values
        shoulder_Control.setP(P_temp);
        shoulder_Control.setSetpoint(shoulder_set_angle);

        //set speed of motor based on PID controller but hard lock it at the speed value
        double speed = shoulder_Control.calculate(getRotDegrees(shoulder));
        speed = speed > ArmConstants.shoulder_speed ? ArmConstants.shoulder_speed : speed;
        speed = speed < -ArmConstants.shoulder_speed ? -ArmConstants.shoulder_speed : speed;
        shoulder.set(speed);
    }


    /**
     * Creates a new ArmSubsystem object
     * @param shoulder_id = id for shoulder motor
     * @param elbow_id = id for elbow motor
     */
    public ArmSubsystem(int shoulder_id, int elbow_id, int encoder, int e_reset_id, int s_reset_id) {

        //for testing pid value
        SmartDashboard.putNumber("P", 0.4);


        //SHOULDER
        shoulder = new CANSparkMax(shoulder_id, MotorType.kBrushless); //using neo motors = brushless
        shoulder_reset = new DigitalInput(s_reset_id);
        shoulder.getEncoder().setPosition(0);


        //ELBOW
        elbow = new CANSparkMax(elbow_id, MotorType.kBrushed); //using window motor = brushed
        elbowCounter = new Counter(new DigitalInput(encoder));
        elbow_reset = new DigitalInput(e_reset_id);
        //elbow.getEncoder().setPosition(0);


        shoulder_set_angle = 0;
        shoulder_Control = new PIDController(ArmConstants.sP, ArmConstants.sI, ArmConstants.sD);
        shoulder_Control.setSetpoint(shoulder_set_angle);
    }


    public void moveElbow(double speed) {

        //If trying to move elbow toward reset limit switch, we check if we have hit the limit
        if(speed < 0 /*&& !elbow_reset.get()*/ || speed > 0)
            elbow.set(speed);
        else {
            elbow.stopMotor();
            //System.out.println("Elbow Limit Reached!");
        }
    }

    // && = and ; || = or ; ! = not ; <= less than or equal to ; == -> is_equivalent?
    public void moveShoulder(double speed) {

        //System.out.println("Shoulder position: " + shoulder.getEncoder().getPosition());

        //2PI [radians]/[rotation] * MotorPosition[native units] * [rotations]/[native unit]
        double rotDegrees = 360 * shoulder.getEncoder().getPosition() / shoulder.getEncoder().getCountsPerRevolution();


        //If trying to move elbow toward reset limit switch, we check if we have hit the limit
        if((speed < 0/*!shoulder_reset.get()*/) || 
           (speed > 0 /*&& rotDegrees > ArmConstants.shoulder_upper_limit*/)){
            
            //instead
            shoulder_set_angle += speed / 25;
            //shoulder.set(-speed);
        }
        else { 
            stopShoulder();
            //System.out.println("Shoulder Limit Reached!");
        }

        if(shoulder_set_angle > 0)
            shoulder_set_angle = 0;

        if(shoulder_set_angle < ArmConstants.shoulder_upper_limit) {
            shoulder_set_angle = ArmConstants.shoulder_upper_limit;
        }
    }


    public void stopShoulder() {
        shoulder.stopMotor();
    }


    
    double getRotDegrees(CANSparkMax motor) {
        return 360 * motor.getEncoder().getPosition() / motor.getEncoder().getCountsPerRevolution();
    }

}   