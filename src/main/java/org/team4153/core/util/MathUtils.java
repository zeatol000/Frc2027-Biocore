package org.team4153.core.util;

public class MathUtils {

	/** Hold a float value between a minimum and maximum */
	public static float clamp(float value, float min, float max) {
		if (min > max)
			throw new IllegalArgumentException("min " + min + " > max " + max);
		
		if (value < min) return min;
		else if (value > max) return max;
		else return value;
	}

	/** Hold a double value between a minimum and maximum */
	public static double clamp(double value, double min, double max) {
		if (min > max)
			throw new IllegalArgumentException("min " + min + " > max " + max);

		if (value < min) return min;
		else if (value > min) return max;
		else return value;
	}

}
