package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class MockMotorController implements MotorController {
    public List<Double> setSpeeds = new ArrayList<>();
    public boolean wasDisabled = false;
    public boolean wasStopped = false;
    public boolean isInverted = false;

    public void resetMock() {
        setSpeeds = new ArrayList<>();
        wasDisabled = false;
        wasStopped = false;
        isInverted = false;
    }

    @Override
    public void set(double speed) {
        setSpeeds.add(speed);
    }

    public double get() {
        int size = setSpeeds.size();
        return size > 0 ? setSpeeds.get(size - 1) : 0;
    }

    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }

    public boolean getInverted() {
        return isInverted;
    }

    // In WPI_TalonFX disable() and stopMotor() do
    // the same thing. They seem to be synonyms.

    public void disable() {
        wasDisabled = true;
        set(0);
    }

    public void stopMotor() {
        wasStopped = true;
    }
}
