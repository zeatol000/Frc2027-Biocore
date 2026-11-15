package org.team4153.core.auto.recording;

import java.io.IOException;

import org.team4153.core.config.Log;
import org.team4153.core.util.SDLController;

/** A record of all xbox inputs, as well as a timestamp of time offset in creation since
 * the recording started, in milliseconds.
 * @param timestamp     time since recording started, milliseconds
 * @param leftX         how much the left joystick was moved horizontally. Negative is left.
 * @param leftY         how much the left joystick was moved vertically. Negative is down.
 * @param leftTrigger   how much the left trigger was pressed
 * @param rightX        how much the right joystick was moved horizontally. Negative is left.
 * @param rightY        how much the right joystick was moved vertically. Negative is down.
 * @param rightTrigger  how much the right trigger was pressed
 * @param leftBumper    if the left bumper was pressed
 * @param rightBumper   if the right bumper was pressed
 * @param dPadUp        if the dpad_up button was pressed
 * @param dPadLeft      if the dpad_left button was pressed
 * @param dPadDown      if the dpad_down button was pressed
 * @param dPadRight     if the dpad_right button was pressed
 * @param y             if the y button was pressed
 * @param x             if the x button was pressed
 * @param a             if the a button was pressed
 * @param b             if the b button was pressed
 * @param view          if the view button was pressed. Left of the XBox button
 * @param menu          if the menu button was pressed. Right of the XBox button
 * @param xbox          if the XBox button was pressed.
 */
public record XBoxRecordingElement(
  long timestamp,
  float leftX,
  float leftY,

  float rightX,
  float rightY,

  float leftTrigger,
  float rightTrigger,

  boolean leftBumper,
  boolean rightBumper,

  boolean leftStick,
  boolean rightStick,

  boolean dPadUp,
  boolean dPadLeft,
  boolean dPadDown,
  boolean dPadRight,

  boolean y,
  boolean x,
  boolean a,
  boolean b,

  boolean view,
  boolean menu,
  boolean xbox
) implements SDLController {
  /** How many fields are present. */
  public static final int FIELD_COUNT = 22;

  public static final byte[] format = "LFFFFFFZZZZZZZZZZZZZZZ".getBytes();

  public static XBoxRecordingElement from(Reader r) {
    try {
      var self = new XBoxRecordingElement(
        r.readLong(),
        r.readFloat(),
        r.readFloat(),
        r.readFloat(),
        r.readFloat(),
        r.readFloat(),
        r.readFloat(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean(),
        r.readBoolean()
      );
      r.done();
      return self;
    }
    catch (IOException _) {
      // while we should properly report this, it seems most likely that this
      // would be due to us trying to read past the last element.
      return null;
    }
  }

  public void write(Writer w) {
    try {
      w.writeLong(timestamp);
      w.writeFloat(leftX);
      w.writeFloat(leftY);
      w.writeFloat(rightX);
      w.writeFloat(rightY);
      w.writeFloat(leftTrigger);
      w.writeFloat(rightTrigger);
      w.writeBoolean(leftBumper);
      w.writeBoolean(rightBumper);
      w.writeBoolean(leftStick);
      w.writeBoolean(rightStick);
      w.writeBoolean(dPadUp);
      w.writeBoolean(dPadLeft);
      w.writeBoolean(dPadDown);
      w.writeBoolean(dPadRight);
      w.writeBoolean(x);
      w.writeBoolean(y);
      w.writeBoolean(a);
      w.writeBoolean(b);
      w.writeBoolean(view);
      w.writeBoolean(menu);
      w.writeBoolean(xbox);
      w.done();
    }
    catch (IOException e) {
      Log.error("Failed to write XBoxRecordingElement to file: " + e.getMessage(), e);
    }
  }

  public boolean south() {
    return a;
  }

  public boolean east() {
    return b;
  }

  public boolean west() {
    return x;
  }

  public boolean north() {
    return y;
  }

  public boolean back() {
    return view;
  }

  public boolean guide() {
    return xbox;
  }

  public boolean start() {
    return menu;
  }
}
