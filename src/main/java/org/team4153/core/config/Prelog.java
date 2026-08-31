package org.team4153.core.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueType;

import edu.wpi.first.wpilibj2.command.Commands;

import org.team4153.core.config.Conf;

/** Logging utilities that do not interact with the config. This is to allow
 * for simple message reporting before the config's static constructor has been
 * invoked as accessing static fields without it causes issues.
 */
public class Prelog {
	/** Log a raw message */
	public static void print(String message) {
		Commands.print(message);
	}

	/** Log a message with info formatting */
	public static void info(String message) {
		message = "[info] " + message;
		print(message);
	}

	/** Log a message with warning formatting */
	public static void warn(String message) {
		message = "[warn] " + message;
		print(message);
	}

	/** Log a message with error formatting */
	public static void error(String message) {
		message = "[error] " + message;
		print(message);
	}
}
