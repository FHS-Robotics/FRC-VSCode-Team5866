package org.usfirst.frc.team5866.robot;

import org.usfirst.frc.team5866.robot.subsystems.clawMech;
import org.usfirst.frc.team5866.robot.subsystems.liftSystem;
import org.usfirst.frc.team5866.robot.subsystems.swerveDrive;
import org.usfirst.frc.team5866.robot.subsystems.swerveModule;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;



public class RobotMap {
	public static ADXRS450_Gyro gyro;
		
    public static SpeedController frontRightSpeedMotor;
    public static SpeedController frontRightAngleMotor;
    public static AnalogInput frontRightEnc;
    public static PIDController frontRightPIDController;
    
    public static SpeedController backRightSpeedMotor;
    public static SpeedController backRightAngleMotor;
    public static AnalogInput backRightEnc;
    public static PIDController backRightPIDController;
    
    public static SpeedController backLeftSpeedMotor;
    public static SpeedController backLeftAngleMotor;
    public static AnalogInput backLeftEnc;
    public static PIDController backLeftPIDController;
    
    public static SpeedController frontLeftSpeedMotor;
    public static SpeedController frontLeftAngleMotor;
    public static AnalogInput frontLeftEnc;
    public static PIDController frontLeftPIDController;
    
    public static swerveModule frontRight;
    public static swerveModule frontLeft;
    public static swerveModule backRight;
    public static swerveModule backLeft;
    public static swerveDrive swerve;
    
    public static liftSystem lift;
    //public static TalonSRX liftMotor;
    
    public static Compressor mainC;
    public static clawMech claw;
    public static DoubleSolenoid gripper;
    public static DoubleSolenoid tilter;

    




    @SuppressWarnings("deprecation")
	public static void init() {

        frontRightEnc = new AnalogInput(3);
        backRightEnc = new AnalogInput(2);
        backLeftEnc = new AnalogInput(1);
        frontLeftEnc = new AnalogInput(0);

        frontRightAngleMotor = new VictorSP(4);
        backRightAngleMotor = new VictorSP(2);
        backLeftAngleMotor = new VictorSP(6);
        frontLeftAngleMotor = new VictorSP(8);

        
        frontRightPIDController = new PIDController(2, 0, 0, 0, frontRightEnc, frontRightAngleMotor, 0.02);
        backLeftPIDController = new PIDController(2, 0, 0, 0, backLeftEnc, backLeftAngleMotor, 0.02);
        frontLeftPIDController = new PIDController(2, 0, 0, 0, frontLeftEnc, frontLeftAngleMotor, 0.02);
        backRightPIDController = new PIDController(2, 0, 0, 0, backRightEnc, backRightAngleMotor, 0.02);
        //try the p as .5 and i as %20 of p
        
        frontRightPIDController.setInputRange(0.015, 4.987);
        frontRightPIDController.setContinuous(true);
        frontRightPIDController.setAbsoluteTolerance(0.15);
        frontRightPIDController.setOutputRange(-1.0, 1.0);
        frontRightPIDController.enable();
        
        frontLeftPIDController.setInputRange(0.015, 4.987);
        frontLeftPIDController.setContinuous(true);
        frontLeftPIDController.setAbsoluteTolerance(0.15);
        frontLeftPIDController.setOutputRange(-1.0, 1.0);
        frontLeftPIDController.enable();
        
        backRightPIDController.setInputRange(0.015, 4.987);
        backRightPIDController.setContinuous(true);
        backRightPIDController.setAbsoluteTolerance(0.15);
        backRightPIDController.setOutputRange(-1.0, 1.0);
        backRightPIDController.enable();
        
        backLeftPIDController.setInputRange(0.015, 4.987);
        backLeftPIDController.setContinuous(true);
        backLeftPIDController.setAbsoluteTolerance(0.15);
        backLeftPIDController.setOutputRange(-1.0, 1.0);
        backLeftPIDController.enable();
    	
        

        LiveWindow.addSensor("Front Right Swerve Module", "Front Right Encoder", frontRightEnc);
        LiveWindow.addActuator("Front Right Swerve Module", "Front Right Angle Motor", (VictorSP) frontRightAngleMotor);
        frontRightAngleMotor.setInverted(false);
        LiveWindow.addActuator("Front Right Swerve Module", "Front Right PID Controller", frontRightPIDController);
        frontRightSpeedMotor = new VictorSP(5);
        LiveWindow.addActuator("Front Right Swerve Module", "Front Right PID Controller", (VictorSP) frontRightSpeedMotor);
        frontRightSpeedMotor.setInverted(false);
        

        LiveWindow.addSensor("Back Right Swerve Module", "Back Right Encoder", backRightEnc);
        LiveWindow.addActuator("Back Right Swerve Module", "Back Right Angle Motor", (VictorSP) backRightAngleMotor);
        backRightAngleMotor.setInverted(false);
        LiveWindow.addActuator("Back Right Swerve Module", "Back Right PID Controller", backRightPIDController);
        backRightSpeedMotor = new VictorSP(3);
        LiveWindow.addActuator("Back Right Swerve Module", "Back Right Speed Motor", (VictorSP) backRightSpeedMotor);
        backRightSpeedMotor.setInverted(false);
        
        LiveWindow.addSensor("Back Left Swerve Module", "Back Left Encoder", backLeftEnc);
        LiveWindow.addActuator("Back Left Swerve Module", "Back Left Angle Motor", (VictorSP) backLeftAngleMotor);
        backLeftAngleMotor.setInverted(false);
        LiveWindow.addActuator("Back Left Swerve Module", "Back Left PID Controller", backLeftPIDController);
        backLeftSpeedMotor = new VictorSP(7);
        LiveWindow.addActuator("Back Left Swerve Module", "Back Left Speed Motor", (VictorSP) backLeftSpeedMotor);
        backLeftSpeedMotor.setInverted(false);
        
        LiveWindow.addSensor("Front Left Swerve Module", "Front Left Encoder", frontLeftEnc);
        LiveWindow.addActuator("Front Left Swerve Module", "Front Left Angle Motor", (VictorSP) frontLeftAngleMotor);
        frontLeftAngleMotor.setInverted(false);
        LiveWindow.addActuator("Front Left Swerve Module", "Front Left PID Controller", frontLeftPIDController);
        frontLeftSpeedMotor = new VictorSP(9);
        LiveWindow.addActuator("Front Left Swerve Module", "Front Left Speed Motor", (VictorSP) frontLeftSpeedMotor);
        frontLeftSpeedMotor.setInverted(false);
  
        //liftMotor = new TalonSRX(20);
    	//liftMotor.configOpenloopRamp(1, 10);


        
        frontLeft = new swerveModule(RobotMap.frontLeftSpeedMotor, frontLeftPIDController);
        frontRight = new swerveModule(RobotMap.frontRightSpeedMotor, frontRightPIDController);
        backLeft = new swerveModule(RobotMap.backLeftSpeedMotor, backLeftPIDController);
        backRight = new swerveModule(RobotMap.backRightSpeedMotor, backRightPIDController);
        
        swerve = new swerveDrive();

        mainC = new Compressor(1);
        mainC.clearAllPCMStickyFaults();
		mainC.start();
		  
		tilter = new DoubleSolenoid(1, 1, 0);
		gripper = new DoubleSolenoid(1, 7, 6);
  
        lift = new liftSystem();
        
        claw = new clawMech(gripper, tilter);

    }
}
