package org.team4153.core.systems.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructArrayPublisher;
import edu.wpi.first.networktables.StructArrayTopic;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.networktables.StructTopic;

import org.team4153.core.config.Conf;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.hardware.Motor;
import org.team4153.core.systems.drivetrain.DrivetrainHW;
import org.team4153.core.systems.drivetrain.SwerveModule;

/** Swerve drivetrain hardware api. */
public class SwerveDrivetrainHW
extends DrivetrainHW<SwerveModuleState, SwerveModulePosition, SwerveModule>
{
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
		super(
			NetworkTableInstance
				.getDefault()
				.getStructArrayTopic(
					"/SmartDashboard/SwerveVelocity",
					SwerveModuleState.struct
				),
			Conf.config.hasPath("drivetrain.maxSpeedMPS")
				? Conf.config.getDouble("drivetrain.maxSpeedMPS")
				: 7.5
		);

		var c = Conf.config;
		var h = Hardware.hardware;
		
		fl = new SwerveModule(
			"FrontLeft",
			(Motor) h[c.getInt("drivetrain.front-left.power")],
			(Motor) h[c.getInt("drivetrain.front-left.steer")],
			c.hasPath("drivetrain.front-left.maxSpeedMPS")
				? c.getDouble("drivetrain.front-left.maxSpeedMPS")
				: maxSpeedMPS
		);

		fr = new SwerveModule(
			"FrontRight",
			(Motor) h[c.getInt("drivetrain.front-right.power")],
			(Motor) h[c.getInt("drivetrain.front-right.steer")],
			c.hasPath("drivetrain.front-right.maxSpeedMPS")
				? c.getDouble("drivetrain.front-right.maxSpeedMPS")
				: maxSpeedMPS
		);

		bl = new SwerveModule(
			"BackLeft",
			(Motor) h[c.getInt("drivetrain.back-left.power")],
			(Motor) h[c.getInt("drivetrain.back-left.steer")],
			c.hasPath("drivetrain.back-left.maxSpeedMPS")
				? c.getDouble("drivetrain.back-left.maxSpeedMPS")
				: maxSpeedMPS
		);

		br = new SwerveModule(
			"BackRight",
			(Motor) h[c.getInt("drivetrain.back-right.power")],
			(Motor) h[c.getInt("drivetrain.back-right.steer")],
			c.hasPath("drivetrain.back-right.maxSpeedMPS")
				? c.getDouble("drivetrain.back-right.maxSpeedMPS")
				: maxSpeedMPS
		);

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
					SwerveModuleState.struct
				),
			maxSpeedMPS
		);

		this.fl = fl;
		this.fr = fr;
		this.bl = bl;
		this.br = br;
	}

	public void setDesiredStates(SwerveModuleState[] states) {
		SwerveDriveKinematics.desaturateWheelSpeeds(states, maxSpeedMPS);
		fl.setDesiredState(states[0]);
		fr.setDesiredState(states[1]);
		bl.setDesiredState(states[2]);
		br.setDesiredState(states[3]);
	}

	public SwerveModulePosition[] positions() {
		return new SwerveModulePosition[] {
			fl.pos(),
			fr.pos(),
			bl.pos(),
			br.pos()
		};
	}

	public SwerveModuleState[] states() {
		return new SwerveModuleState[] {
			fl.state(),
			fr.state(),
			bl.state(),
			br.state()
		};
	}
}
