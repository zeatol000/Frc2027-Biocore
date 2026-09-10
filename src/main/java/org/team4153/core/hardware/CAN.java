package org.team4153.core.hardware;

/** A hardware element that is attached to the CAN network.
 *
 * Requires that any implementing class also implements Hardware.
 *
 * The CAN (Computer Area Network) ID is a 29 bit identifier that is split into
 * 5 parts:
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
 *
 * Useful links:
 * - https://docs.wpilib.org/en/stable/docs/software/can-devices/index.html
 * - https://docs.wpilib.org/en/stable/docs/software/can-devices/using-can-devices.html
 * - https://docs.wpilib.org/en/stable/docs/software/can-devices/can-addressing.html
 */
public interface CAN<Self extends CAN<Self> & Hardware> {
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
		// if no, return -1 to -4 depending on what failed.
		if ((nodeId & ~0x3f) != 0)
			return -1;

		if ((apiIndex & ~0xf) != 0)
			return -2;

		if ((apiClass & ~0x3f) != 0)
			return -3;

		if ((type & ~0x1f) != 0)
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


	/** CAN device types. 5 bits (32 possible values) */
	public static class types {
		public static final byte
			broadcastMessages = 0,
			robotController = 1,
			motorController = 2,
			relayController = 3,
			gyroSensor = 4,
			accelerometer = 5,
			distanceSensor = 6,
			encoder = 7,
			powerDistributionModule = 8,
			pneumaticsController = 9,
			misc = 10,
			ioBreakout = 11,
			servoController = 12,
			colorSensor = 13,
			// reserved 14 to 30
			firmwareUpdate = 31
		;
	}

	/** Manufacturers. 8 bits (256 possible values) */
	public static class manufacturers {
		public static final byte
			broadcast = 0,
			ni = 1,
			luminaryMicro = 2,
			deka = 3,
			ctrElectronics = 4,
			revRobotics = 5,
			grapple = 6,
			mindSensors = 7,
			teamUse = 8,
			kauaiLabs = 9,
			copperforge = 10,
			playingWithFusion = 11,
			studica = 12,
			theThriftyBot = 13,
			reduxRobotics = 14,
			andyMark = 15,
			vividHosting = 16,
			vertosRobotics = 17,
			swyftRobotics = 18,
			lumynLabs = 19,
			brushlandLabs = 20
			// reserved 21 to 255
		;
	}

	/** API Grouping. 6 bits (64 possible values) */
	public static class apiClass {
		public static final byte
			voltageControlMode = 0,
			speedControlMode = 1,
			voltageCompensationMode = 2,
			positionControlMode = 3,
			currentControlMode = 4,
			status = 5,
			periodicStatus = 6,
			configuration = 7,
			ack = 8
		;
	}

	/** API Index. 4 bits (16 possible values) */
	public static class apiIndex {
		public static final byte
			enableControl = 0,
			disableControl = 1,
			setSetpoint = 2,
			pConstant = 3,
			iConstant = 4,
			dConstant = 5,
			setReference = 6,
			trustedEnable = 7,
			trustedSetNoAck = 8,
			trustedSetSetpointNoAck = 10,
			setSetpointNoAck = 11
		;
	}

	/** Broadcast messages are messages sent to all nodes by setting the device
	 * type and manufacturer to 0. The API Class for broadcast messages is 0.
	 */
	public static class broadcastMessages {
		public static final byte
			disable = 0,
			systemHalt = 1,
			systemReset = 2,
			deviceAssign = 3,
			deviceQuery = 4,
			heartbeat = 5,
			sync = 6,
			update = 7,
			firmwareVersion = 8,
			enumerate = 9,
			systemResume = 10
		;
	}
}
