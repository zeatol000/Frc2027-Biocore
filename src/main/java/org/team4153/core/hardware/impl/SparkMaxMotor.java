package org.team4153.core.hardware.impl;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel;

import com.typesafe.config.Config;

import org.team4153.core.hardware.Motor;
import org.team4153.core.hardware.MotorFeedback;
import org.team4153.core.hardware.impl.MotorTypes;

/* https://codedocs.revrobotics.com/java/com/revrobotics/spark/sparkmax */
public class SparkMaxMotor extends SparkMax implements Motor {
	protected final byte nodeId;
	protected final byte busId;
	protected final int id;
	protected final String name;

	protected final boolean brake;
	protected final int currentLimit; // amps
	
	protected final float eOffset;
	protected final MotorFeedback feedback;

	//protected final Encoder encoderOverride;

	public final SparkLowLevel.MotorType sparkType;

	public SparkMaxMotor(Config self) {
		this(
			(byte) self.getInt("nodeId"),
			(byte) self.getInt("canBus"),
			self.getInt("id"),
			self.getString("name"),

			self.hasPath("encoderOffset")
				? (float) self.getDouble("encoderOffset")
				: 0.0f,
			self.getInt("currentLimit"),
			self.getBoolean("brake"),
			self.hasPath("feedback")
				? MotorFeedback.fromString(self.getString("feedback"))
				: MotorFeedback.none,

		//encoderOverride = ;
		
			!self.hasPath("motorType")
				? SparkLowLevel.MotorType.kBrushless
				: !self.getString("motorType").toLowerCase().equals("kbrushed")
					? SparkLowLevel.MotorType.kBrushless
					: SparkLowLevel.MotorType.kBrushed
		);
	}

	public SparkMaxMotor(
		byte nodeId,
		byte busId,
		int  id,
		String name,
		float eOffset,
		int currentLimit,
		boolean brake,
		MotorFeedback feedback,
		SparkLowLevel.MotorType sparkType
	) {
		super((int) nodeId, sparkType); // the documentation is out of date istg
		this.nodeId = nodeId;
		this.busId = busId;
		this.id = id;
		this.name = name;
		this.eOffset = eOffset;
		this.currentLimit = currentLimit;
		this.brake = brake;
		this.feedback = feedback;
		this.sparkType = sparkType;
	}

	public final MotorTypes TYPE() {
		return MotorTypes.SparkMax;
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
