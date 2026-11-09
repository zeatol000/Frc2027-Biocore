package org.team4153.biocore;

import org.wpilib.framework.RobotBase;

import org.team4153.core.*;
import org.team4153.core.config.Conf;

public class Main {
	public static void main(String[] args) {
		RobotBase.startRobot(() ->
			new BaseRobot(new BaseRobotContainer())
		);
	}
}
