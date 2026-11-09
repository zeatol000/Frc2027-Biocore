package org.team4153.core.config.fromconfig;

import com.typesafe.config.Config;

import org.wpilib.units.BaseUnits;
import org.wpilib.units.measure.Angle;
import org.wpilib.units.measure.Distance;

public class Units {

  public static Angle angleFrom(Config cfg) {
    double magnitude = cfg.getDouble("magnitude");
    double baseUnitMagnitude = cfg.getDouble("baseUnitMagnitude");

    return new Angle(
      magnitude,
      baseUnitMagnitude,
      BaseUnits.AngleUnit
    );
  }

  public static Distance distanceFrom(Config cfg) {
    double magnitude = cfg.getDouble("magnitude");
    double baseUnitMagnitude = cfg.getDouble("baseUnitMagnitude");

    return new Distance(
      magnitude,
      baseUnitMagnitude,
      BaseUnits.DistanceUnit
    );
  }
}
