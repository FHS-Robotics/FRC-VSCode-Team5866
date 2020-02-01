/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*https://www.chiefdelphi.com/t/java-maxbotix-ultrasonic-sensor-code/102578   
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This sensor class is designed to use the maxbotix sensor
 * This code was found on a tutorial, but most of it is disregarded.  Just see GetRangeInCM()
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
		min_voltage = .1;//default .5, but that is for a diferent sensor
		voltage_range = 5.0 - min_voltage; //default 5, we are using 5 volts
		min_distance = 20.0; //default 3, but we are using centimeters
        distance_range = 570.0 - min_distance; //default 60, but the maxbotix 1200 has a max of 5.7 meters
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
    }

    // Just get the voltage.
    public double GetVoltage() {
        return channel.getVoltage();
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
        range = channel.getAverageVoltage();
        if (range < min_voltage) {
            return -2.0;
        }
        range = range/(5.0/512); //conversion to centimeters assuming 5v input
        return range;
    }
}
