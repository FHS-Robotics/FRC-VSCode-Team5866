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
    double g = 9.8; //acceleration due to gravity

    public double shootSpeed = 0.0;
  
    public PIDShooter() {
      super(1, 0, 0);
    }

    //do not disable ever
    @Override
    public void periodic() {
      if(!getPIDController().isEnabled())
        enable();
    }
  
    @Override
    public void usePIDOutput(double output) {
      shootSpeed = output;
    }
  
    @Override
    public double returnPIDInput() {
      // Return the process variable measurement here
      return getStartingVelocity();
    }

    /**
    * Kinematics solution to find Starting Velocity(v0) based on parameters and common kinematics formulas <\n>
    * <li> v = v0y/sin(theta)
    * <li> v = v0x/cos(theta)
    * <li> v0x = x/t
    * <li> v0y = v0x*sin(theta)/cos(theta)
    * <li> v0y = x*tan(theta)/t
    * <li> y = y0 + v0yt - 1/2at^2
    * <li> y = x*tan(theta)*t/t - (1/2)*g*t^2
    * <li> t = sqrt(2(x*tan(theta)-y)/g)
    * <li> v0*cos(theta) = v0x = x/t = x/sqrt((x-tan(theta)-y)/g)
    * <li> @return: v0 = x/(cos(theta)*sqrt((x-tan(theta)-y)/g))
    */
    public double getStartingVelocity() {
        d = getDistance();
        double heightTotal = h2-h1 + h2-h3; //height of the target relative to height of limelight
        double velocity = d/(Math.sqrt(Math.abs(2*(d - Math.toDegrees(Math.tan(a1)) - heightTotal) / g))); //take absolute value to avoid root of negative
        return velocity;
    }
  
    public double getDistance() {
      a2 = RobotMap.limeLight.getY(); //set a2 to the angle of the target in the y
      d = (h2 - h1) / Math.toDegrees(Math.tan(a1 + a2));
      d += d2;
      return d;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
