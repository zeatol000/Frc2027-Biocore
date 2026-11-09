package org.team4153.core.util;

import java.util.function.Consumer;
import java.util.Set;

import org.wpilib.command3.*;

/** Command V3 utilities */
public class CMD {
  /** Empty Set of Mechanisms */
  public static final Set<Mechanism> emptyRequirements = Set.of();

  /** A command that does nothing */
  public static final Command empty = of(() -> {});

  /** Makes a command of the given parameters with lambda () -> void */
  public static Command of(Effect e) {
    return of(
      e,
      "Command[" + System.identityHashCode(e) + ']',
      emptyRequirements
    );
  }

  /** Makes a command of the given parameters with lambda () -> void */
  public static Command of(Effect e, String name) {
    return of(
      e,
      name,
      emptyRequirements
    );
  }

  /** Makes a command of the given parameters with lambda () -> void */
  public static Command of(Effect e, Set<Mechanism> requirements) {
    return of(
      e,
      "Command[" + System.identityHashCode(e) + ']',
      requirements
    );
  }

  /** Makes a command of the given parameters with lambda () -> void */
  public static Command of(
    Effect e,
    String name,
    Set<Mechanism> requirements
  ) {
    return new Command(){
      public void run(Coroutine coroutine) {
        try { e.apply(); }
        finally { coroutine.yield(); }
      }
      public String name() {
        return name;
      }
      public Set<Mechanism> requirements() {
        return requirements;
      }
    };
  }



  /** Makes a command of the given parameters with lambda Coroutine -> void */
  public static Command of(Consumer<Coroutine> fn) {
    return of(
      fn,
      "Command[" + System.identityHashCode(fn) + ']',
      emptyRequirements
    );
  }

  /** Makes a command of the given parameters with lambda Coroutine -> void */
  public static Command of(Consumer<Coroutine> fn, String name) {
    return of(
      fn,
      name,
      emptyRequirements
    );
  }

  /** Makes a command of the given parameters with lambda Coroutine -> void */
  public static Command of(Consumer<Coroutine> fn, Set<Mechanism> requirements) {
    return of(
      fn,
      "Command[" + System.identityHashCode(fn) + ']',
      requirements
    );
  }

  /** Makes a command of the given parameters with lambda Coroutine -> void */
  public static Command of(
    Consumer<Coroutine> fn,
    String name,
    Set<Mechanism> requirements
  ) {
    return new Command(){
      public void run(Coroutine coroutine) {
        try { fn.accept(coroutine); }
        finally { coroutine.yield(); }
      }
      public String name() {
        return name;
      }
      public Set<Mechanism> requirements() {
        return requirements;
      }
    };
  }
}
