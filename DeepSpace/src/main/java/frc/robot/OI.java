/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.SetSensitivity;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.OpenClaw;

/**
 * This class contains all of the objects for the operator interface
 */
public class OI {

    public static Joystick m_leftStick = new Joystick(0);
    public static Joystick m_rightStick = new Joystick(1);
    public static Joystick secondaryController = new Joystick(2);

    public static JoystickButton sensUp = new JoystickButton(m_rightStick, 5);
    public static JoystickButton sensDown = new JoystickButton(m_rightStick, 3);
    public static int sensitivity = 5; //from 1-10

    public static JoystickButton clawOpen = new JoystickButton(secondaryController, 1);
	public static JoystickButton clawClose = new JoystickButton(secondaryController, 2);


    public OI()
    {
        sensUp.whenPressed(new SetSensitivity(true));
        sensDown.whenPressed(new SetSensitivity(false));

        clawOpen.whenPressed(new OpenClaw());
        clawClose.whenPressed(new CloseClaw());
    }
}
 