package org.team4153.core.mechanisms.drivetrain;

import org.wpilib.command3.Mechanism;
import org.wpilib.math.kinematics.*;
import org.wpilib.networktables.NetworkTableInstance;
import org.wpilib.networktables.StructArrayPublisher;
import org.wpilib.networktables.StructArrayTopic;
import org.wpilib.networktables.StructPublisher;
import org.wpilib.networktables.StructTopic;

/** Drivetrain hardware api.
 *
 * TYPE PARAMETERS:
 * D		a module of the drivetrain, such as a swerve
 * A		data that determines the acceleration of a module
 * V 		data that determines the velocity of a module
 * P		data that determines the position of a module
 * K		the kinematics class
 * O		the odometry class
 * O3		the odometry 3d class
 */
public abstract class DrivetrainHW<
	D,
	A,
	V,
	P,
	K  extends Kinematics<?, ?, ?>,
	O  extends Odometry<?>,
	O3 extends Odometry3d<?>
> implements Mechanism {

	/** Maximum speed */
	public final double maxSpeedMPS;

	//protected final StructTopic<ChassisAccelerations> chassisAccelerationsTopic =
	//	NetworkTableInstance
	//	.getDefault()
	//	.getStructTopic(
	//		"/SmartDashboard/ChassisAccelerationActual",
	//		ChassisAccelerations.struct
	//	);

	protected final StructTopic<ChassisVelocities> chassisVelocitiesTopic =
		NetworkTableInstance
		.getDefault()
		.getStructTopic(
			"/SmartDashboard/ChassisVelocityActual",
			ChassisVelocities.struct
		);

	//protected final StructPublisher<ChassisAccelerations> chassisAccelerationsPublisher =
	//	chassisAccelerationsTopic.publish();

	protected final StructPublisher<ChassisVelocities>	chassisVelocitiesPublisher =
		chassisVelocitiesTopic.publish();


	protected final StructArrayTopic<V>			velocityTopic;
	protected final StructArrayPublisher<V>	velocityPublisher;
	//protected final StructArrayTopic<A>			accelerationTopic;
	//protected final StructArrayPublisher<A>	accelerationPublisher;


	public DrivetrainHW(
		StructArrayTopic<V> velocityTopic,
		//StructArrayTopic<A> accelerationTopic,
		double maxSpeedMPS
	) {
		this.maxSpeedMPS = maxSpeedMPS;
		this.velocityTopic = velocityTopic;
		this.velocityPublisher = velocityTopic.publish();
		//this.accelerationTopic = accelerationTopic;
		//this.accelerationPublisher = accelerationTopic.publish();
	}


	/** Set the desired velocities of all drive modules */
	public abstract void setDesiredVelocities(V[] velocities);
	
	/** Return the current positions of all drive modules */
	public abstract P[] positions();

	/** Return the current velocities of all drive modules */
	public abstract V[] velocities();

	/* Return the current accelerations of all drive modules *
	public abstract A[] accelerations();*/
}
