package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class Values {
    public static void init() {
        SmartDashboard.putNumber("intake_speed", 0.3);
        SmartDashboard.putNumber("arm_speed", 1);
    }

    public static double INTAKE_SPEED() {
        return SmartDashboard.getNumber("intake_speed", 0.3);
    }

    public static double ARN_SPEED() {
        return SmartDashboard.getNumber("arm_speed", 1);
    }
}
