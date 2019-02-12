package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WristSystem{

        PWMSpeedController wristMotor;

        //init wrist system
        public WristSystem(PWMSpeedController _motor){
                wristMotor = _motor; //we might need another motor for the wrist mechanism, but for now we only have one.
        }
        
        //move method
        public void move(double speed){
                wristMotor.set(speed);
            }
}