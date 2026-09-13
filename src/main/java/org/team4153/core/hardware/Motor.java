package org.team4153.core.hardware;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import org.wpilib.math.geometry.Rotation2d;

import org.team4153.core.config.Prelog;
import org.team4153.core.hardware.CAN;
import org.team4153.core.hardware.Hardware;
import org.team4153.core.hardware.impl.*;

/** Physical motors attached to the CAN system.
 *
 * Note: for computation times, we use floats for rotation angles such as
 * encoder offsets. But we use doubles for large values such as speed.
 */
public interface Motor extends Hardware, Output<Motor>, CAN<Motor> {
	/** Encoder offsets. Set to 0 if the motor is used for movement instead of
	 * specific rotation.
	 */
	float encoderOffset();

	/** Is this motor used for rotation instead of general power? */
	boolean isRotation();


	/** Get a motor by its CAN id, or null if it doesn't exist or isn't a motor */
	static Motor getByCan(byte canId) {
		if (CAN.elements[canId] instanceof Motor m)
			return m;

		else
			return null;
	}


	/** Internal method for assembling motors by config */
	static Motor unsafeMakeMotor(Config self) {
		String cls = self.getString("class").toLowerCase();

		return switch (cls) {
			case "sparkmax" -> new SparkMaxMotor(self);
			default -> {
				String msg = "Unknown motor type: "+cls+"\nCannot continue execution";
				Prelog.error(msg);
				throw new ConfigException.Generic(msg);
			}
		};

		//return el;
	}

	/** Set the current speed. Power motors */
	void run(double speed);

	/** ???. Power motors */
	double distance();

	/** Get the raw angle of the encoder. Rotation motors */
	Rotation2d rawAngle();

	/** Get the angle of the encoder, considering offset. Rotation motors */
	Rotation2d realAngle();

	/** Set the angle of rotation. Rotation motors */
	void rotate(float setpoint);

	/** Stop movement or return to the default rotation. Either power or rotation */
	void stop();

	/** Get the current speed. Either power or rotation */
	double speed();

	/* Get the current acceleration. Either power or rotation *
	double acc();*/


	//void log(String key);
}
