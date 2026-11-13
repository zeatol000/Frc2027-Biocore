package org.team4153.core;

import org.wpilib.command3.Command;
import org.wpilib.math.kinematics.SwerveDriveOdometry;
//import org.wpilib.hardware.imu.OnboardIMU; // gyro


import org.team4153.core.config.Conf;
import org.team4153.core.config.fromconfig.SwerveUtils;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.mechanisms.drivetrain.SwerveDrivetrainAPI;
import org.team4153.core.mechanisms.input.InputProxy;
import org.team4153.core.hardware.impl.input.XboxInputSource;

/** Default implementation of a robot container.
 * A robot container is what manages high-level APIs that make it so your life
 * isn't painful. The general idea is: the Robot handles low-level API and
 * calls commands on the container.
 *
 * Why we do this instead of having one huge class is straightforward: so you don't
 * have a 5-thousand-line file that is a nightmare to debug.
 */
public class BaseRobotContainer implements RobotContainer {
	//public static final String RECORDING_FILE = "recording.csv";

	public final XboxInputSource controller;
	//public final LiveDriverInputSource liveInputs;
	public final InputProxy<XboxInputSource> inputs;

	//public final InputRecorder recorder;

	//public final Vision vision
	public final SwerveDrivetrainAPI swerves;
	public final SwerveDriveOdometry odometry;


	public BaseRobotContainer() {
		int controllerId = Conf.config.getInt("drivetrain.inputId");
		controller = (XboxInputSource) Hardware.hardware[controllerId];

		inputs = new InputProxy<>(controller);

		odometry = SwerveUtils.odometryFrom(
			Conf.config.getConfig("drivetrain.odometry")
		);

		var kinematics = SwerveUtils.kinematicsFrom(
			Conf.config.getConfigList("drivetrain.odometry.kinematics")
		);
		
		swerves = new SwerveDrivetrainAPI(
			odometry,
			kinematics,
			() -> -inputs.leftX(),
			() -> -inputs.leftY(),
			inputs::rightX,
			inputs::rightTrigger
		);

		swerves.drivetrain.setDefaultCommand(swerves.driveCommand());

		configureBindings();
	}


	public void configureBindings() {
	}

	public Command auto() {
		return null;
	}
}
