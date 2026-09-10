package org.team4153.core.hardware.impl;

//import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import com.typesafe.config.Config;

import edu.wpi.first.math.geometry.Rotation2d;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team4153.core.hardware.Motor;
import org.team4153.core.hardware.MotorFeedback;
//import org.team4153.core.hardware.impl.MotorTypes;
import org.team4153.core.util.MathUtils;

/* https://codedocs.revrobotics.com/java/com/revrobotics/spark/sparkmax */
public class SparkMaxMotor extends SparkMax implements Motor {
	protected final byte nodeId;
	protected final byte busId;
	protected final int id;
	protected final String name;

	protected final boolean brake;
	protected final int currentLimit; // amps
	
	protected final float eOffset;
	protected final boolean rotation;
	protected final MotorFeedback feedback;

	//protected final Encoder encoderOverride;

	public final SparkLowLevel.MotorType sparkType;
	protected final SparkClosedLoopController controller;

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
				: !self.getString("motorType").equalsIgnoreCase("kBrushed")
					? SparkLowLevel.MotorType.kBrushless
					: SparkLowLevel.MotorType.kBrushed,

			self.hasPath("encoderOffset")
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
		SparkLowLevel.MotorType sparkType,
		boolean rotation
	) {
		super(nodeId, sparkType); // the website's documentation is out of date... uuhhhg
		this.nodeId = nodeId;
		this.busId = busId;
		this.id = id;
		this.name = name;
		this.eOffset = eOffset;
		this.currentLimit = currentLimit;
		this.brake = brake;
		this.feedback = feedback;
		this.sparkType = sparkType;
		this.rotation = rotation;

		controller = rotation
					  ? getClosedLoopController()
					  : null;
	}


// Power motor methods
	public final void run(double speed) {
		speed = MathUtils.clamp(speed, -1, 1);
		set(speed);
	}

	public final double speed() {
		return get();
	}

	public final double distance() {
		return getEncoder().getPosition();
	}

// Power and Rotation
	public final void stop() {
		set(0); // SparkMax method
	}

// Rotation motor methods
	public final Rotation2d rawAngle() {
		return new Rotation2d(getAnalog().getPosition());
	}

	public final Rotation2d realAngle() {
		return new Rotation2d(((float) getAnalog().getPosition()) - eOffset);
	}

	public final void rotate(float setpoint) {
		controller.setSetpoint(setpoint - eOffset, ControlType.kPosition);
	}




// General getters
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

	public final boolean isRotation() {
		return rotation;
	}
}
