package org.team4153.core.systems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	public void setDesiredState(SwerveModuleState desired) {
		Rotation2d current = rotation.realAngle();
		String s = "Swerve/" + desc;

		// degrees for human viewing.
		SmartDashboard.putNumber(s + "/currentAngle", current.getDegrees());
		SmartDashboard.putNumber(s + "/goalAngle", desired.angle.getDegrees());
		SmartDashboard.putNumber(s + "/preOptimizedVelocity", desired.speedMetersPerSecond);

		desired.optimize(current);
		desired.cosineScale(current);

		SmartDashboard.putNumber(s + "/finalVelocity", desired.speedMetersPerSecond);

		float angle = (float) desired.angle.getRadians();
		double speed = desired.speedMetersPerSecond / maxSpeedMPS;
		rotation.rotate(angle);
		power.run(speed);
	}

	public SwerveModulePosition pos() {
		double distance = power.distance();
		Rotation2d angle = rotation.realAngle();
		
		return new SwerveModulePosition(distance, angle);
	}

	public SwerveModuleState state() {
		double vel = power.speed();
		Rotation2d angle = rotation.realAngle();

		return new SwerveModuleState(vel, angle);
	}

	public double distance() {
		return power.distance();
	}

	/*public void log() {
		power.log(desc);
		rotation.log(desc);
	}*/
}
