package org.team4153.core.auto.recording;

import java.io.Serial;
import java.io.Serializable;

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
 * @param dpadUp        if the dpad_up button was pressed
 * @param dpadLeft      if the dpad_left button was pressed
 * @param dpadDown      if the dpad_down button was pressed
 * @param dpadRight     if the dpad_right button was pressed
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
  float leftTrigger,
  float rightX,
  float rightY,
  float rightTrigger,

  boolean leftBumper,
  boolean rightBumper,

  boolean dpadUp,
  boolean dpadLeft,
  boolean dpadDown,
  boolean dpadRight,

  boolean y,
  boolean x,
  boolean a,
  boolean b,

  boolean view,
  boolean menu,
  boolean xbox
) implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  /** How many fields are present. */
  public static final int FIELD_COUNT = 20;
}
