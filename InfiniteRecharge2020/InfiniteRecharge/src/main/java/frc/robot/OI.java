package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.SwitchDriveMode;
import frc.robot.commands.SetDriveAngle;
import frc.robot.commands.CableSystem;
import frc.robot.commands.HookSystem;
import frc.robot.commands.IntakeSystem;
import frc.robot.commands.SwitchDriveMode;
/**
 * OI
 */
public class OI {

    
	public static Joystick m_driverControl = new Joystick(0);
    public static Joystick m_gunnerControl = new Joystick(1);
    
    public static JoystickButton switchDrive;

    public static POVButton turnLeft;
    public static POVButton turnForward;
    public static POVButton turnRight;
    public static POVButton turnBack;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton hookExtend;
    public static JoystickButton hookRetract;
    public static JoystickButton raise;
    public static JoystickButton lower;

    public OI() {
        switchDrive = new JoystickButton(m_driverControl, 0);
        intakeForward = new JoystickButton(m_gunnerControl, 1);
        intakeBackward = new JoystickButton(m_gunnerControl, 2);
        hookExtend = new JoystickButton(m_driverControl, 6);//*TBD
        hookRetract = new JoystickButton(m_driverControl, 7);//*
        raise = new JoystickButton(m_driverControl, 5);//*
        lower = new JoystickButton(m_driverControl, 4);//*

        intakeForward.whenHeld(new IntakeSystem(true));
        intakeBackward.whenHeld(new IntakeSystem(false));
        hookExtend.whenHeld(new HookSystem(true));
        hookRetract.whenHeld(new HookSystem(false));
        raise.whenHeld(new CableSystem(true));
        lower.whenHeld(new CableSystem(false));

        switchDrive.whenPressed(new SwitchDriveMode()); //switch drive mode when this button is pressed

        turnLeft = new POVButton(m_driverControl, 270);
        turnForward = new POVButton(m_driverControl, 0);
        turnRight = new POVButton(m_driverControl, 90);
        turnBack = new POVButton(m_driverControl, 180);

        /*turnLeft.whenPressed(new SetDriveAngle(-90));
        turnForward.whenPressed(new SetDriveAngle(0));
        turnRight.whenPressed(new SetDriveAngle(90));
        turnBack.whenPressed(new SetDriveAngle(180));*/
    }
}