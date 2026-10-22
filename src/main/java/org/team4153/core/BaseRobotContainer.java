package org.team4153.core;

import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Command;

import org.team4153.core.util.CMD;
import org.team4153.core.mechanisms.drivetrain.SwerveDrivetrainAPI;

/** Default implementation of a robot container.
 * A robot container is what manages high level APIs that make it so your life
 * isn't painful. The general idea is: the Robot handles low level API and
 * calls commands on the container.
 *
 * Why we do this instead of having one huge class is simple: so you don't have
 * one 5 thousand line file that is a nightmare to debug.
 */
public abstract class BaseRobotContainer implements RobotContainer {
  //public static final String RECORDING_FILE = "recording.csv";

  //public final CommandXboxController controller;
  //public final LiveDriverInputSource liveInputs;
  //public final SwitchableDriverInputSource inputs;
  //public final InputRecorder recorder;

  //public final Vision vision
  //public final SwerveDrivetrainAPI swerves;
  //public final Odometry odometry
}
