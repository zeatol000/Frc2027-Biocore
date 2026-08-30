package org.team4153.core.hardware;

/** A hardware element that is attached to the CAN network.
 *
 * The CAN (Computer Area Network) has 5 parts:
 * - device type 		(5 bits)
 * - manufacturer id (8 bits)
 * - api class 		(6 bits)
 * - api index 		(4 bits)
 * - node id  			(6 bits)
 *
 * Device type (bits 28-24) is something like a motor controller.
 *
 * Manufacturer id (bits 23-16) is the manufacturer's id (shocking).
 *
 * API class (bits 15-10) and API index (bits 9-6) are used internally by the
 * hardware API such as SparkMax.
 *
 * Node Id (bits 5-0) is the actual device.
 */
public interface CAN {
	/** The unique node id */
	public abstract byte NODE_ID();

	/** The unique bus id */
	public abstract byte BUS_ID();


	/** An array of all CAN elements. Generally, users shouldn't touch this. */
	public static final CAN[] elements = new CAN[64];


	/** Pack a CAN id */
	public static int pack(
		byte type,				// 5
		byte manufacturer,	// 8
		byte apiClass,			// 6
		byte apiIndex,			// 4
		byte nodeId				// 6
	) {
		// check if the fields fit into their respective area
		// if no, return -1 to -4
		if ((nodeId & 0x3f ^ nodeId) != 0)
			return -1;

		if ((apiIndex & 0xf ^ apiIndex) != 0)
			return -2;

		if ((apiClass & 0x3f ^ apiClass) != 0)
			return -3;

		if ((type & 0x1f ^ type) != 0)
			return -4;

		return
			(type << 23) |
			(manufacturer << 18) |
			(apiClass << 10) |
			(apiIndex << 6) |
			nodeId;
	}

	public static byte getType(int can) {
		return (byte)( can >> 23 );
	}

	public static byte getManufacturer(int can) {
		return (byte)( (can >> 18) & 0xff );
	}

	public static byte getApiClass(int can) {
		return (byte)( (can >> 10) & 0x3f );
	}

	public static byte getApiIndex(int can) {
		return (byte)( (can >> 6) & 0xf );
	}

	public static byte getNodeId(int can) {
		return (byte)( can & 0x3f );
	}
}
