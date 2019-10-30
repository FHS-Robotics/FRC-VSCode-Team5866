package org.usfirst.frc.team5866.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


public class clawMech extends Subsystem {
	static DoubleSolenoid gripper;
	static DoubleSolenoid tilter;
    public clawMech(DoubleSolenoid gripper, DoubleSolenoid tilter){
    	clawMech.gripper = gripper;
    	clawMech.tilter = tilter;
    }

    public void initDefaultCommand() {}
    
    public void close() {
    	gripper.set(DoubleSolenoid.Value.kForward);
    }
    
    public void open() {
    	gripper.set(DoubleSolenoid.Value.kReverse);
    }
    public void up() {
    	tilter.set(DoubleSolenoid.Value.kForward);
    }
    public void down() {
    	tilter.set(DoubleSolenoid.Value.kReverse);
    }
}

