package frc.robot;

import java.util.Map;
import java.util.function.Consumer;

import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.SwitchDriveMode;
import frc.robot.commands.SwitchLimelightLight;
import frc.robot.commands.TeleOpDrive;
import frc.robot.commands.VisionAlignHorizontal;
import frc.robot.commands.VisionAlignRotational;
import frc.robot.commands.Shoot.mode;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinColorWheel;
import frc.robot.commands.ExtendArm;
import frc.robot.commands.ExtendColorWheel;
import frc.robot.commands.ActivateHooks;
import frc.robot.commands.ChangeIntakeSensitivity;
import frc.robot.commands.IntakeSystem;/**
 * OI
 */
public class OI {

    public static boolean climbed = false;

    public static double intakeSens = 3; //max of 5 and changed with the corresponding command
    public static boolean limeLightActive = true;
    
	public static Joystick m_driverControl = new Joystick(0);
    public static Joystick m_gunnerControl = new Joystick(1);

    public static JoystickButton switchLimeLight;
    
    public static JoystickButton switchDrive;
    public static JoystickButton alignTarget;
    public static JoystickButton allignBall;

    public static POVButton turnLeft;
    public static POVButton turnForward;
    public static POVButton turnRight;
    public static POVButton turnBack;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton shooterForward;
    public static JoystickButton shooterBackward;
    public static JoystickButton shootAll;
    public static JoystickButton shooterSmart;

    public static POVButton sensUp;
    public static POVButton sensDown;

    //Climb system
    public static JoystickButton unfoldArm;
    public static JoystickButton foldArm;
    public static JoystickButton activateHook;

    public static JoystickButton colorWheelSpinClockWise;
    public static JoystickButton colorWheelSpinCounter;

    public static JoystickButton colorWheelRise;
    public static JoystickButton colorWheelDrop;

    public static NetworkTableEntry resetGyro;

    /**
     * Initializer for all OI components
     */
    public OI() {
        switchDrive = new JoystickButton(m_driverControl, 10);
        alignTarget = new JoystickButton(m_driverControl, 8);
        allignBall = new JoystickButton(m_driverControl, 7);

        switchLimeLight = new JoystickButton(m_driverControl, 1); //button for turning limelight light on and off

        intakeForward = new JoystickButton(m_gunnerControl, 1);
        intakeBackward = new JoystickButton(m_gunnerControl, 2);
        shooterForward = new JoystickButton(m_gunnerControl, 3);
        shooterBackward = new JoystickButton(m_gunnerControl, 4);
        shooterSmart = new JoystickButton(m_gunnerControl, 8);
        //shootAll = new JoystickButton(m_gunnerControl, 4);

        sensUp = new POVButton(m_gunnerControl, 90);
        sensDown = new POVButton(m_gunnerControl, 270);

        unfoldArm = new JoystickButton(m_driverControl, 4);
        foldArm = new JoystickButton(m_driverControl, 3);
        activateHook = new JoystickButton(m_driverControl, 2);

        colorWheelSpinClockWise = new JoystickButton(m_gunnerControl, 6);
        colorWheelSpinCounter = new JoystickButton(m_gunnerControl, 5);

        colorWheelRise = new JoystickButton(m_driverControl, 1);//*
        colorWheelDrop = new JoystickButton(m_driverControl, 2);

        intakeForward.whenHeld(new IntakeSystem(true));
        intakeBackward.whenHeld(new IntakeSystem(false));
        shooterForward.whenHeld(new Shoot(mode.Forward));
        shooterBackward.whenHeld(new Shoot(mode.Reverse));
        shooterSmart.whenHeld(new Shoot(mode.Smart));

        switchLimeLight.whenPressed(new SwitchLimelightLight());

        sensUp.whenPressed(new ChangeIntakeSensitivity(true));
        sensDown.whenPressed(new ChangeIntakeSensitivity(false));
         
        unfoldArm.whenHeld(new ExtendArm(true));
        foldArm.whenHeld(new ExtendArm(false));
        activateHook.whenHeld(new ActivateHooks());
        
        //servo and color wheel stuffC 
        colorWheelRise.whenPressed(new ExtendColorWheel(true));
        colorWheelDrop.whenPressed(new ExtendColorWheel(false));

        colorWheelSpinClockWise.whenHeld(new SpinColorWheel(true));
        colorWheelSpinCounter.whenHeld(new SpinColorWheel(false));

        switchDrive.whenPressed(new SwitchDriveMode()); //switch drive mode when this button is pressed
        //alignTarget.whenHeld(new VisionAlignHorizontal()); //align to target when this button is pressed
        alignTarget.whenHeld(new VisionAlignRotational()); //align to target rotation when this button is pressed

        turnLeft = new POVButton(m_driverControl, 270);
        turnForward = new POVButton(m_driverControl, 0);
        turnRight = new POVButton(m_driverControl, 90);
        turnBack = new POVButton(m_driverControl, 180);

        resetGyro = Shuffleboard.getTab("Bridge").add("Reset Gyro", false)
                                    .withWidget(BuiltInWidgets.kToggleSwitch)
                                    .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
                                    .getEntry();

        //testing for adding a listener to this button
        resetGyro.addListener(new Consumer<EntryNotification>(){     
            @Override
            public void accept(EntryNotification arg0) {
                RobotMap.gyro.reset();
            }
        }, 0);
    }

    public static void SetDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(RobotMap.m_drive, new TeleOpDrive());
    }

    public static void PublishData() {

        //Limelight
        SmartDashboard.putNumber("LimelightX", RobotMap.limeLight.getX());
        SmartDashboard.putNumber("LimelightY", RobotMap.limeLight.getY());
        SmartDashboard.putNumber("LimelightArea", RobotMap.limeLight.getArea());

        SmartDashboard.putNumber("Intake Sensitivity", intakeSens);

        //test code
        //Shuffleboard.getTab("Bridge").add("LimeLightX", RobotMap.limeLight.getX());
        //Shuffleboard.getTab("Bridge").add("LimeLightY", RobotMap.limeLight.getX());
        //Shuffleboard.getTab("Bridge").add("LimeLightZ", RobotMap.limeLight.getX());

        SmartDashboard.putNumber("Gyro Yaw", RobotMap.gyro.getYaw());
        //Shuffleboard.getTab("Bridge").add("Gyro Yaw", RobotMap.gyro.getYaw())
                                    //.withWidget(BuiltInWidgets.kGyro);

        /* deprecated because of an attempt to use listeners
        //boolean box checks
        if(OI.resetGyro.getBoolean(true)) {
            OI.resetGyro.setBoolean(false);
            RobotMap.gyro.reset();
        }*/
    }
}