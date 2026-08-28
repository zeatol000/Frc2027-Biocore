package org.team4153.core.hardware;

import org.team4153.core.hardware.CAN;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.hardware.impl.MotorType;

/** Physical motors attached to the CAN system. */
public interface Motor extends Hardware, Output, CAN {
	/** The type of the motor. Eg. TalonFX or SparkMAX */
	public abstract MotorType TYPE();

	/** Encoder offsets. Set to 0 if the motor is used for movement instead of
	 * specific rotation.
	 */
	public abstract float encoderOffset();
}
