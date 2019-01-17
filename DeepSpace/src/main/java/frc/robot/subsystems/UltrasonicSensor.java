/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This sensor is designed to use the maxbotix sensor
 */
public class UltrasonicSensor extends Subsystem {

    private final double IN_TO_CM_CONVERSION = 2.54;
    private boolean use_units;    //Are we using units or just returning voltage?
    private double min_voltage;	  //Minimum voltage the ultrasonic sensor can return
    private double voltage_range; //The range of the voltages returned by the sensor (maximum - minimum)
    private double min_distance;  //Minimum distance the ultrasonic sensor can return in inches
    private double distance_range;//The range of the distances returned by this class in inches (maximum - minimum)
    AnalogInput channel;

    @Override
    protected void initDefaultCommand() {
        
    }

    //default constructor
    public UltrasonicSensor(int _channel) {
        channel = new AnalogInput(_channel);
        //default values
		use_units = true;
		min_voltage = .5;
		voltage_range = 5.0 - min_voltage;
		min_distance = 3.0;
        distance_range = 60.0 - min_distance;
        
        setSubsystem("UltrasonicSensor");
    }

    //constructor
    public UltrasonicSensor(int _channel, boolean _use_units, double _min_voltage,
            double _max_voltage, double _min_distance, double _max_distance) {
        channel = new AnalogInput(_channel);
        //only use unit-specific variables if we're using units
        if (_use_units) {
            use_units = true;
            min_voltage = _min_voltage;
            voltage_range = _max_voltage - _min_voltage;
            min_distance = _min_distance;
            distance_range = _max_distance - _min_distance;
        }
        setSubsystem("UltrasonicSensor");
    }

    // Just get the voltage.
    public double GetVoltage() {
        return channel.getVoltage();
    }

    /* GetRangeInInches
     * Returns the range in inches
     * Returns -1.0 if units are not being used
     * Returns -2.0 if the voltage is below the minimum voltage
     */
    public double GetRangeInInches() {
        double range;
        //if we're not using units, return -1, a range that will most likely never be returned
        if (!use_units) {
            return -1.0;
        }
        range = channel.getVoltage();
        if (range < min_voltage) {
            return -2.0;
        }
        //first, normalize the voltage
        range = (range - min_voltage) / voltage_range;
        //next, denormalize to the unit range
        range = (range * distance_range) + min_distance;
        return range;
    }

    /* GetRangeInCM
     * Returns the range in centimeters
     * Returns -1.0 if units are not being used
     * Returns -2.0 if the voltage is below the minimum voltage
     */
    public double GetRangeInCM() {
        double range;
        //if we're not using units, return -1, a range that will most likely never be returned
        if (!use_units) {
            return -1.0;
        }
        range = channel.getVoltage();
        if (range < min_voltage) {
            return -2.0;
        }
        //first, normalize the voltage
        range = (range - min_voltage) / voltage_range;
        //next, denormalize to the unit range
        range = (range * distance_range) + min_distance;
        //finally, convert to centimeters
        range *= IN_TO_CM_CONVERSION;
        return range;
    }
}