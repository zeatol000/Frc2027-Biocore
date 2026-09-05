package org.team4153.core.hardware.impl;

public enum MotorTypes {
	TalonFX,
	KrackenX60,
	SparkMax,
	Unknown;

	public static MotorTypes fromString(String str) {
		return switch (str.toLowerCase()) {
			case "neo" -> SparkMax;
			case "sparkmax" -> SparkMax;
			case "talonfx" -> TalonFX;
			case "krackenx60" -> KrackenX60;
			default -> Unknown;
		};
	}
}
