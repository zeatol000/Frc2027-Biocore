package org.team4153.core;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.team4153.core.BaseRobotContainer;
import org.team4153.core.config.Conf;


/** The main class that handles hardware connection.
 * 
 */
public class BaseRobot extends TimedRobot {
	public final BaseRobotContainer container;

	public BaseRobot(BaseRobotContainer container) {
		Conf.forceLoad();
		this.container = container;
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		CommandScheduler
			.getInstance()
			.schedule(
				Commands.print("hi")
			);
	}
}
