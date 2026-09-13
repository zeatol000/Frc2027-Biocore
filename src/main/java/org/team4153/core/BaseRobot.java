package org.team4153.core;

import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.framework.TimedRobot;

import org.team4153.core.BaseRobotContainer;
import org.team4153.core.config.Conf;
import org.team4153.core.config.Log;


/** The main class that handles hardware connection.
 * 
 */
public class BaseRobot extends TimedRobot {
	/** The robot container */
	public final BaseRobotContainer container;

	public BaseRobot(BaseRobotContainer container) {
		Conf.forceLoad();
		this.container = container;

		if (isSimulation()) Log.dev("started in simulation");
	}



	// THE periodic method
	@Override public void robotPeriodic() {
		Scheduler.getDefault().run();
	}



	@Override public void disabledInit() {
		Log.dev("disabling");
	}

	@Override public void autonomousInit() {
		Log.dev("entering auto");
	}

	@Override public void teleopInit() {
		Log.dev("entering teleop");
	}

	@Override public void utilityInit() {
		Log.dev("entering utility");
	}

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
