package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * OI
 */
public class OI {

    public static Joystick m_driverControl = new Joystick(0);
    public static Joystick m_gunnerControl = new Joystick(1);
    
    public static JoystickButton switchDrive;
    public static JoystickButton intakeStart;

    public OI() {
        switchDrive = new JoystickButton(m_driverControl, 0);
        intakeStart = new JoystickButton(m_driverControl, 1);
    }
}