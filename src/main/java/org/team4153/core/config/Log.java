package org.team4153.core.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueType;

import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.command3.Mechanism;

import org.team4153.core.config.Conf;
import static org.team4153.core.config.Prelog.format;

/** Preferred logging API over System.out, System.err, IO, or WPILib
 * Commands.print.
 *
 * Example:
 * Log.print("if this gets logged then the robot is about to explode")
 */
public class Log implements Command {
	static {
		Config c = Conf.config;

		if(!c.hasPath("logging.mode") ||
			c.getValue("logging.mode").valueType() != ConfigValueType.STRING
		) {
			logLevel = LogLevel.match;
		}
		else {
			logLevel = LogLevel.fromString(
				c.getString("logging.mode")
			);
		}


		if(!c.hasPath("logging.file") ||
			c.getValue("logging.file").valueType() != ConfigValueType.STRING
		) {
			logFile = null;
		}
		else {
			String path = c.getString("logging.file");

			if (!path.equalsIgnoreCase("none"))
				logFile = Paths.get(path);

			else
				logFile = null;
		}


		if(!c.hasPath("logging.stack") ||
			c.getValue("logging.stack").valueType() != ConfigValueType.NUMBER
		) {
			maxStack = c.getInt("logging.stack");
		}
		else {
			maxStack = 8;
		}
	}



	/** The log level */
	public static final LogLevel logLevel;

	/** The file to log to (or null if none) */
	public static final Path logFile;

	/** How many stack iterations to print on an exception */
	public static final int maxStack;


	/** Log a raw message */
	public static void print(String message) {
		Scheduler
			.getDefault()
			.schedule(
				new Log(message)
			);
	}

	/** Log a message if the log level is dev. Formats with dev */
	public static void dev(String message) {
		if (logLevel != LogLevel.dev) return;

		message = "DEV " + LocalDateTime.now().format(format) + " " + message;
		print(message);

	}

	/** Log a message if the log level is pit or higher. Formats with pit */
	public static void pit(String message) {
		if (logLevel == LogLevel.match) return;

		message = "PIT " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with info formatting */
	public static void info(String message) {
		message = "INF " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with warning formatting */
	public static void warn(String message) {
		message = "WRN " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with error formatting */
	public static void error(String message) {
		message = "ERR " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with error formatting and an exception */
	public static void error(String message, Throwable e) {
		StringBuilder sb = new StringBuilder();

		sb.append("ERR ")
			.append(LocalDateTime.now().format(format))
			.append(' ')
			.append(message)
			.append('\n');

		StackTraceElement[] stack = e.getStackTrace();
		int len = Math.min(maxStack, stack.length);

		for (int i = 0; i < len; i++) {  // hopefully good formatting.
			StackTraceElement elem = stack[i];
			sb.append("\tat ")
				.append(elem.getClassName())
				.append("::")
				.append(elem.getMethodName())
				.append(" in ")
				.append(elem.getFileName())
				.append(':')
				.append(elem.getLineNumber())
				.append('\n');
		}

		print(sb.toString());
	}


// INSTANCE
	public Log(String message) {
		this.message = message;
	}

	public final String message;

	public void run(Coroutine coroutine) {
		System.out.println(message);

		if (logFile == null) {
			coroutine.yield();
			return;
		}

		try {
			Files.writeString(
				logFile,
				message,
				StandardOpenOption.CREATE,
				StandardOpenOption.APPEND
			);
		}
		catch (IOException e) {}
		finally {
			coroutine.yield();
		}
	}

	public String name() {
		return "Log";
	}

	public Set<Mechanism> requirements() {
		return Set.of();
	}


// LOG LEVEL
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
