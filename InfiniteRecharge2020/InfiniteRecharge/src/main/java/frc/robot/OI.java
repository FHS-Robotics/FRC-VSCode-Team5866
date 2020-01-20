package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.SwitchDriveMode;
import frc.robot.commands.SetDriveAngle;

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

    public OI() {
        switchDrive = new JoystickButton(m_driverControl, 1);
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