package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TeleOpDrive;
import frc.robot.subsystems.Arm;

/**
 * This class holds static methods that send values into robot code
 * and sends debug output back out. It uses SmartDashboard right now,
 * but in the future values may be hard-coded.
 */
public final class Values {
    public static void init() {
        SmartDashboard.putNumber("intake_speed", 0.3);
        SmartDashboard.putNumber("arm_speed", 1);
        SmartDashboard.putNumber("elevator_speed", 1);
    }

    /**
     * This method controls the speed at which the Intake runs.
     * 
     * @see TeleOpDrive
     */
    public static double INTAKE_SPEED() {
        return SmartDashboard.getNumber("intake_speed", 0.3);
    }

    /**
     * This method controls the speed at which the Arm runs.
     * 
     * @see TeleOpDrive
     */
    public static double ARM_SPEED() {
        return SmartDashboard.getNumber("arm_speed", 1);
    }

    /**
     * This method controls the speed at which the Elevator runs.
     * 
     * @see TeleOpDrive
     */
    public static double ELEVATOR_SPEED() {
        return SmartDashboard.getNumber("elevator_speed", 1);
    }

    /**
     * This gives debug output from the Arm.moveArm() method.
     * 
     * @see Arm
     */
    public static void PUT_ARM_MOVE(double trueAmount) {
        SmartDashboard.putNumber("Arm.moveArm() trueAmount: ", trueAmount);
    }

    /**
     * This gives debug output from the Arm.periodic() method.
     * 
     * @see Arm
     */
    public static void PUT_ARM_PERIODIC(double counterGet) {
        SmartDashboard.putNumber("Arm.periodic() m_counter.get(): ", counterGet);
    }
}
