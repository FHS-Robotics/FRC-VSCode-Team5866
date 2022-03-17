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
    * [TeleopCommand.java] and [AutonomousCommand.java]
* [Main.java], [Robot.java], [RobotContainer.java] and [Constants.java]

[TeleopCommand.java]: src/main/java/frc/robot/commands/TeleopCommand.java
[AutonomousCommand.java]: src/main/java/frc/robot/commands/AutonomousCommand.java

[Main.java]: src/main/java/frc/robot/Main.java
[Robot.java]: src/main/java/frc/robot/Robot.java
[RobotContainer.java]: src/main/java/frc/robot/RobotContainer.java
[Constants.java]: src/main/java/frc/robot/Constants.java\

<em>

## Unit Testing
</em>

<h3 align="right">Files involved in our Unit Tests</h3>
<pre>
src/test/java/frc/robot/
├─ subsystems/
│  ├─ ArmTest.java
│  ├─ ElevatorTest.java
│  ├─ IntakeTest.java
├─ utilities/
│  ├─ AutoStrategyTest.java
│  ├─ ProxyCommandBaseTest.java
├─ MockMotorController.java
gradle.build
</pre>

<ul>
<li> <p align="right">
They make for useful examples to study!
</p> </li>
<li> <p align="right">
Test code at <a href="src/test/java/frc/robot">src/test/java/frc/robot</a>
</p> </li>
<li> <p align="right">
You may add unit tests to your project by adding <code>test { useJUnit() }</code> to build.gradle:
</p> </li>
</ul>

<em>

## Robot Simulation
</em>

<h3 align="left">An Autonomous Path on the Simulation GUI.</h3>

![Simulated Robot Autonomous Mode on Simulation GUI](images/Autonomous%20Simulation%20GUI.png)

* Simulated Drive, Arm, Intake, and Elevator subsystems.
* A `Field2d` is fed the robot's location as well as the robot's trajectory.
* That `Field2d` is transmitted through Network Tables, and it is visualized on the Simulation GUI.
