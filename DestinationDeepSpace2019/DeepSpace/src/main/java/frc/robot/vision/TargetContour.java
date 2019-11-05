
package frc.robot.vision;

/**
 * Add your docs here.
 */
public class TargetContour {

    public double area;
    public double centerX;
    public double centerY;
    public double width;
    public double height;

    public TargetContour(double _area, double _centerX, double _centerY, double _width, double _height)
    {
        area = _area;
        centerX = _centerX;
        centerY = _centerY;
        width = _width;
        height = _height;
    }
}
