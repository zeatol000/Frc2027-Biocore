package org.team4153.core.hardware;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import org.team4153.core.config.Log;
import org.team4153.core.hardware.CAN;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.hardware.impl.*;

/** Physical motors attached to the CAN system. */
public interface Motor extends Hardware, Output, CAN {
	/** The type of the motor. Eg. TalonFX or SparkMAX */
	public abstract MotorType TYPE();

	/** Encoder offsets. Set to 0 if the motor is used for movement instead of
	 * specific rotation.
	 */
	public abstract float encoderOffset();


	public static Motor getByCan(byte canId) {
		if (CAN.elements[canId] instanceof Motor m)
			return m;

		else
			return null;
	}


	public static Motor unsafeMakeMotor(Config self) {
		String cls = self.getString("class").toLowerCase();

		return switch (cls) {
			case "sparkmax" -> new SparkMaxMotor(self);
			default -> {
				String msg = "Unknown motor type: "+cls+"\nCannot continue execution";
				Log.error(msg);
				throw new ConfigException.Generic(msg);
			}
		};

		//return el;
	}
}
