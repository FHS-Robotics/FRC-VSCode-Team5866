package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class PIDShooter extends PIDSubsystem {

    double h1 = 0; //height of limelight lense from the ground
    double h2 = 0; //height of vision target
    double h3 = 0; //height of inner port
    double a1 = 0; //angle of limelight lense from the horizontal
    double a2; //angle of target from the center line of the limelight lense;
    double d; //distance from the vision target on the outer port;
    double d2 = 0; //distance of inner port wall from the outer port
  
    public PIDShooter() {
      super(0, 0, 0);
    }
  
    @Override
    public void usePIDOutput(double output) {
      // Use the output here
    }
  
    @Override
    public double returnPIDInput() {
      // Return the process variable measurement here
      return 0;
    }

    //y = y0 + v0t = 1/2at^2
    //vy0 = sqrt(2g(y-y0))
    //v=vy0/sin(theta)
    public double getStartingVelocity() {
        d = getDistance();
        
        return 0;
    }
  
    public double getDistance() {
      a2 = RobotMap.limeLight.getY(); //set a2 to the angle of the target in the y
      d = (h2 - h1) / Math.tan(a1 + a2);
      d += d2;
      return d;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
