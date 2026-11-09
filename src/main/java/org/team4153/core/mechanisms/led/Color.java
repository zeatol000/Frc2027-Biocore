package org.team4153.core.mechanisms.led;

/** Color serialization and utilities */
public class Color {

	/** Default RGB color */
	public record RGB(int r, int g, int b) {}
	
	/** RGB color with Alpha */
	public record RGBA(int r, int g, int b, int a) {}


	/** Store RGBA as bytes and pack into an int */
	public static int pack(
		byte r, byte g, byte b, byte a
	) {
		return (r << 24) | (g << 16) | (b << 8) | a;
	}

	/** Get red out of a packed int */
	public static byte getR(int rgba) {
		return (byte) (rgba >> 24);
	}

	/** Get green out of a packed int */
	public static byte getG(int rgba) {
		return (byte) ((rgba >> 16) & 0xff);
	}

	/** Get blue out of a packed int */
	public static byte getB(int rgba) {
		return (byte) ((rgba >> 8) & 0xff);
	}

	/** Get alpha out of a packed int */
	public static byte getA(int rgba) {
		return (byte) rgba;
	}


	/** Nice packed colors */
	public static final int
		RED	= 0xff0000ff,
		BLUE	= 0xff00ff,
		GREEN	= 0xffff,
		BLACK	= 0xff,
		WHITE	= -1;
}
