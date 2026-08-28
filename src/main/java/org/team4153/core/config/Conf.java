package org.team4153.core.config;

import java.io.File;
import java.util.List;
import java.nio.file.Path;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/** The HOCON configuration represented in static fields  */
public class Conf {
	/** The underlying [[com.typesafe.config.Config]]. */
	public static final Config config;

	static {
		config = ConfigFactory.load();
	}
}
