package org.team4153.core.hardware.impl;

public enum MotorType {
	TalonFX,
	SparkMax,
	KrackenX60,
	RevNeo,
	Neo2,
	NeoVortex,
	Neo550,
	Unknown;

	public static MotorType fromString(String str) {
		return switch (str.toLowerCase()) {
			case "talonfx" -> TalonFX;
			case "sparkmax" -> SparkMax;
			case "krackenx60" -> KrackenX60;
			case "revneo" -> RevNeo;
			case "neo2" -> Neo2;
			case "neovortex" -> NeoVortex;
			case "neo550" -> Neo550;
			default -> Unknown;
		};
	}
}
