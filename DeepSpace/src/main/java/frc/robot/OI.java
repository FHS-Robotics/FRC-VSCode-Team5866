/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetLEDModeManual;
import frc.robot.commands.SetSensitivity;
import frc.robot.commands.SwitchCompressor;
import frc.robot.commands.SwitchControlMode;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.LowerClaw;
import frc.robot.commands.claw.OpenClaw;
import frc.robot.commands.claw.RaiseClaw;
import frc.robot.commands.robotLifter.LiftRobot;
import frc.robot.commands.robotLifter.LowerRobot;
import frc.robot.commands.robotLifter.LowerRobot;
import frc.robot.commands.cargodelivery.DeliverCargo;
import frc.robot.subsystems.LEDInterface.ColorMode;

/**
 * This class contains all of the objects for the operator interface
 */
public class OI {

    public static boolean mode = true;

    public static Joystick m_leftStick = new Joystick(0);
    public static Joystick m_rightStick = new Joystick(1);
    public static Joystick secondaryController = new Joystick(2);

    public static JoystickButton sensUp = new JoystickButton(m_rightStick, 5);
    public static JoystickButton sensDown = new JoystickButton(m_rightStick, 3);
    public static int sensitivity = 5; //from 1-10

    public static JoystickButton clawOpen = new JoystickButton(secondaryController, 1);
    public static JoystickButton clawClose = new JoystickButton(secondaryController, 2);
    public static JoystickButton clawRaise = new JoystickButton(secondaryController, 4);
    public static JoystickButton clawLower = new JoystickButton(secondaryController, 3);

    public static POVButton baseRaise = new POVButton(secondaryController, 90);
    public static POVButton baseLower = new POVButton(secondaryController, 270);


    public static JoystickButton deliverCargo = new JoystickButton(secondaryController, 8);


    public static JoystickButton compressorSwitch = new JoystickButton(secondaryController, 7);
    
    //buttons for manually setting the led mode
    public static JoystickButton setNeutral = new JoystickButton(m_leftStick, 12);
    public static JoystickButton setShowcase = new JoystickButton(m_leftStick, 11);

    public static JoystickButton switchMode = new JoystickButton(m_rightStick, 8);


    public OI()
    {
        mode = true;
        switchMode.whenPressed(new SwitchControlMode());

        sensUp.whenPressed(new SetSensitivity(true));
        sensDown.whenPressed(new SetSensitivity(false));
        SmartDashboard.putNumber("Joystick Sensitivity", sensitivity); //publish the sensitivity on the Shuffleboard


        clawOpen.whenPressed(new OpenClaw());
        clawClose.whenPressed(new CloseClaw());
        clawRaise.whenPressed(new RaiseClaw());
        clawLower.whenPressed(new LowerClaw());

        baseRaise.whenPressed(new LiftRobot());
        baseLower.whenPressed(new LowerRobot());

        compressorSwitch.whenPressed(new SwitchCompressor());

        deliverCargo.whenPressed(new DeliverCargo(true, 1));

        //publish the starting positions of the wrist and claw to the Shuffleboard
        SmartDashboard.putString("Claw State", "Closed");
        SmartDashboard.putString("Wrist State", "Raised");


        setNeutral.whenPressed(new SetLEDModeManual(ColorMode.neutral));
        setShowcase.whenPressed(new SetLEDModeManual(ColorMode.showcase));
    }
}
 