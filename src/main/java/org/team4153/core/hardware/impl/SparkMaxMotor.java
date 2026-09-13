package org.team4153.core.hardware.impl;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
//import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.util.CANPorts;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import com.typesafe.config.Config;

import org.wpilib.hardware.bus.CANPort;
import org.wpilib.math.geometry.Rotation2d;
//import org.wpilib.wpilibj.smartdashboard.SmartDashboard;

import org.team4153.core.hardware.Motor;
import org.team4153.core.hardware.MotorFeedback;
import org.team4153.core.util.MathUtils;

/** SparkMax motors https://codedocs.revrobotics.com/java/com/revrobotics/spark/sparkmax
 *
 * Normally, you configure SparkMax motors via SparkMaxConfig, but those have
 * been marked as deprecated and we cannot incur warnings under -Werror, so I
 * guess we just aren't configuring the motors?
 */
public class SparkMaxMotor extends SparkMax implements Motor {
	protected final byte nodeId;
	protected final CANPort port;
	protected final int id;
	protected final String name;

	protected final boolean brake;
	
	protected final float eOffset;
	protected final boolean rotation;
	protected final MotorFeedback feedback;

	//protected final Encoder encoderOverride;

	public final SparkLowLevel.MotorType sparkType;
	protected final SparkClosedLoopController controller;

	public SparkMaxMotor(Config self) {
		this(
			(byte) self.getInt("nodeId"),
			CANPorts.fromBusId(self.getInt("canPort")),
			self.getInt("id"),
			self.getString("name"),

			self.hasPath("encoderOffset")
				? (float) self.getDouble("encoderOffset")
				: 0.0f,
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

			self.hasPath("encoderOffset"),
			
			self.hasPath("kReset")
				? !self.getBoolean("kReset")
				: false,
			self.hasPath("kPersist")
				? !self.getBoolean("kPersist")
				: false,

			self.hasPath("inverted")
				? self.getBoolean("inverted")
				: false,

			null
		);
	}

	/** Primary constructor. Takes all of the important arguments and assembles
	 * a SparkMaxMotor instance.
	 *
	 * @param cfg Alternate {@link SparkMaxConfig} that will be prioritized over
	 * 	making a new SparkMaxConfig. 
	 */
	public SparkMaxMotor(
		byte nodeId,
		CANPort port,
		int  id,
		String name,
		float eOffset,
		boolean brake,
		MotorFeedback feedback,
		SparkLowLevel.MotorType sparkType,
		boolean rotation,
		boolean noReset,
		boolean noPersist,
		boolean inverted,
		SparkMaxConfig cfg
	) {
		super(port, nodeId, sparkType);
		this.nodeId = nodeId;
		this.port = port;
		this.id = id;
		this.name = name;
		this.eOffset = eOffset;
		this.brake = brake;
		this.feedback = feedback;
		this.sparkType = sparkType;
		this.rotation = rotation;

		controller = rotation
					  ? getClosedLoopController()
					  : null;

		if (cfg == null)
			 cfg = new SparkMaxConfig();

		cfg
			.idleMode( brake? IdleMode.kBrake: IdleMode.kCoast )
			.inverted(inverted);

		configure(
			cfg,
			noReset
				? ResetMode.kNoResetSafeParameters
				: ResetMode.kResetSafeParameters,
			noPersist
				? PersistMode.kNoPersistParameters
				: PersistMode.kPersistParameters
		);
	}


// Power motor methods
	public final void run(double speed) {
		speed = MathUtils.clamp(speed, -1, 1);
		setThrottle(speed);
	}

	public final double speed() {
		return getThrottle();
	}

	public final double distance() {
		return getEncoder().getPosition().get();
	}

// Power and Rotation
	public final void stop() {
		stopMotor();
	}

// Rotation motor methods
	public final Rotation2d rawAngle() {
		return new Rotation2d(getAnalog().getPosition().get());
	}

	public final Rotation2d realAngle() {
		return new Rotation2d(
			getAnalog().getPosition().get() - eOffset
		);
	}

	public final void rotate(float setpoint) {
		controller.setSetpoint(setpoint - eOffset, ControlType.kPosition);
	}




// General getters
	public final byte NODE_ID() {
		return nodeId;
	}

	public final CANPort PORT() {
		return port;
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
