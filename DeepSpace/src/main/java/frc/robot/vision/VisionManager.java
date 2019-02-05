
package frc.robot.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class VisionManager {

    public static NetworkTable table;
    public static TargetContour[] targets = new TargetContour[10]; //allocate for 10 targets just in case; this should just be 2 though

    public static double[] defaultValue = new double[0];

    //values for filtering the contours
    public static double minWHRatio; //minimum width/height ratio
    public static double maxWHRatio; //maximum width/height ratio
    public static double minCenterXPos, minCenterYPos; //minimum center x and y
    public static double maxCenterXPos, maxCenterYPos; //maximum center x and y

    public static void init ()
    {
        //declare where we will be pullig vision information from
        table = NetworkTable.getTable("GRIP/visionTargetReport");

        minWHRatio = .3;
        maxWHRatio = .9;
        minCenterXPos = 100;
        minCenterYPos = 0;
        maxCenterXPos = 540; //100 less than 640, the output image of the OpenCV code
        maxCenterYPos = 10000; //setting the y values very high; no restrictions yet
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
}
