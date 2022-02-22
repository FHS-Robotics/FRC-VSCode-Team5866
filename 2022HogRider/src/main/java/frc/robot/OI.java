
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


/**
 * This holds input devices that control the robot's behavior.
 */
public final class OI {
    public static XboxController driverController = new XboxController(0);
    public static XboxController gunnerController = new XboxController(1);

    // TODO: Do we still need `JoyStick`s? 
    public static Joystick driverStick = new Joystick(0);
    public static Joystick gunnerStick = new Joystick(1);
}