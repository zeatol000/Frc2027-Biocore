package org.team4153.core.mechanisms.drivetrain;

import java.util.Set;
import java.util.function.DoubleSupplier;

import org.wpilib.command3.Command;
import org.wpilib.math.controller.PIDController;
import org.wpilib.math.filter.SlewRateLimiter;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Translation2d;
import org.wpilib.math.kinematics.Kinematics;
import org.wpilib.math.kinematics.Odometry;
import org.wpilib.math.kinematics.Odometry3d;

import org.team4153.core.util.CMD;

/** The class that connects inputs from the user (i.e., the controller) and
 * routes it to the DrivetrainHW. */
public abstract class DrivetrainAPI<
	D,
	A,
	V,
	P,
	K  extends Kinematics<?, ?, ?>,
	O  extends Odometry<?>,
	O3 extends Odometry3d<?>,
	HW extends DrivetrainHW<D, A, V, P, K, O, O3>
> {
	public final HW drivetrain;

	/** Suppliers of joystick inputs */
	public final DoubleSupplier  xsup, ysup, osup, triggersup;

	/** Limiters of joystick inputs */
	public final SlewRateLimiter xlim, ylim, turnlim;

	/** Odometry */
	public final O odometry;

	/** Kinematics */
	public final K kinematics;

	/** The PID controller */
	public final PIDController pid;

	public final double maxSpeed;
	public final double maxAngularSpeed;
	public final double maxAcceleration;
	public final double maxAngularAcceleration;

	public DrivetrainAPI(
		HW drivetrain,
		O odometry,
		K kinematics,
		PIDController pid,
		DoubleSupplier xsup,
		DoubleSupplier ysup,
		DoubleSupplier osup,
		DoubleSupplier triggersup,
		double maxAcceleration,
		double maxAngularAcceleration
	) {
		this.drivetrain = drivetrain;
		this.odometry = odometry;
		this.kinematics = kinematics;
		this.xsup = xsup;
		this.ysup = ysup;
		this.osup = osup;
		this.triggersup = triggersup;
		this.maxSpeed = drivetrain.maxSpeed;
		this.maxAngularSpeed = drivetrain.maxAngularSpeed;
		this.maxAcceleration = maxAcceleration;
		this.maxAngularAcceleration = maxAngularAcceleration;
		this.xlim = new SlewRateLimiter(maxAcceleration);
		this.ylim = new SlewRateLimiter(maxAcceleration);
		this.turnlim = new SlewRateLimiter(maxAngularAcceleration);
		this.pid = pid;
		pid.enableContinuousInput(-Math.PI, Math.PI);
	}

	public Command driveCommand() {
		return CMD.of(
			this::drive,
			"DrivetrainDriveCommand",
			Set.of(drivetrain)
		);
	}

	public abstract void drive();
}
