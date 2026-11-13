package org.team4153.core.util;

/** SDL is a library that provides a standard api for different types of
 * inputs such as controller. This class represents an SDL_Gamepad in the
 * official library.
 */
public interface SDLController {
  /** Positional south button. Xbox 'A' */
  boolean south();

  /** Positional east button. Xbox 'B' */
  boolean east();

  /** Positional west button. Xbox 'X' */
  boolean west();

  /** Positional north button. Xbox 'Y' */
  boolean north();


  /** Back button. Xbox 'Select' */
  boolean back();

  /** Home button. Xbox 'Xbox' */
  boolean guide();

  /** Start / Options button. */
  boolean start();


  /** Left stick press / L3 */
  boolean leftStick();

  /** Right stick press / R3 */
  boolean rightStick();


  /** Left bumper / LB / L1. SDL calls this leftShoulder but that sounds dumb. */
  boolean leftBumper();

  /** Right bumper / RB / R1. SDL calls this leftShoulder but that sounds dumb. */
  boolean rightBumper();


  /** Directional pad up */
  boolean dPadUp();

  /** Directional pad left */
  boolean dPadLeft();

  /** Directional pad right */
  boolean dPadRight();

  /** Directional pad down */
  boolean dPadDown();


  /** Left joystick horizontal. Negative values correspond to right. */
  double leftX();

  /** Left joystick vertical. Negative values correspond to down. */
  double leftY();

  /** Right joystick horizontal. Negative values correspond to right. */
  double rightX();

  /** Right joystick vertical. Negative values correspond to down. */
  double rightY();


  /** Left trigger / L2. Nonnegative. */
  double leftTrigger();

  /** Right trigger / R2. Nonnegative. */
  double rightTrigger();
}
