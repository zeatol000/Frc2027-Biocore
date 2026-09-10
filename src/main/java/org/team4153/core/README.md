# Package org.team4153.core

This directory (folder) is the core. Basically, it handles abstraction layers
and will hopefully be competent enough such that separate robots can use the
core without heavy modifications.

With the exception of collections, every class in the core project is public
and not final. This allows people to extend and override whatever they need in
a scenario that the core has not been prepared for.

That said, please avoid invoking the constructors of:
- Hardware implementation classes.
- ...


---
## Subpackages
**auto**:
Utilities for auto.

**collection**:
Ever used an `ArrayList`? Thats a collection. It stores multiple values of the
given type. This package stores other collections. Do note that not all
collections store multiple values.

**config**:
Configuration utilities.

**hardware**:
Contains interfaces and classes that correspond to different hardware elements
such as motors and the radio.

**svc**:
Code for dealing with or being the supervising laptop (drivers station).

**systems**:
Subsystem interfaces and classes for a robot. A subsystem could literally be
anything that does stuff. Eg. a shooter, a funnel, the drivetrain itself.

**util**:
General utilities.



## Good Links
#### Important
1. [WPILib Javadoc](https://github.wpilib.org/allwpilib/docs/release/java/index.html)
    Javadoc is Java's documentation system. It shows you every package, class,
    method, field, etc. It also has the respective documentation comments.
2. [FRC Glossary](https://docs.wpilib.org/en/stable/docs/software/frc-glossary.html)
    Definitions of weird terms like "RSL".
3. [Command Based Programming](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html)
    Its like functional programming, but more object oriented.

#### Data
4. [Dashboards](https://docs.wpilib.org/en/stable/docs/software/dashboards/index.html)
    A dashboard is a program used to retrieve and display information about the
    operation of a robot (even during simulation).
5. [Telemetry](https://docs.wpilib.org/en/stable/docs/software/telemetry/index.html)
    Telemetry is recording and sending real-time data for analysis. Analysis
    could be anything from debugging to optimizing.
6. [Vision Processing](https://docs.wpilib.org/en/stable/docs/software/vision-processing/index.html)

#### Hardware
7. [Hardware APIs](https://docs.wpilib.org/en/stable/docs/software/hardware-apis/index.html)
8. [Hardware Basics](https://docs.wpilib.org/en/stable/docs/hardware/hardware-basics/index.html)
9. [Hardware Tutorials](https://docs.wpilib.org/en/stable/docs/hardware/hardware-tutorials/index.html)
10. [Sensors](https://docs.wpilib.org/en/stable/docs/hardware/sensors/index.html)
11. [CAN devices](https://docs.wpilib.org/en/stable/docs/software/can-devices/index.html)
    Computer Access Network. Very complicated so read this as well as the `CAN`
    class in the `org.team4153.core.hardware` package.
12. [Kinematics and Odometry](https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/index.html)
    Kinematics is how objects (the robot) move, ignoring outside forces.
    Odometry is collecting data about how something moved using sensors.


#### Tutorials
13. [Zero-to-Robot](https://docs.wpilib.org/en/stable/docs/zero-to-robot/introduction.html)
    Start of the "Zero to Robot" article from WPI. It shows you most of what
    you need to know to get started programming. Most links have been taken
    from this.
    This is also where you will find the WPI fork of VS Code.
14. [Basic Programming](https://docs.wpilib.org/en/stable/docs/software/basic-programming/index.html)
    A bunch of random things you probably want to know.

#### Misc
15. [Driver Station](https://docs.wpilib.org/en/stable/docs/software/driverstation/index.html)
    The driver station is a piece of software made by FIRST. It handles
    actually running the robot. In 2027, FIRST will release a cross-platform
    driver station, which we will be using.
16. [Robot Simulation](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/introduction.html)
17. [Advanced Controls](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/index.html)
18. [Networking](https://docs.wpilib.org/en/stable/docs/networking/networking-introduction/index.html)
    Communication between the driver station and robot.
19. [Port Forwarding](https://docs.wpilib.org/en/stable/docs/networking/networking-utilities/portforwarding.html)


## Misc
### Impl
Any time you see "impl", that most likely means "implementation". For example,
package org.team4153.core.hardware.impl contains the actually functioning code
for each given hardware type.


### Romi
You will often hear the term "Romi" when dealing with FRC. It is mostly
unrelated to what you will be doing. The Romi Robot Kit is a tool that helps
people learn how to code FRC robots by having pretty much the same API. You can
buy it [here](https://www.pololu.com/product/4022).


### Generics and JVM Types
Generics are the weird `<` and `>` in types. For example, an `ArrayList<A>`
must be distinguished from an `ArrayList<B>`. Keep in mind, the JVM only tracks
that some object is an `ArrayList`, it doesn't care what the generic is. This is
called "erasure". This means the runtime type of `ArrayList<A>` is just
`ArrayList`.

Arrays are different. They are the only data types that have 'generics' during 
runtime.

Here is an example of the types you see, versus the types the JVM sees:

| User         | JVM                   | Extra                                           |
| ------------ | --------------------- | ----------------------------------------------- |
| byte         | B                     |                                                 |
| char         | C                     |                                                 |
| double       | D                     |                                                 |
| float        | F                     |                                                 |
| int          | I                     |                                                 |
| long         | J                     |                                                 |
| short        | S                     |                                                 |
| boolean      | Z                     | booleans sometimes don't exist                  |
| void         | V                     | can only be used in the return types of methods |
| Class        | Lpackage/to/Class;    | the full path to the Class, wrapped in 'L;'     |
| String       | Ljava/lang/String;    |                                                 |
| ArrayList<A> | Ljava/util/ArrayList; | erasure at its finest                           |
| byte[\]      | [B                    | arrays keep their types                         |
| int\[\]\[\]  | [[I                   | arrays of arrays                                |
| String\[\]   | [Ljava/lang/String;   | arrays of classes                               |
| <A>          | Ljava/lang/Object;    | all generics are erased to Object               |


so on and so forth. This sounds dumb, but it makes the JVM run extremely fast,
and with low size for compiled code. For example, instead of a function
described as `int add(int, int)`, it can be described as `add:(II)I`.


### B<Self extends B\<Self\> & A>
Some interfaces will have a signature like this:
```java
interface B<Self extends B<Self> & A>  {...}
```
When implementing this interface, you only need to pass your class into the
generic like this:
```java
class C implements B<C>  {...}
```
HOWEVER! The '&' means that the Self generic must be both B and A, so it forces
you to implement A if you are also implementing B.
```java
class C implements B<C>  {...}      // error: C does not fullfill generic Self extends B<Self> & A
class D implements A, B<D>  {...}
```
This is a way of forcing 'if B then A' in interfaces.
This is particularly useful with the hardware interfaces, as it makes no sense
that a `CAN` class isn't a `Hardware` class either.


### Static Constructors
If a class constructor is what properly sets up class fields and whatnots, then
a static constructor is pretty much the exact same, but for static fields.
Static constructors are declared with `static { ... }` and they only run once,
when the class is accessed for the first time. For example:
```java
public class Conf {
    public static final Config config;
    public static final List<? extends Config> can, input;
    static {
        config = ConfigFactory.load();
        can = config.getConfigList("can");
        // ...
        can.forEach(Hardware::unsafeMakeHardware);
    }
}
```
Keep in mind that it is always better to have strings and primitives be defined
at compiletime. `static final String x = "something";`. This is because strings
and primitives can go through a process called inlining and constant folding.
Basically, if you have `1 + 2`, then why waste time at runtime computing `1 + 2`
when you can just have `3`. Similar things with strings apply.

Inlining doesn't apply when:
- the type is not java.lang.String or any primitive
- the field is not static
- the field is not final
- the field is defined in the static constructor

However, the Java virtual machine is also very smart. If you access `Conf.config`,
then the Java virtual machine can often optimize the
`getstatic org/team4153/core/config/Conf.config` instruction (not how it appears
in actual class files) into the literal address of the field.
As you can expect, these optimizations only apply to final fields. However, 
these **can** be applied to instance fields, but with huge restrictions.
These optimizations only really run in HotSpot JIT C2 compilation, where if a
function is ran enough times, then the JVM will compile it to machine code.
