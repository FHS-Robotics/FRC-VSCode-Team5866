package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class PIDShooter extends PIDSubsystem {

    double h1 = 0.939; //height of limelight lense from the ground
    double h2 = 2.2733; //height of vision target
    double h3 = 2.438; //height of inner port
    double a1 = 29.9965; //angle of limelight lense from the horizontal //32.3
    double a2; //angle of target from the center line of the limelight lense;
    double a3 = 45;
    double d; //distance from the vision target on the outer port;
    double d2 = 0.7747; //distance of inner port wall from the outer port
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
      //shootSpeed = output;
      //shootSpeed = getStartingVelocity();
      shootSpeed = getVelocityFromDate(getDistance());
    }
  
    @Override
    public double returnPIDInput() {
      // Return the process variable measurement here
      return getVelocityFromDate(getDistance());
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
        //System.out.println(d);
        double heightTotal = h2-h1 + h2-h3; //height of the target relative to height of limelight
        double velocity = d/(Math.cos(Math.toRadians(a3))*(Math.sqrt(Math.abs(2*(d - Math.tan(Math.toRadians(a3)) - heightTotal) / g)))); //take absolute value to avoid root of negative
        //System.out.println(velocity);
        return velocity;
    }

    /**
     * gets the returned velocity based on the excel data that we collecte
     * @param x
     * @return
     */
    public double getVelocityFromDate(double x) {
      if(x<=0)
        return 0;
      if(x<1.97)
        return 2.2;
      //double v = (0.0021*Math.pow(x, 6)) - (0.0614*Math.pow(x,5)) + (0.7324*Math.pow(x, 4)) - (4.5082*Math.pow(x, 3)) + (15.077*Math.pow(x, 2)) - (25.929*x) + 19.568;
      //double v = (0.0021*x*x*x*x*x*x) - (0.0614*x*x*x*x*x) + (0.7324*x*x*x*x) - (4.5082*x*x*x) + (15.077*x*x) - (25.929*x) + 19.568;
      double v = 0.0125*Math.pow(x, 4) - 0.2556*Math.pow(x, 3) + 1.8758*Math.pow(x, 2) - 5.7734*x + 7.9013;
      //double v = -0.0056*Math.pow(x, 5) + 0.1385*Math.pow(x, 4) - 1.3157*Math.pow(x, 3) + 6.0219*Math.pow(x, 2) - 13.2*x + 12.702;
      //System.out.println(v);
      return v;
    }

    public double getDistance() {
      a2 = RobotMap.limeLight.getY(); //set a2 to the angle of the target in the y
      //System.out.println(a2);
      d = (h2 - h1) / Math.tan(Math.toRadians(a1 + a2));
      d += d2;
      //System.out.println("distance to target: " + d + " meters");
      return d;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
