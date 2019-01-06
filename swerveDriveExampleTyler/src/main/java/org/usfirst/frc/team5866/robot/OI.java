package org.usfirst.frc.team5866.robot;

import org.usfirst.frc.team5866.robot.commands.CloseClaw;
import org.usfirst.frc.team5866.robot.commands.OpenClaw;
import org.usfirst.frc.team5866.robot.commands.TiltDown;
import org.usfirst.frc.team5866.robot.commands.TiltUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick rightDriveStick = new Joystick(0);
	public static Joystick leftDriveStick = new Joystick(1);
	public static Joystick secondaryController = new Joystick(2);	
	
	public static JoystickButton clawOpen = new JoystickButton(secondaryController, 1);
	public static JoystickButton clawClose = new JoystickButton(secondaryController, 2);
	public static JoystickButton clawUp = new JoystickButton(secondaryController, 3);
	public static JoystickButton clawDown = new JoystickButton(secondaryController, 4);

	public OI() {
		clawUp.whenPressed(new TiltUp());
		clawDown.whenPressed(new TiltDown());
		clawOpen.whenPressed(new OpenClaw());
		clawClose.whenPressed(new CloseClaw());
	}
}
