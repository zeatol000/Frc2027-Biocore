package org.team4153.core.hardware.impl;

public enum MotorType {
	TalonFX,
	KrackenX60,
	SparkMax,
	Unknown;

	public static MotorType fromString(String str) {
		return switch (str.toLowerCase()) {
			case "neo" -> SparkMax;
			case "sparkmax" -> SparkMax;
			case "talonfx" -> TalonFX;
			case "krackenx60" -> KrackenX60;
			default -> Unknown;
		};
	}
}
