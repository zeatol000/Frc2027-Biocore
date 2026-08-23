package org.team4153.core.hardware;

import org.team4153.core.hardware.Motor;

/** Companion class for [[org.team4153.core.hardware.Motor]] */
public class Motors {
	// Store by CAN ids. CAN can be from 0 to 62
	private static Motor[] motors = new Motor[63];

	/** Get the Motor array. Generally shouldn't be used but just in case. */
	public static Motor[] unsafeGetMotors() {
		return motors;
	}

	public static Motor byCanId(byte id) {
		return motors[id];
	}
}
