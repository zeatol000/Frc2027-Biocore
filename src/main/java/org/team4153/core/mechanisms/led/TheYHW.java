package org.team4153.core.mechanisms.led;

import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.led.*;

import static org.team4153.core.config.Conf.config;

// the giant led Y that is on almost every bot
public class TheYHW implements Mechanism {
  /*
   * LED Strip Lengths
   * Climber: 28in/103 LED's
   * Shooter: 11in/40 LED's
   *
   * Running LED sets Worm Color
   * Chasing LED sets background
   *
   * Climber LED Indicators
   * Extended: Background Red
   * Locked: Backgound Green
   * Wich Active
   */

  public final boolean active; // should this
  public final AddressableLED led;
  public final AddressableLEDBuffer ledBuffer;

  public TheYHW() {
    active = config.getBoolean("led.y.active");

    led = new AddressableLED(
      config.getInt("led.y.channel")
    );

    ledBuffer = new AddressableLEDBuffer(
      config.getInt("led.y.length")
    );

    led.setLength(ledBuffer.getLength());
    led.setData(ledBuffer);
  }
}
