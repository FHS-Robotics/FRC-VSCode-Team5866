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
import frc.robot.commands.SwitchDriveMode;
import frc.robot.commands.TeleOpDrive;
import frc.robot.commands.VisionAlignHorizontal;
import frc.robot.commands.Shoot;
import frc.robot.commands.CableSystem;
import frc.robot.commands.ExtendColorWheel;
import frc.robot.commands.HookSystem;
import frc.robot.commands.IntakeSystem;/**
 * OI
 */
public class OI {

    
	public static Joystick m_driverControl = new Joystick(0);
    public static Joystick m_gunnerControl = new Joystick(1);
    
    public static JoystickButton switchDrive;
    public static JoystickButton alignTarget;

    public static POVButton turnLeft;
    public static POVButton turnForward;
    public static POVButton turnRight;
    public static POVButton turnBack;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton shooterForward;
    public static JoystickButton shootAll;

    public static JoystickButton hookExtend;
    public static JoystickButton hookRetract;
    public static JoystickButton raise;
    public static JoystickButton lower;

    public static JoystickButton colorWheelRise;
    public static JoystickButton colorWheelDrop;

    public static NetworkTableEntry resetGyro;

    /**
     * Initializer for all OI components
     */
    public OI() {
        switchDrive = new JoystickButton(m_driverControl, 1);
        alignTarget = new JoystickButton(m_driverControl, 8);

        intakeForward = new JoystickButton(m_gunnerControl, 1);
        intakeBackward = new JoystickButton(m_gunnerControl, 2);
        shooterForward = new JoystickButton(m_gunnerControl, 3);
        shootAll = new JoystickButton(m_gunnerControl, 4);

        hookExtend = new JoystickButton(m_driverControl, 6);//*TBD
        hookRetract = new JoystickButton(m_driverControl, 7);//*
        raise = new JoystickButton(m_driverControl, 5);//*
        lower = new JoystickButton(m_driverControl, 4);//*

        colorWheelRise = new JoystickButton(m_driverControl, 1);//*
        colorWheelDrop = new JoystickButton(m_driverControl, 2);

        intakeForward.whenHeld(new IntakeSystem(true));
        intakeBackward.whenHeld(new IntakeSystem(false));
        shooterForward.whenHeld(new Shoot());

        hookExtend.whenHeld(new HookSystem(true));
        hookRetract.whenHeld(new HookSystem(false));
        raise.whenHeld(new CableSystem(true));
        lower.whenHeld(new CableSystem(false));
        
        //servo and color wheel stuff
        colorWheelRise.whenPressed(new ExtendColorWheel(true));
        colorWheelDrop.whenPressed(new ExtendColorWheel(false));

        switchDrive.whenPressed(new SwitchDriveMode()); //switch drive mode when this button is pressed
        alignTarget.whenHeld(new VisionAlignHorizontal()); //align to target when this button is pressed

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
        //deprecated and moved to TeleOpDrive();
        /*turnLeft.whenPressed(new SetDriveAngle(-90));
        turnForward.whenPressed(new SetDriveAngle(0));
        turnRight.whenPressed(new SetDriveAngle(90));
        turnBack.whenPressed(new SetDriveAngle(180));*/
    }

    public static void SetDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(RobotMap.m_drive, new TeleOpDrive());
    }

    public static void PublishData() {

        //Limelight
        /*SmartDashboard.putNumber("LimelightX", RobotMap.limeLight.getX());
        SmartDashboard.putNumber("LimelightY", RobotMap.limeLight.getY());
        SmartDashboard.putNumber("LimelightArea", RobotMap.limeLight.getArea());*/

        //test code
        //Shuffleboard.getTab("Bridge").add("LimeLightX", RobotMap.limeLight.getX());
        //Shuffleboard.getTab("Bridge").add("LimeLightY", RobotMap.limeLight.getX());
        //Shuffleboard.getTab("Bridge").add("LimeLightZ", RobotMap.limeLight.getX());

        //SmartDashboard.putNumber("Gyro Yaw", RobotMap.gyro.getYaw());
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