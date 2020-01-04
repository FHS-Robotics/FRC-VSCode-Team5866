package frc.robot.subsystems;

/**
 * SwerveDrive
 */
public class SwerveDrive {

    private static double L;
    private static double W;

    SwerveModule front;
    SwerveModule backLeft;
    SwerveModule backRight;

    public SwerveDrive(double _length, double _width, SwerveModule _front, SwerveModule _backLeft, SwerveModule _backRight)
    {
        L = _length;
        W = _width;

        front = _front;
        backLeft = _backLeft;
        backRight = _backRight;
    }

    /**
     * 
     * @param x1 - The x axis of the driving joystick
     * @param y1 - The y axis of the driving joystick
     * @param x2 - The x axis of the rotational joystick
     */
    public void drive (double x1, double y1, double x2) {
        double diameter = Math.sqrt ((L * L) + (W * W)); //diameter of the bot calculated via the pythagorean theorem
        y1 *= -1; //invert y axis of drive joystick
        x1 *= -1;
        x2 *= -2;
    
        double a = x1 - x2 * (L / diameter);
        double b = x1 + x2 * (L / diameter);
        double c = y1 - x2 * (W / diameter);
        double d = y1 + x2 * (W / diameter);
    
        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));
    
        double backRightAngle = Math.atan2 (a, d) / Math.PI;
        double backLeftAngle = Math.atan2 (a, c) / Math.PI;
        double frontRightAngle = Math.atan2 (b, d) / Math.PI;
        double frontLeftAngle = Math.atan2 (b, c) / Math.PI;

        //interpolate from 4 wheels to 3 wheels
        double frontSpeed = (frontLeftSpeed + frontRightSpeed) / 2;
        double frontAngle = (frontLeftAngle + frontRightAngle) / 2;

        //drive all serve drives using calculated 
        front.drive(frontSpeed , frontAngle);
        backLeft.drive(backLeftSpeed, backLeftAngle);
        backRight.drive(backRightSpeed, backRightAngle);
    }

    /**
     * @param value = angle from -1 : 1
     */
    public void setSetpoint(double value) {
        front.setSetpoint(value);
        backLeft.setSetpoint(value);
        backRight.setSetpoint(value);
    }

    public void enable() {
        front.enable();
        backLeft.enable();
        backRight.enable();
    }
}