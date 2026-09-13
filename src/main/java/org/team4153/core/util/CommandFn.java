package org.team4153.core.util;

import java.util.Set;

import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.command3.Scheduler.ScheduleResult;
import org.wpilib.command3.Mechanism;

/** Wrapper of WPILib Commands version 3 for lambdas */
@FunctionalInterface
public interface CommandFn extends Command {

	/** Utility method to schedule this instance */
	default ScheduleResult schedule() {
		return Scheduler
			.getDefault()
			.schedule(this);
	}

	/** The command3 method that is the name of the command */
	default String name() {
		return "CommandFn[" + toString() + "]";
	}

	/** The mechanisms this requires */
	default Set<Mechanism> requirements() {
		return Set.of();
	}
}
