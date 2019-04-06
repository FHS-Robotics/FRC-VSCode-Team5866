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
import frc.robot.commands.SwitchControlMode;
import frc.robot.commands.PID.TurnRobot;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.LowerClaw;
import frc.robot.commands.claw.OpenClaw;
import frc.robot.commands.claw.RaiseClaw;
import frc.robot.commands.robotLifter.LiftRobot;
import frc.robot.commands.robotLifter.LowerRobot;
import frc.robot.commands.cargodelivery.DeliverCargo;
import frc.robot.subsystems.LEDInterface.ColorMode;

/**
 * This class contains all of the objects for the operator interface
 */
public class OI {

    public static boolean mode = true;

    public static boolean pidDriving = false;

    //all methods using the left and right joysticks will be commented out since we are using two xbox controllers now
    ////public static Joystick m_leftStick = new Joystick(0);
    ////public static Joystick m_rightStick = new Joystick(1);
    public static Joystick driverController = new Joystick(3); //white
    public static Joystick secondaryController = new Joystick(2); //blue

    ////public static JoystickButton sensUp = new JoystickButton(m_rightStick, 5);
    ////public static JoystickButton sensDown = new JoystickButton(m_rightStick, 3);
    public static JoystickButton sensUp = new JoystickButton(driverController, 6);
    public static JoystickButton sensDown = new JoystickButton(driverController, 5);

    public static int sensitivity = 7; //from 0-10

    ////public static POVButton turnRight = new POVButton(m_rightStick, 90);
    ////public static POVButton turnLeft = new POVButton(m_rightStick, 270);
    ////public static POVButton turnForward = new POVButton(m_rightStick, 0);
    ////public static POVButton turnBackward = new POVButton(m_rightStick, 180);
    public static POVButton turnRight = new POVButton(driverController, 90);
    public static POVButton turnLeft = new POVButton(driverController, 270);
    public static POVButton turnForward = new POVButton(driverController, 0);
    public static POVButton turnBackward = new POVButton(driverController, 180);

    public static JoystickButton clawOpen = new JoystickButton(secondaryController, 1);
    public static JoystickButton clawClose = new JoystickButton(secondaryController, 2);
    public static JoystickButton clawRaise = new JoystickButton(secondaryController, 4);
    public static JoystickButton clawLower = new JoystickButton(secondaryController, 3);

    public static JoystickButton driverClawOpen = new JoystickButton(driverController, 1);
    public static JoystickButton driverClawClose = new JoystickButton(driverController, 2);
    public static JoystickButton driverClawRaise = new JoystickButton(driverController, 4);
    public static JoystickButton driverClawLower = new JoystickButton(driverController, 3);


    public static POVButton baseRaise = new POVButton(secondaryController, 90);
    public static POVButton baseLower = new POVButton(secondaryController, 270);

    public static JoystickButton driverBaseRaise = new JoystickButton(driverController, 7);
    public static JoystickButton driverBaseLower = new JoystickButton(driverController, 8);


    public static JoystickButton deliverCargo = new JoystickButton(secondaryController, 8);
    
    //buttons for manually setting the led mode
    ////public static JoystickButton setNeutral = new JoystickButton(m_leftStick, 12);
    ////public static JoystickButton setShowcase = new JoystickButton(m_leftStick, 11);

    ////public static JoystickButton switchMode = new JoystickButton(m_rightStick, 8);


    public OI()
    {
        mode = true;
        pidDriving = false;
        ////switchMode.whenPressed(new SwitchControlMode());

        sensUp.whenPressed(new SetSensitivity(true));
        sensDown.whenPressed(new SetSensitivity(false));
        SmartDashboard.putNumber("Joystick Sensitivity", sensitivity); //publish the sensitivity on the Shuffleboard

        //turn based on the direction of the POV buttons
        turnRight.whenPressed(new TurnRobot(90));
        turnLeft.whenPressed(new TurnRobot(-90));
        turnForward.whenPressed(new TurnRobot(0));
        turnBackward.whenPressed(new TurnRobot(180));

        clawOpen.whenPressed(new OpenClaw());
        clawClose.whenPressed(new CloseClaw());
        clawRaise.whenPressed(new RaiseClaw());
        clawLower.whenPressed(new LowerClaw());

        driverClawOpen.whenPressed(new OpenClaw());
        driverClawClose.whenPressed(new CloseClaw());
        driverClawRaise.whenPressed(new RaiseClaw());
        driverClawLower.whenPressed(new LowerClaw());

        baseRaise.whenPressed(new LiftRobot());
        baseLower.whenPressed(new LowerRobot());
        
        driverBaseRaise.whenPressed(new LiftRobot());
        driverBaseLower.whenPressed(new LowerRobot());

        deliverCargo.whenPressed(new DeliverCargo(true, 1));

        //publish the starting positions of the wrist and claw to the Shuffleboard
        SmartDashboard.putString("Claw State", "Closed");
        SmartDashboard.putString("Wrist State", "Raised");

        SmartDashboard.putBoolean("PID Driving", false);


        ////setNeutral.whenPressed(new SetLEDModeManual(ColorMode.neutral));
        ////setShowcase.whenPressed(new SetLEDModeManual(ColorMode.showcase));


        /*Start the iron dashboard for keyboard control
        ironDashboard = new IronDashboardWASD();
        try {
            ironDashboard.run();
        } catch (Exception e) {
            System.out.println("Iron Dashboard connection not started");
            System.out.print(e);
        }*/
    }

    public static void setPIDActive(boolean state)
    {
        pidDriving = state;
    }
}
 