package main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WristSystem{
        PWMTalonSRX wristMotor = new PWMTalonSRX();

        public void move(double speed){
                wristMotor.set(speed);
            }
}