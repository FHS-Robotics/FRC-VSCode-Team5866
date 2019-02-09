package main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WristSystem{
        public WristSystem(){
                PWMTalonSRX wristMotor = new PWMTalonSRX(); //we might need another motor for the wrist mechanism, but for now we only have one.
        }
        

        public void move(double speed){
                wristMotor.set(speed);
            }
}