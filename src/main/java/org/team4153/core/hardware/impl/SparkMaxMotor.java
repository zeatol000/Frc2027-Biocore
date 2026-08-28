package org.team4153.core.hardware.impl;

import org.team4153.core.hardware.Motor;
import org.team4153.core.hardware.impl.MotorType;

public class SparkMaxMotor implements Motor {
	protected final byte canId;
	protected final int id;
	protected final String name;
	protected final float eOffset;

	public SparkMaxMotor(byte canId, int id, String name) {
		this.canId = canId;
		this.id = id;
		this.name = name;
		this.eOffset = 0.0f;
	}

	public SparkMaxMotor(byte canId, int id, String name, float eOffset) {
		this.canId = canId;
		this.id = id;
		this.name = name;
		this.eOffset = eOffset;
	}

	public final MotorType TYPE() {
		return MotorType.SparkMax;
	}

	public final byte CAN_ID() {
		return canId;
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
