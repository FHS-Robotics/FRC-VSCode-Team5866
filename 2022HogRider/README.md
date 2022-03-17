<h1 align="center">
5866 Fe Iron Tigers PRESENTS HogRider
</h1>
<hr/>
<p align="center">
HogRider is our 2022 FRC robot!
</p>
<p align="center">
<a href="src/main/java/frc/robot/Robot.java">
Robot.java
</a>
is documented to give <em>anyone</em> a rundown on how our robot's software is structured this year.
</p>

<em>

## Robot Software
</em>

* WPILib's Command-Based Framework
* [Main.java], [Robot.java], [RobotContainer.java] and [Constants.java]

[Main.java]: src/main/java/frc/robot/Main.java
[Robot.java]: src/main/java/frc/robot/Robot.java
[RobotContainer.java]: src/main/java/frc/robot/RobotContainer.java
[Constants.java]: src/main/java/frc/robot/Constants.java\

<em>

## Unit Testing
</em>

* They make for useful examples to study!
* Located at [src/test/java/frc/robot]
* Unit Tests require adding the following to `build.gradle`:
    ```groovy
    test {
        useJUnit()
    }
    ```

[src/test/java/frc/robot]: src/test/java/frc/robot
