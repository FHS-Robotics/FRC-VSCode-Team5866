package frc.robot.Subsystems;

import java.util.Hashtable;
import java.util.TreeMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;

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

        speeds.put("skirt", 0.5);
        speeds.put("stageOne", 1.0);
        speeds.put("stageTwo", 1.0);
    }

    public void intake(boolean direction) {
        skirt.set(speeds.get("skirt"));
        stageOne.set(speeds.get("stageOne"));
        stageTwo.set(speeds.get("stageTwo"));
    }
    
}