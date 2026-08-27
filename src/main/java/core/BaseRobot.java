package org.team4153.core;

import edu.wpi.first.wpilibj.TimedRobot;

import org.team4153.core.BaseRobotContainer;
import org.team4153.core.config.Conf;


/** The main class that handles hardware connection.
 * 
 */
public class BaseRobot extends TimedRobot {
	public final BaseRobotContainer container;

	public BaseRobot(BaseRobotContainer container) {
		this.container = container;
		IO.println("hi");
		IO.println(Conf.config);
	}
}
