package org.team4153.core.hardware;

public enum MotorFeedback {
	incrementalEncoder,
	absoluteEncoder,
	resolver,
	hallEffect,
	tachometer,
	none;

	public static MotorFeedback fromString(String str) {
		return switch (str.toLowerCase()) {
			case "absoluteencoder" -> absoluteEncoder;
			case "incrementalencoder" -> incrementalEncoder;
			case "resolver" -> resolver;
			case "halleffect" -> hallEffect;
			case "tachometer" -> tachometer;
			default -> none;
		};
	}
}
