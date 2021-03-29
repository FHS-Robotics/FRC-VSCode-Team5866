package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.Intake;
import frc.robot.Commands.Shoot;
import frc.robot.Subsystems.Shooter;

public class OI {

    //#region constants
    public static final int c_driver = 0;
    public static final int c_gunner = 0;

    //xbox control axes
    public static final int c_driveForwardAxis = 1;
    public static final int c_rotateAxis = -1;
    public static final int c_turretRotateAxis = 0;

    //xbox button values
    public static final int c_a = 0;
    public static final int c_b = 1;
    public static final int c_x = 2;
    public static final int c_y = 3;
    public static final int c_start = 4;
    public static final int c_select = 5;
    public static final int c_bumperLeft = -1;
    public static final int c_bumperRight = -1;
    public static final int c_leftThumb = -1;
    public static final int c_rightThumb = -1;
    //#endregion

    public static XboxController m_driverControl = new XboxController(0);
    public static XboxController m_gunnerControl = new XboxController(1);

    public static JoystickButton ballLock; //lock onto balls

    public static JoystickButton shootForward;
    public static JoystickButton shootBackward;
    public static JoystickButton intakeForward;
    public static JoystickButton intakeBackward;
    public static JoystickButton turretLock;

    public OI() {

        shootForward = new JoystickButton(m_gunnerControl, c_x);
        shootBackward = new JoystickButton(m_gunnerControl, c_y);
        intakeForward = new JoystickButton(m_gunnerControl, c_a);
        intakeBackward = new JoystickButton(m_gunnerControl, c_b);

        shootForward.whileHeld(new Shoot(true));
        shootBackward.whileHeld(new Shoot(false));
        intakeForward.whileHeld(new Intake(true));
        intakeBackward.whileHeld(new Intake(false));
    }
}
