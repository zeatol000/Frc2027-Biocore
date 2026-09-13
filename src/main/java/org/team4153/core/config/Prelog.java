package org.team4153.core.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Logging utilities that do not interact with the config. This is to allow
 * for simple message reporting before the config's static constructor has been
 * invoked as accessing static fields without it causes issues.
 *
 * Eg:
 * robot accesses Conf for the first time
 * -> Conf invokes the static constructor
 * -> static constructor creates hardware elements
 * -> hardware elements attempt to access the normal Log class
 * -> Log class's static constructor runs
 * -> the Log static constructor tries to access logging fields in Conf.config
 * -> since the Conf static constructor has not finished, this accessing may
 *    be ignored (i'm not too sure how it works after this)
 * -> Logging can no longer happen.
 *
 * However with Prelog, it ignores the Conf class so static construction can
 * happen properly.
 */
public class Prelog {
	public static final DateTimeFormatter format =
		DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss.S"); // S for fraction-of-second or n for nano-of-second.

	/** Log a raw message */
	public static void print(String message) {
		System.out.println(message);
	}

	/** Log a message with info formatting */
	public static void info(String message) {
		message = "INF! " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with warning formatting */
	public static void warn(String message) {
		message = "WRN! " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}

	/** Log a message with error formatting */
	public static void error(String message) {
		message = "ERR! " + LocalDateTime.now().format(format) + " " + message;
		print(message);
	}
}
