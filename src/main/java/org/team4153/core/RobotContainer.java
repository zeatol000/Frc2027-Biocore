package org.team4153.core;

import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Command;

/** An abstract robot container. It is recommended to use {@link BaseRobotContainer} instead.
 * Robot containers separate the low-level hardware code (the robot) from the high-level
 * (robot container) code.
 */
public interface RobotContainer {

   /** Use this to pass the autonomous command to the main {@link BaseRobot} class.
    * See {@link #startRecording()}
    */
   Command auto();

   /** Use this method to define your trigger -> command mappings.
    * Triggers can be created via the
    * {@link org.wpilib.command3.Trigger#Trigger(java.util.function.BooleanSupplier)}
    * constructor with an arbitrary predicate or via the named
    * factories in {@link org.wpilib.command3.button.CommandXboxController}
    *
    * The reason why this isn't fully in the config file is because the trigger
	 * actions have complex results. E.g., how do you represent
	 * XButton.toggleOnTrue(swerve.pointToHubCommand()) as a hocon config?
    * My best bet would be ```
    * XButton {
    *   toggle = true
    *   action {
    *     mechanism = "swerve"
    *     action = "pointToHubCommand"
    *   }
    * }```.
    * Although automating the creation of the Trigger would mean using Java
	 * reflection, which adds unesseccary complexity for a basic FRC bot.
    */
   void configureBindings();

   /** Start recording driver input, discarding whatever was previously recorded.
    * Call from teleopInit().
    *
    * Recordings are the default way of making autonomous commands. Put simply,
    * while recording, we read the inputs and write them to a file. Once we
    * finish the recording, we stop reading inputs.
    * After the file has been saved, and we enable auto, we then reread the
    * file and forward all recording to the inputs.
    *
    * This can be adapted to a custom design, where the user presses a button
    * and the bot will follow a 'complex command' as saved in a file, but that
    * is out of the scope of this current library.
    */
   default void startRecording() {};

   /** Stops a recording if active, and saves it to the deploy directory.
    * Call from disabledInit()
    */
   default void stopRecording() {};

   /** Call once per loop from robotPeriodic() to sample driver input while
    * recording.
    */
   default void recordPeriodic() {};
}
