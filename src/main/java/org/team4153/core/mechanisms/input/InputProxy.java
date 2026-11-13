package org.team4153.core.mechanisms.input;

import org.team4153.core.util.SDLController;

/** This class provides a mutable wrapper over input sources for easily
 * switching the actual input source. For example, this can be used to keep a
 * distinction from controller inputs and auto recording playback.
 *
 * Basically this is just a wrapper over the volatile field 'src'.
 * However, if you are experiencing race conditions and want to make sure that
 * the field gets properly mutated, use the get and set methods, which are
 * marked synchronized.
 */
public class InputProxy<T extends InputSource> implements SDLController {
	/** The InputSource to delegate to. */
	public volatile T src;

	public InputProxy(T active) {
		this.src = active;
	}


	/** Synchronize this proxy and get the InputSource. */
	public synchronized T get() {
		return this.src;
	}

	/** Synchronize this proxy and set the InputSource. */
	public synchronized void set(T active) {
		this.src = active;
	}


	/** Positional south button. Xbox 'A' */
	public boolean south() {
		return src.south();
	}

	/** Positional east button. Xbox 'B' */
	public boolean east() {
		return src.east();
	}

	/** Positional west button. Xbox 'X' */
	public boolean west() {
		return src.west();
	}

	/** Positional north button. Xbox 'Y' */
	public boolean north() {
		return src.north();
	}


	/** Back button. Xbox 'Select' */
	public boolean back() {
		return src.back();
	}

	/** Home button. Xbox 'Xbox' */
	public boolean guide() {
		return src.guide();
	}

	/** Start / Options button. */
	public boolean start() {
		return src.start();
	}


	/** Left stick press / L3 */
	public boolean leftStick() {
		return src.leftStick();
	}

	/** Right stick press / R3 */
	public boolean rightStick() {
		return src.rightStick();
	}


	/** Left bumper / LB / L1 */
	public boolean leftBumper() {
		return src.leftBumper();
	}

	/** Right bumper / RB / R1 */
	public boolean rightBumper() {
		return src.rightBumper();
	}


	/** Directional pad up */
	public boolean dPadUp() {
		return src.dPadUp();
	}

	/** Directional pad left */
	public boolean dPadLeft() {
		return src.dPadLeft();
	}

	/** Directional pad right */
	public boolean dPadRight() {
		return src.dPadRight();
	}

	/** Directional pad down */
	public boolean dPadDown() {
		return src.dPadDown();
	}


	/** Left joystick horizontal. Negative values correspond to right. */
	public double leftX() {
		return src.leftX();
	}

	/** Left joystick vertical. Negative values correspond to down. */
	public double leftY() {
		return src.leftY();
	}

	/** Right joystick horizontal. Negative values correspond to right. */
	public double rightX() {
		return src.rightX();
	}

	/** Right joystick vertical. Negative values correspond to down. */
	public double rightY() {
		return src.rightY();
	}


	/** Left trigger / L2. Nonnegative. */
	public double leftTrigger() {
		return src.leftTrigger();
	}

	/** Right trigger / R2. Nonnegative. */
	public double rightTrigger() {
		return src.rightTrigger();
	}
}
