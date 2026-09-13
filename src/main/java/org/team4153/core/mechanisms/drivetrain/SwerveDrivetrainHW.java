package org.team4153.core.mechanisms.drivetrain;

import org.wpilib.math.kinematics.ChassisVelocities;
import org.wpilib.math.kinematics.SwerveDriveKinematics;
import org.wpilib.math.kinematics.SwerveModuleAcceleration;
import org.wpilib.math.kinematics.SwerveModulePosition;
import org.wpilib.math.kinematics.SwerveModuleVelocity;
import org.wpilib.math.kinematics.SwerveDriveKinematics;
import org.wpilib.math.kinematics.SwerveDriveOdometry;
import org.wpilib.math.kinematics.SwerveDriveOdometry3d;
import org.wpilib.networktables.NetworkTableInstance;
import org.wpilib.networktables.StructArrayPublisher;
import org.wpilib.networktables.StructArrayTopic;
import org.wpilib.networktables.StructPublisher;
import org.wpilib.networktables.StructTopic;

import org.team4153.core.config.Conf;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.hardware.Motor;
import org.team4153.core.mechanisms.drivetrain.DrivetrainHW;
import org.team4153.core.mechanisms.drivetrain.SwerveModule;

/** Swerve drivetrain hardware api. */
public class SwerveDrivetrainHW extends DrivetrainHW<
	SwerveModule,
	SwerveModuleAcceleration,
	SwerveModuleVelocity,
	SwerveModulePosition,
	SwerveDriveKinematics,
	SwerveDriveOdometry,
	SwerveDriveOdometry3d
> {
	// The swerve modules
	/** Front left swerve */
	public final SwerveModule fl;
	/** Front right swerve */
	public final SwerveModule fr;
	/** Back left swerve */
	public final SwerveModule bl;
	/** Back right swerve */
	public final SwerveModule br;


	// construct by config
	public SwerveDrivetrainHW() {
		var c = Conf.config;
		var h = Hardware.hardware;

		double max = c.hasPath("drivetrain.maxSpeedMPS")
			? c.getDouble("drivetrain.maxSpeedMPS")
			: 7.5;

		
		var fl = new SwerveModule(
			"FrontLeft",
			(Motor) h[c.getInt("drivetrain.front-left.power")],
			(Motor) h[c.getInt("drivetrain.front-left.steer")],
			c.hasPath("drivetrain.front-left.maxSpeedMPS")
				? c.getDouble("drivetrain.front-left.maxSpeedMPS")
				: max
		);

		var fr = new SwerveModule(
			"FrontRight",
			(Motor) h[c.getInt("drivetrain.front-right.power")],
			(Motor) h[c.getInt("drivetrain.front-right.steer")],
			c.hasPath("drivetrain.front-right.maxSpeedMPS")
				? c.getDouble("drivetrain.front-right.maxSpeedMPS")
				: max
		);

		var bl = new SwerveModule(
			"BackLeft",
			(Motor) h[c.getInt("drivetrain.back-left.power")],
			(Motor) h[c.getInt("drivetrain.back-left.steer")],
			c.hasPath("drivetrain.back-left.maxSpeedMPS")
				? c.getDouble("drivetrain.back-left.maxSpeedMPS")
				: max
		);

		var br = new SwerveModule(
			"BackRight",
			(Motor) h[c.getInt("drivetrain.back-right.power")],
			(Motor) h[c.getInt("drivetrain.back-right.steer")],
			c.hasPath("drivetrain.back-right.maxSpeedMPS")
				? c.getDouble("drivetrain.back-right.maxSpeedMPS")
				: max
		);

		this(max, fl, fr, bl, br);

	}

	// construct by parameters
	public SwerveDrivetrainHW(
		double maxSpeedMPS,
		SwerveModule fl,
		SwerveModule fr,
		SwerveModule bl,
		SwerveModule br
	) {
		super(
			NetworkTableInstance
				.getDefault()
				.getStructArrayTopic(
					"/SmartDashboard/SwerveVelocity",
					SwerveModuleVelocity.struct
				),

			maxSpeedMPS
		);

		this.fl = fl;
		this.fr = fr;
		this.bl = bl;
		this.br = br;
	}

	public void setDesiredVelocities(SwerveModuleVelocity[] vel) {
		vel = SwerveDriveKinematics.desaturateWheelVelocities(vel, maxSpeedMPS);
		fl.setDesiredVelocity(vel[0]);
		fr.setDesiredVelocity(vel[1]);
		bl.setDesiredVelocity(vel[2]);
		br.setDesiredVelocity(vel[3]);
	}

	public SwerveModulePosition[] positions() {
		return new SwerveModulePosition[] {
			fl.pos(),
			fr.pos(),
			bl.pos(),
			br.pos()
		};
	}

	public SwerveModuleVelocity[] velocities() {
		return new SwerveModuleVelocity[] {
			fl.vel(),
			fr.vel(),
			bl.vel(),
			br.vel()
		};
	}
}
