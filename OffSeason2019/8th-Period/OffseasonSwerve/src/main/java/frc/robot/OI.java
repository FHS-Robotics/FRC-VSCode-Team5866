package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * OI
 */
public class OI {

    public static Joystick driverController;

    public OI() {
        driverController = new Joystick(0);
    }
}