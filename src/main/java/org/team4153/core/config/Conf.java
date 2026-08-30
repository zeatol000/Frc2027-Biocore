package org.team4153.core.config;

import java.io.File;
import java.util.List;
import java.nio.file.Path;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import org.team4153.core.hardware.Hardware;

/** The HOCON configuration represented in static fields  */
public class Conf {
	/** The underlying [[com.typesafe.config.Config]]. */
	public static final Config config;

	public static final List<? extends Config>
		can,
		input;



	static {
		config = ConfigFactory.load();

		can = config.getConfigList("can");
		input = config.getConfigList("input");

		can.forEach(Hardware::unsafeMakeHardware);

		if (config.hasPath("hardwareArraySize"))
			hardwareArraySize = config.getInt("hardwareArraySize");
		else
			hardwareArraySize = 0x1000;
	}


	public static final int hardwareArraySize;
}
