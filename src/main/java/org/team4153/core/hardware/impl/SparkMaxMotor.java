package org.team4153.core.hardware.impl;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel;

import com.typesafe.config.Config;

import org.team4153.core.hardware.Motor;
import org.team4153.core.hardware.MotorFeedback;
import org.team4153.core.hardware.impl.MotorType;

public class SparkMaxMotor implements Motor {
	protected final byte nodeId;
	protected final byte busId;
	protected final int id;
	protected final String name;

	protected final boolean brake;
	protected final int currentLimit; // amps
	
	protected final float eOffset;
	protected final MotorFeedback feedback;

	//protected final Encoder encoderOverride;

	public SparkMaxMotor(Config self) {
		nodeId = (byte) self.getInt("nodeId");
		busId	 = (byte) self.getInt("canBus");
		id		 = self.getInt("id");
		name	 = self.getString("name");

		eOffset		 = self.hasPath("encoderOffset")
						 ? (float) self.getDouble("encoderOffset")
						 : 0.0f;
		currentLimit = self.getInt("currentLimit");
		brake			 = self.getBoolean("brake");
		feedback		 = self.hasPath("feedback")
						 ? MotorFeedback.fromString(self.getString("feedback"))
						 : null;

		//encoderOverride = ;
	}

	public final MotorType TYPE() {
		return MotorType.SparkMax;
	}

	public final byte NODE_ID() {
		return nodeId;
	}

	public final byte BUS_ID() {
		return busId;
	}

	public final int ID() {
		return id;
	}

	public final String NAME() {
		return name;
	}

	public final float encoderOffset() {
		return eOffset;
	}
}
