
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
    public static XboxController driverController = new XboxController(0);
    public static XboxController gunnerController = new XboxController(1);

    public static Joystick driverStick = new Joystick(0);
    public static Joystick gunnerStick = new Joystick(1);
    

    public OI(){
        
    }
}