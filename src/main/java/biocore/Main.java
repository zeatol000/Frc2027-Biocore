package org.team4153.biocore;

import edu.wpi.first.wpilibj.RobotBase;

import org.team4153.core.BaseRobot;

public class Main {
	public void main(String[] args) {
		RobotBase.startRobot(() -> new BaseRobot(null));
	}
}
