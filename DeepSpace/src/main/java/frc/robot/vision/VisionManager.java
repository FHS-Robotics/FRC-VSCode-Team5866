
package frc.robot.vision;

//import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public final class VisionManager {

    public static NetworkTable table;
    public static TargetContour[] targets = new TargetContour[10]; //allocate for 10 targets just in case; this should just be 2 though

    public static double[] defaultValue = new double[0];

    //values for filtering the contours
    public static double minWHRatio; //minimum width/height ratio
    public static double maxWHRatio; //maximum width/height ratio
    public static double minCenterXPos, minCenterYPos; //minimum center x and y
    public static double maxCenterXPos, maxCenterYPos; //maximum center x and y


    //#region Heading
    /**
     * (These values were gotten off of a website so they may be off)
     * The horizontal viewing angle of the Microsoft Lifecam 3000 is 60 degrees
     * The vertical viewing angle is 34.3 degrees
     * The diagonal viewing angle is 68.5 degrees
     * 
     * The resolution of the Lifecam after processing is 640 * 480
     */
    private static double camPixWidth; //default [640]
    private static double camPixHeight; //default [480]
    private static double viewingAngleH ; //default [34.3] degrees
    private static double viewingAngleV; //default [60] degrees

    public static double headConRatio; //heading conversion ratio from the position (in pixels) of a point to a heading angle
    //#endregion

    public static void init ()
    {
        //declare where we will be pullig vision information from
        //table = NetworkTable.getTable("GRIP/visionTargetReport");
        table = NetworkTable.getTable("GRIP/visionTargetReport");

        minWHRatio = .3;
        maxWHRatio = .9;
        minCenterXPos = 100;
        minCenterYPos = 0;
        maxCenterXPos = 540; //100 less than 640, the output image of the OpenCV code
        maxCenterYPos = 10000; //setting the y values very high; no restrictions yet


        camPixWidth = 640;
        camPixHeight = 480;
        viewingAngleH = 34.3;
        viewingAngleV = 60;

        headConRatio = camPixWidth / viewingAngleH;
    }

    public static void FindTargets()
    {
        if(table != null)
        {
            //get all of the value arrays in our table
            double[] areas = table.getNumberArray("area", defaultValue);
            double[] centerXArray = table.getNumberArray("centerX", defaultValue);
            double[] centerYArray = table.getNumberArray("centerY", defaultValue);
            double[] widths = table.getNumberArray("width", defaultValue);
            double[] heights = table.getNumberArray("height", defaultValue);

            TargetContour[] foundTargets = new TargetContour[10];

            int i = 0;
            for(double area : areas)
            {
                double centerX = centerXArray[i];
                double centerY = centerYArray[i];
                double width = widths[i];
                double height = heights[i];

                //the width of the tape is 2 in, and the height is 5.25 in, so the perfect ratio for
                //our target should be about .381, but we will have a little bit of variance
                if(width / height >= .3 && width / height <= .9)
                {
                    if(centerX > minCenterXPos 
                    && centerX < maxCenterXPos 
                    && centerY > minCenterYPos 
                    && centerY < maxCenterYPos)
                    {
                        TargetContour newContour = new TargetContour(area, centerX, centerY, width, height);
                        foundTargets[i] = newContour;
                    }
                }
                i++;
            }
            targets = foundTargets;
        }
    }

    /**
     * Returns a heading from the average location of all of the found targets stored by this class
     * @return heading : the found heading
     */
    public static double FindHeading()
    {
        //for the geometric center of our vision targets
        double xCenter = 0; 
        //double yCenter = 0;

        for(TargetContour t : targets)
        {
            xCenter += t.centerX;
            //yCenter += t.centerY;
        }
        xCenter = xCenter / targets.length; //divide to find average x position; same for y 
        //yCenter = yCenter / targets.length;

        double heading = xCenter / headConRatio;
        heading -= camPixWidth / 2; //converts into an angle to a positive or negative angle that we can then rotate towards

        return heading;
    }
}
