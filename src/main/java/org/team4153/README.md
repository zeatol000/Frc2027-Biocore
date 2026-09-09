# Package org.team4153

For maintainability, every directory will have a README.md file that explains
what it is and goes into minor depth on what does what. The md file extension
means it is a 'markdown' file. It just allows for simple formatting like
bolding.

Simliarly, each source file will have documentation comments (begin with `/**`
and end with `*/`).

Hopefully, the documentation should be good enough that people who don't
understand the codebase or Java can figure out where an issue is coming from.

If you are going to use AI, please tell it to read the README.md files before
doing anything.

---
## Subpackages

**core**:
Provides wrappers over hardware and defines simple systems that all robots use
such as the drivetrain. This package is quite complicated so please read its
respective README file.

**biocore**:
The package for the 2027 FRC game BioCore.


## The JVM and Environment

Team 4153 is going to be switching to using Raspberry Pi CM5's for the robot
brain instead of a RoboRIO. These have quad-core Cortex-A76 processors and 4 GB
of RAM.

While this is fairly good for normal use, the JVM can introduce tiny sutters in
the execution. This means we need to be a bit picky with the JVM and JVM flags.

First of all, we want to use a modern JDK (such as JDK 26) ideally from OpenJDK.
JDK 26 allows us to use Project Leyden, which lets us 'pre-record' how the
robot runs so the JVM can optimize the runtime execution very quickly. Leyden 
optimizations range from lower RAM usage to the JVM compiling certain functions
to native code.

I also reccomend switching to JDK 28 when it releases in a few years. It
introduces Project Valhalla's value classes. These allow for creating classes
that do not exist on the heap and are treated pretty much like primitive values.

Next the JVM flags can be very important. These are some recommended defaults:
```
-XX:+UseZGC             Use the Z garbage collector to minimize GC pauses
-XX:+AlwaysPreTouch     Fully allocate defauly memory at startup to prevent 
                        some memory allocation issues.
-Xms2g                  Allocate 2 gigabytes by default in the heap.
-Xmx3g                  Allocate 3 gigabytes at maximum. Don't do anything
                        higher as that can potentially starve the operating
                        system or JVM itself.
```
