package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    public static Joystick m_driverControl = new Joystick(0);
    public static Joystick m_gunnerControl = new Joystick(1);

    public static JoystickButton ballLock; //lock onto balls

    public static JoystickButton shootForward;
    public static JoystickButton shootBackward;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton turretLock;

    public OI() {

    }
}
