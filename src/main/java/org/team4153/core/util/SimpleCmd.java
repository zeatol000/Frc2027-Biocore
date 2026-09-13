package org.team4153.core.util;

import java.util.Set;

import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.command3.Scheduler.ScheduleResult;
import org.wpilib.command3.Mechanism;

/** Wrapper of WPILib Commands version 3, which is a bit more low-level than
 * 2026 and prior.
 *
 * This can also be defined as a functional lambda.
 */
@FunctionalInterface
public interface SimpleCmd extends Command {

	/** The main method */
	void run();

	/** Utility method to schedule this instance */
	default ScheduleResult schedule() {
		return Scheduler
			.getDefault()
			.schedule(this);
	}

	/** The low-level command3 method that calls the main method */
	default void run(Coroutine coroutine) {
		try {
			run();
		}
		finally {
			coroutine.yield();
		}
	}

	/** The command3 method that is the name of the command */
	default String name() {
		return "SimpleCmd[" + toString() + "]";
	}

	/** What the command requires to run. For this, none */
	default Set<Mechanism> requirements() {
		return Set.of();
	}
}
