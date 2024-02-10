package frc.robot.utilities;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.simulation.SimDeviceDataJNI;

public final class SimUtils {
    public static void setGyroAngle(double setAngle) {
        int device = SimDeviceDataJNI.getSimDeviceHandle("navX-Sensor[0]");
        SimDouble angle = new SimDouble(SimDeviceDataJNI.getSimValueHandle(device, "Yaw"));
        angle.set(setAngle);
    }
}
