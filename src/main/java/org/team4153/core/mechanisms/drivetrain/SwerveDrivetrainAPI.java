package org.team4153.core.mechanisms.drivetrain;

import java.util.function.DoubleSupplier;

import org.wpilib.math.controller.PIDController;
import org.wpilib.math.filter.SlewRateLimiter;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Translation2d;
import org.wpilib.math.kinematics.*;

import static org.team4153.core.config.Conf.config;

public class SwerveDrivetrainAPI extends DrivetrainAPI<
  SwerveModule,
  SwerveModuleAcceleration,
  SwerveModuleVelocity,
  SwerveModulePosition,
  SwerveDriveKinematics,
  SwerveDriveOdometry,
  SwerveDriveOdometry3d,
  SwerveDrivetrainHW
  > {

  public final boolean boostEnabled;

  public SwerveDrivetrainAPI(
    SwerveDriveOdometry odometry,
    SwerveDriveKinematics kinematics,
    DoubleSupplier xsup,
    DoubleSupplier ysup,
    DoubleSupplier osup,
    DoubleSupplier triggersup
  ) {
    super(
      new SwerveDrivetrainHW(),
      odometry,
      kinematics,
      new PIDController(
        config.hasPath("drivetrain.pid.kp")
          ? config.getDouble("drivetrain.pid.kp")
          : 1.8,
        config.hasPath("drivetrain.pid.ki")
          ? config.getDouble("drivetrain.pid.ki")
          : 0,
        config.hasPath("drivetrain.pid.kd")
          ? config.getDouble("drivetrain.pid.kd")
          : 0
      ),
      xsup,
      ysup,
      osup,
      triggersup,
      config.hasPath("drivetrain.maxAcceleration")
        ? config.getDouble("drivetrain.maxAcceleration")
        : 5,
      config.hasPath("drivetrain.maxAngularAcceleration")
        ? config.getDouble("drivetrain.maxAngularAcceleration")
        : 10
    );

    boostEnabled = config.hasPath("drivetrain.boost")
      && config.getBoolean("drivetrain.boost");
  }

  public void drive() {
    double xSpeed = xsup.getAsDouble();
    double ySpeed = ysup.getAsDouble();
    double oSpeed = osup.getAsDouble();
    double boost = triggersup.getAsDouble();

    if (boostEnabled) {
      xSpeed /= 2;
      xSpeed += xSpeed * boost;
      ySpeed /= 2;
      ySpeed += ySpeed * boost;
      oSpeed /= 2;
      oSpeed += ySpeed * boost;
    }

    xSpeed = xlim.calculate(xSpeed * maxSpeed);
    ySpeed = ylim.calculate(ySpeed * maxSpeed);
    oSpeed = turnlim.calculate(oSpeed * maxAngularSpeed);

    ChassisVelocities cv = new ChassisVelocities(xSpeed, ySpeed, oSpeed);

    drivetrain.chassisVelocitiesPublisher.set(cv);

    SwerveModuleVelocity vels[] = kinematics.toSwerveModuleVelocities(cv);
    drivetrain.setDesiredVelocities(vels);
  }
}
