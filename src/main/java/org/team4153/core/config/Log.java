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

/** Preferred logging API over System.out, System.err, IO, or WPILib
 * Commands.print.
 *
 * Example:
 * Log.print("if this gets logged then the robot is about to explode")
 */
public class Log {
	/** The log level */
	public static final LogLevel logLevel;

	/** The file to log to (or null if none) */
	public static final Path logFile;


	/** Log a raw message */
	public static void print(String message) {
		Commands.print(message);

		if (logFile == null) return;
		try {
			Files.writeString(
				logFile,
				message,
				StandardOpenOption.CREATE,
				StandardOpenOption.APPEND
			);
		}
		catch (IOException e) {}
	}

	/** Log a message if the log level is dev. Formats with dev */
	public static void dev(String message) {
		if (logLevel != LogLevel.dev) return;
		message = "[dev] " + message;
		print(message);

	}

	/** Log a message if the log level is pit or higher. Formats with pit */
	public static void pit(String message) {
		if (logLevel == LogLevel.match) return;
		message = "[pit] " + message;
		print(message);
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



	static {
		Config c = Conf.config;

		if (
			!c.hasPath("logging.mode") ||
			c.getValue("logging.mode").valueType() != ConfigValueType.STRING
		)
			logLevel = LogLevel.match;

		else logLevel = LogLevel.fromString(
			c.getString("logging.mode")
		);


		if (
			!c.hasPath("logging.file") ||
			c.getValue("logging.file").valueType() != ConfigValueType.STRING
		)
			logFile = null;

		else logFile = Paths.get(
			c.getString("logging.file")
		);
	}



	public static enum LogLevel {
		match, pit, dev;

		public static LogLevel fromString(String str) {
			return switch (str.toLowerCase()) {
				case "dev" -> dev;
				case "pit" -> pit;
				default -> match;
			};
		}
	}
}
