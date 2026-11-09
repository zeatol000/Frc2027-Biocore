package org.team4153.core.config.fromconfig;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueType;

import org.wpilib.math.geometry.*;
import org.wpilib.math.linalg.*;
import org.wpilib.math.numbers.*;
import org.wpilib.units.measure.*;

public class Geometry {



  public static Pose2d pose2dFrom(Config cfg) {
    // (double x, double y, Rotation2d rotation)
    // (Translation2d translation, Rotation2d rotation)
    // (Matrix<N3, N3> matrix)
    // (Distance x, Distance y, Rotation2d rotation)

    if (cfg.hasPath("x")) {
      if (cfg.getValue("x").valueType() == ConfigValueType.NUMBER) {
        double x = cfg.getDouble("x");
        double y = cfg.getDouble("y");
        Rotation2d rotation = rotation2dFrom(cfg.getConfig("rotation"));

        return new Pose2d(x, y, rotation);
      }
      else { //if (cfg.getValue("x").valueType() == ConfigValueType.OBJECT)
        Distance x = Units.distanceFrom(cfg.getConfig("x"));
        Distance y = Units.distanceFrom(cfg.getConfig("y"));
        Rotation2d rotation = rotation2dFrom(cfg.getConfig("rotation"));

        return new Pose2d(x, y, rotation);
      }
    }
    else if (cfg.hasPath("translation")) {
      Translation2d translation = translation2dFrom(cfg.getConfig("translation"));
      Rotation2d rotation = rotation2dFrom(cfg.getConfig("rotation"));

      return new Pose2d(translation, rotation);
    }
    else if (cfg.hasPath("matrix")) {
      Matrix<N3, N3> matrix = null;

      return new Pose2d(matrix);
    }
    else return new Pose2d();
  }

  public static Rotation2d rotation2dFrom(Config cfg) {
    // (double value)
    // (double x, double y)
    // (Matrix<N2, N2> rotationMatrix)
    // (Angle angle)

    if (cfg.hasPath("value")) {
      return new Rotation2d(cfg.getDouble("value"));
    }
    else if (cfg.hasPath("x")) {
      double x = cfg.getDouble("x");
      double y = cfg.getDouble("y");

      return new Rotation2d(x, y);
    }
    else if (cfg.hasPath("angle")) {
      Angle angle = Units.angleFrom(cfg.getConfig("angle"));

      return new Rotation2d(angle);
    }
    else if (cfg.hasPath("matrix")) {
      Matrix<N2, N2> matrix = null;

      return new Rotation2d(matrix);
    }
    else return new Rotation2d();
  }

  public static Translation2d translation2dFrom(Config cfg) {
    // (double x, double y)
    // (double distance, Rotation2d angle)
    // (Vector<N2> vector)
    // (Distance x, Distance y)

    if (cfg.hasPath("x")) {
      if (cfg.getValue("x").valueType() == ConfigValueType.NUMBER) {
        double x = cfg.getDouble("x");
        double y = cfg.getDouble("y");

        return new Translation2d(x, y);
      }
      else {
        Distance x = Units.distanceFrom(cfg.getConfig("x"));
        Distance y = Units.distanceFrom(cfg.getConfig("y"));

        return new Translation2d(x, y);
      }
    }
    else if (cfg.hasPath("distance")) {
      double distance = cfg.getDouble("distance");
      Rotation2d angle = rotation2dFrom(cfg.getConfig("angle"));

      return new Translation2d(distance, angle);
    }
    else if (cfg.hasPath("vector")) {
      Vector<N2> vector = null;

      return new Translation2d(vector);
    }
    else return new Translation2d();
  }
}
