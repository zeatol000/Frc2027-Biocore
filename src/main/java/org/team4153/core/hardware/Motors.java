package org.team4153.core.hardware;

import org.team4153.core.hardware.Motor;

/** Companion class for [[org.team4153.core.hardware.Motor]] */
public class Motors {
	/* Store by CAN ids. CAN can be from 0 to 62.
	 * Ideally, you shouldn't touch this
	 */
	public static final Motor[] motors = new Motor[63];

	public static Motor byCanId(byte id) {
		return motors[id];
	}
}
