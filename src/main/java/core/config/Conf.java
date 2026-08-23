package org.team4153.core.config;

import java.io.File;
import java.util.List;
import java.nio.file.Path;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import org.team4153.core.config.ConfConstruct;

/** The HOCON configuration represented in static fields. Please view
 * [[org.team4153.core.config.ConfConstruct]]
 */
public class Conf {
	/** The underlying [[com.typesafe.config.Config]]. */
	public static final Config config;

	static {
		config = switch (ConfConstruct.constructionType) {
			case 0 -> ConfigFactory.load();
			default ->
				throw new Error("illegal argument in ConfConstruct.constructionType");
		};
	}
}
