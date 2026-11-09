package org.team4153.core;

import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.framework.TimedRobot;

import org.team4153.core.BaseRobotContainer;
import org.team4153.core.config.Conf;
import org.team4153.core.config.Log;

import org.wpilib.command3.*;

/** The main class that handles hardware connection.
 * 
 */
public class BaseRobot extends TimedRobot {
	/** The robot container */
	public final RobotContainer container;

	/** The current autonomous command in use */
	public Command autoCommand;

	public BaseRobot(RobotContainer container) {
		// make sure the config is loaded.
		final int garbage = Conf.hardwareArraySize;

		this.container = container;

		if (isSimulation()) Log.dev("started in simulation");
		else Log.dev("welcome to the real world");
	}


	/** This method gets called with every period, no matter what platform.
	 * Generally, this method boils down to {@code
	 *		Scheduler.getDefault().run();
	 * }
	 */
	@Override public void robotPeriodic() {
		Scheduler.getDefault().run();

		container.recordPeriodic();
	}



	/** This method gets run when the robot is disabled. */
	@Override public void disabledInit() {
		Log.dev("disabling");

		container.stopRecording();
	}

	/** This method gets run when autonomous is enabled. */
	@Override public void autonomousInit() {
		Log.dev("entering auto");

		autoCommand = container.auto();

		if (autoCommand != null) {
			Scheduler.getDefault().schedule(autoCommand);
		}
	}

	/** This method gets run when teleop is enabled. */
	@Override public void teleopInit() {
		Log.dev("entering teleop");

		// TODO: cancel
		//if (autoCommand != null) {
		//	autoCommand.cancel();
		//}

		container.startRecording();
	}

	/** This method gets run when utility is enabled.
	 * Utility used to be named 'test'. This was switched to utility in 2027 to
	 * emphasise that the mode can be used for more than testing. This can be
	 * anything from automated routines, to sensor calibration, to whatever.
	 *
	 * Possibly, this could be used to mark when a robot enters an automated
	 * behavior mid-match. Such as automating trying to score? Whatever it is,
	 * this is up to the user.
	 */
	@Override public void utilityInit() {
		Log.dev("entering utility");
	}

	/** This method gets run when the robot is started in a simulation. */
	@Override public void simulationInit() {
		Log.dev("welcome to the simulation");
	}


	@Override public void disabledPeriodic() {}
	@Override public void autonomousPeriodic() {}
	@Override public void teleopPeriodic() {}
	@Override public void utilityPeriodic() {}
	@Override public void simulationPeriodic() {}

	@Override public void disabledExit() {
		Log.dev("enabling");
	}
	
	@Override public void autonomousExit() {}
	@Override public void teleopExit() {}
	@Override public void utilityExit() {}
}
