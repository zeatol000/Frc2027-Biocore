package org.team4153.core.mechanisms.drivetrain;

import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.kinematics.SwerveModuleAcceleration;
import org.wpilib.math.kinematics.SwerveModulePosition;
import org.wpilib.math.kinematics.SwerveModuleVelocity;
import org.wpilib.math.kinematics.SwerveDriveKinematics;
import org.wpilib.math.kinematics.SwerveDriveOdometry;
import org.wpilib.math.kinematics.SwerveDriveOdometry3d;
//import org.wpilib.smartdashboard.SmartDashboard;

import org.team4153.core.hardware.Motor;

public class SwerveModule {
	protected final Motor power;
	protected final Motor rotation;
	public final String desc;
	public final double maxSpeedMPS;

	public SwerveModule(String desc, Motor power, Motor rotation, double maxSpeedMPS) {
		this.desc = desc;
		this.power = power;
		this.rotation = rotation;
		this.maxSpeedMPS = maxSpeedMPS;
	}

	public void setDesiredVelocity(SwerveModuleVelocity vel) {
		Rotation2d current = rotation.realAngle();
		//String s = "Drivetrain/" + desc;

		// degrees for human viewing.
		//SmartDashboard.putNumber(s + "/currentAngle", current.getDegrees());
		//SmartDashboard.putNumber(s + "/goalAngle", desired.angle.getDegrees());
		//SmartDashboard.putNumber(s + "/preOptimizedVelocity", desired.speedMetersPerSecond);

		vel = vel
			.optimize(current)		// reduce distance to travel
			.cosineScale(current);	// smooth driving

		//SmartDashboard.putNumber(s + "/finalVelocity", desired.speedMetersPerSecond);

		float angle = (float) vel.angle.getRadians();
		double speed = vel.velocity / maxSpeedMPS;
		rotation.rotate(angle);
		power.run(speed);
	}

	public SwerveModulePosition pos() {
		double distance = power.distance();
		Rotation2d angle = rotation.realAngle();
		
		return new SwerveModulePosition(distance, angle);
	}

	public SwerveModuleVelocity vel() {
		double vel = power.speed();
		Rotation2d angle = rotation.realAngle();

		return new SwerveModuleVelocity(vel, angle);
	}

	public double distance() {
		return power.distance();
	}

	/*public void log() {
		power.log(desc);
		rotation.log(desc);
	}*/
}
