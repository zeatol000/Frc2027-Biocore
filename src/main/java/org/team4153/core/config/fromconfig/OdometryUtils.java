package org.team4153.core.config.fromconfig;

import com.typesafe.config.Config;

import org.wpilib.math.geometry.*;
import org.wpilib.math.kinematics.*;

public class OdometryUtils {
  //if (cfg.getValue("initialPose").valueType() != ConfigValueType.STRING)


  public static Odometry<?> odometryFrom(Config cfg) {
    return switch (cfg.getString("class").toLowerCase()) {
      case "twodeadwheel" -> null;
      case "swerve" -> SwerveUtils.odometryFrom(cfg);
      case "mecanum" -> null;
      case "differential" -> null;
      default -> null;
    };
  }


  public static <T> Odometry3d<T> odometry3dFrom(Config cfg) {
    return null;
  }
}
