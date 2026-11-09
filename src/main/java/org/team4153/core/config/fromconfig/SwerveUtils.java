package org.team4153.core.config.fromconfig;

import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueType;

import org.wpilib.math.geometry.*;
import org.wpilib.math.kinematics.*;
import org.wpilib.units.measure.Distance;

public class SwerveUtils {
  public static SwerveDriveOdometry odometryFrom(Config cfg) {
    SwerveDriveKinematics kinematics =
      kinematicsFrom(cfg.getConfigList("kinematics"));

    Rotation2d gyroAngle = Geometry.rotation2dFrom(cfg.getConfig("gyroAngle"));

    Pose2d initialPose = null;


    List<? extends Config> modulePositionList = cfg.getConfigList("modulePositions");
    SwerveModulePosition[] modulePositions = new SwerveModulePosition[modulePositionList.size()];

    for (int i = 0; i < modulePositions.length; i++) {
      modulePositions[i] = positionFrom(
        modulePositionList.get(i)
      );
    }

    if (cfg.hasPath("initialPose")) {
      initialPose = Geometry.pose2dFrom(cfg.getConfig("initialPose"));
    }

    return (initialPose == null)
      ? new SwerveDriveOdometry(kinematics, gyroAngle, modulePositions)
      : new SwerveDriveOdometry(kinematics, gyroAngle, modulePositions, initialPose);
  }


  public static SwerveDriveKinematics kinematicsFrom(
    List<? extends Config> cs
  ) {
    final int len = cs.size();
    Translation2d[] arr = new Translation2d[len];

    for (int i = 0; i < len; i++) {
      arr[i] = Geometry.translation2dFrom(
        cs.get(i)
      );
    }

    return new SwerveDriveKinematics(arr);
  }

  public static SwerveModulePosition positionFrom(Config cfg) {
    if (!cfg.hasPath("angle"))
      return new SwerveModulePosition();

    Rotation2d angle = Geometry.rotation2dFrom(cfg.getConfig("angle"));

    if (cfg.getValue("distance").valueType() == ConfigValueType.NUMBER) {
      double distance = cfg.getDouble("distance");

      return new SwerveModulePosition(distance, angle);
    }
    else {
      Distance distance = Units.distanceFrom(cfg.getConfig("distance"));

      return new SwerveModulePosition(distance, angle);
    }
  }
}
