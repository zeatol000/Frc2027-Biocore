package org.team4153.core.util;

/** Represents a lambda that takes no arguments and returns void.
 * A side effect, if you will. */
@FunctionalInterface
public interface Effect {
  void apply();
}