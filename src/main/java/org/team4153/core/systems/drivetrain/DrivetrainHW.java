package org.team4153.core.systems.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
//import edu.wpi.first.math.kinematics.;
//import edu.wpi.first.math.kinematics.;
//import edu.wpi.first.math.kinematics.;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructArrayPublisher;
import edu.wpi.first.networktables.StructArrayTopic;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.networktables.StructTopic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Drivetrain hardware api.
 *
 * TYPE PARAMETERS:
 * State 		data that determines the state of a module
 * Position		data that determines the position of a module
 * Drive			a module
 */
public abstract class DrivetrainHW<State, Position, Drive> extends SubsystemBase {

	/** Maximum speed */
	public final double maxSpeedMPS;

	protected final StructTopic<ChassisSpeeds> 		chassisSpeedsTopic = NetworkTableInstance
		.getDefault()
		.getStructTopic(
			"/SmartDashboard/ChassisVelocityActual",
			ChassisSpeeds.struct
		);

	protected final StructPublisher<ChassisSpeeds>	chassisSpeedsPublisher = chassisSpeedsTopic.publish();


	protected final StructArrayTopic<State>		stateTopic;
	protected final StructArrayPublisher<State>	statePublisher;


	public DrivetrainHW(
		StructArrayTopic<State> stateTopic,
		double maxSpeedMPS
	) {
		this.maxSpeedMPS = maxSpeedMPS;
		this.stateTopic = stateTopic;
		this.statePublisher = stateTopic.publish();
	}


	/** Set the desired states of all drive modules */
	public abstract void setDesiredStates(State[] states);
	
	/** Return the current positions of all drive modules */
	public abstract Position[] positions();

	/** Return the current states of all drive modules */
	public abstract State[] states();

}
