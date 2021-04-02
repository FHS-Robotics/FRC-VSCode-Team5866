package frc.robot.Subsystems;

import java.util.Hashtable;
import java.util.TreeMap;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.PWMSparkMax;

public class IntakeSystem {

    public CANSparkMax skirt;
    public CANSparkMax stageOne;
    public CANSparkMax stageTwo;

    public static Hashtable<String, Double> speeds;

    public IntakeSystem(CANSparkMax _skirt, CANSparkMax _stageOne, CANSparkMax _stageTwo) {
        skirt = _skirt;
        stageOne = _stageOne;
        stageTwo = _stageTwo;

        speeds = new Hashtable<>();
        speeds.put("skirt", 0.25);
        speeds.put("stageOne", -0.25);
        speeds.put("stageTwo", -0.25);
    }

    public void intake(boolean direction) {
        if(direction) {
            skirt.set(speeds.get("skirt"));
            stageOne.set(speeds.get("stageOne"));
            stageTwo.set(speeds.get("stageTwo"));
        }
        else {
            skirt.set(-speeds.get("skirt"));
            stageOne.set(-speeds.get("stageOne"));
            stageTwo.set(-speeds.get("stageTwo"));
        }
    }

    public void release() {
        skirt.set(0);
        stageOne.set(0);
        stageTwo.set(0);
    }
    
}