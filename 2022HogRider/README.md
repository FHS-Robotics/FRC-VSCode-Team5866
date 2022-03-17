<h1 align="center">
5866 Fe Iron Tigers PRESENTS HogRider
</h1>
<hr />
<h2 align="center">
HogRider is our 2022 FRC robot!
</h2>
<h3 align="center">
<a href="src/main/java/frc/robot/Robot.java">
Robot.java
</a>
is documented to give <em>anyone</em> a rundown on how our robot's software is structured this year.
</h3>
<hr />

<h3 align="right">Using the Command-Based Framework</h3>
<img align="right" alt="Robot Project in Visual Studio Code" src="images/Robot%20Software%20Visual%20Studio%20Code.png" width="270" height="294" />
<hr />

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

* They make for useful examples to study!
* Test code at <a href="src/test/java/frc/robot">src/test/java/frc/robot</a>
* You may add unit tests to your project by adding <code>test { useJUnit() }</code> to build.gradle:

<h3 align="right">An Autonomous Path on the Simulation GUI.</h3>

![Simulated Robot Autonomous Mode on Simulation GUI](images/Autonomous%20Simulation%20GUI.png)

* Simulated Drive, Arm, Intake, and Elevator subsystems.
* A `Field2d` is fed the robot's location as well as the robot's trajectory.
* That `Field2d` is transmitted through Network Tables, and it is visualized on the Simulation GUI.
