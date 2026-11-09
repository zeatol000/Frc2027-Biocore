 src/main/
 ├─ resources/
 │  └─ application.conf
 ├─ deploy/
 │  └─ README.md
 └─ java/
    └─ org/
       └─ team4153/
          ├─ README.md
          ├─ biocore/
          │  └─ Main.java
          └─ core/
             ├─ RobotContainer.java
             ├─ README.md
             ├─ BaseRobotContainer.java
             ├─ BaseRobot.java
             ├─ config/
             │  ├─ Prelog.java
             │  ├─ Conf.java
             │  ├─ README.md
             │  ├─ Log.java
             │  └─ fromconfig/
             │     ├─ KinematicsUtils.java
             │     ├─ OdometryUtils.java
             │     ├─ Units.java
             │     ├─ Geometry.java
             │     └─ SwerveUtils.java
             ├─ svc/
             ├─ doc/
             ├─ mechanisms/
             │  ├─ README.md
             │  ├─ input/
             │  │  ├─ InputProxy.java
             │  │  ├─ InputSource.java
             │  │  └─ impl/
             │  │     ├─ XboxInputSource.java
             │  │     └─ XboxInputPlayback.java
             │  ├─ led/
             │  │  └─ Color.java
             │  └─ drivetrain/
             │     ├─ DrivetrainHW.java
             │     ├─ DrivetrainAPI.java
             │     ├─ SwerveDrivetrainHW.java
             │     ├─ SwerveModule.java
             │     └─ SwerveDrivetrainAPI.java
             ├─ util/
             │  ├─ Effect.java
             │  ├─ MathUtils.java
             │  └─ CMD.java
             ├─ auto/
             │  └─ recording/
             │     ├─ Recorder.java
             │     ├─ Reader.java
             │     └─ XBoxRecordingElement.java
             └─ hardware/
                ├─ Controller.java
                ├─ MotorFeedback.java
                ├─ Output.java
                ├─ CAN.java
                ├─ Motor.java
                ├─ Hardware.java
                ├─ Input.java
                ├─ Radio.java
                └─ impl/
                   └─ SparkMaxMotor.java

---

# Resources

The resources directory is for files that will be included within the JAR such
as the configuration file.

# Deploy

The deploy directory is for files that are placed as normal files in the robot.

---

# org/team4153/biocore

This is for the year-specific code. For 2027, it is "BioCore"



# org/team4153/core/

This package is for the main library that aims to reduce the whole "1 computer,
1 robot" situation we are in.

## org/team4153/core/RobotContainer.java
public interface RobotContainer

This file contains an interface that defines the pure functionality of the
robot container. But does almost nothing itself.

## org/team4153/core/BaseRobotContainer.java
public class BaseRobotContainer implements RobotContainer

This file contains a very basic implementation of the RobotContainer. It should
be able to handle chassis bots, but not much else.

## org/team4153/core/BaseRobot.java
public class BaseRobot extends org.wpilib.framework.TimedRobot

This is the hardware-communicating robot. It should also be able to function on
chassis bots, but not much else.


# org/team4153/core/svc/

This package, while currently empty, is going to be for calling the supervisor
laptop. This is for stuff like the dashboard and whatnots.

The name 'svc' is actually a reference to ARM assembly, where the kernel call
mnemonic is 'svc' meaning 'supervisor call'.


# org/team4153/core/doc/

This directory isn't a package for code. Instead, it is going to be a big
instruction manual and lookup for people who are new to both FRC and Java.

I was extremely confused while writing this bot as most of the documentation is
subpar, so if I can help a future student, then I will.

The directory will contain an introduction to Java for people who have:
1. never coded before. I may not be able to give a good explanation of Java for
   these people, but one can hope, no?
2. coded in LabVIEW. This is primarily because our team used to use LabVIEW as
   the FRC language, and only two people on the team actually know Java.
   Neither of which are mentors.
3. coded in Python as a lot of people have some understanding of Python. It is
   also quite popular.
4. coded in C#. This is a bit of a weird language to talk about, but consider
   that this is a high school club. A lot of high schoolers, especially those
   who program, have tried to make a game. And when you look up tutorials on
   how to make a game, you get Unity and C#. We also have Game Design classes
   at the school so...

Additionally to this introduction, there will be a boat load of resources on
how FRC and WPILib works. What is 'odometry'? What is a 'command'? so on and so
forth. Those two are bad examples but this will get quite in-depth about WPILib
and will probably take up the majority of this directory.

There will also be resources on the actualy core library itself. Especially
with the increasingly complicated configuration capabilities. A lot of this
will be directing people where things are, how to use them, and why we do
things in this way.

Finally, there will also be just misc information on whatever may be relevant.
For example, most people use Windows, but the Robot will be running on a
Raspberry PI using Linux. While Java and WPILib can mostly hide the OS
differences, there may be a scenario in the future in which someone needs to
know what the heck is going on with Linux.


# org/team4153/core/auto/

This contains utilities for the first 30 seconds of each match; auto.
For now, my only "auto" implementation is the recording method, where one
records the inputs of the controller and writes them to a file. Then when auto
starts, we reread the file and forward the read inputs to the drivetrain and
other.

# org/team4153/core/util/

General utilities of whatever.

## org/team4153/core/util/Effect.java

This is a functional interface of the signature () -> void. Thats it really...

## org/team4153/core/util/MathUtils.java

Generic math utilities like NaN-safe clamping.

## org/team4153/core/util/CMD.java

This provides static methods for creating Commands from the command3 framework
without dedicating a lot of space to the class.

The command3 Command interface needs 3 methods: the actual running method,
which takes a "coroutine" (similar to a low level worker thread from an effect
library such as Cats-Effect) and returns void; the name of the command; and a
set of the mechanisms that this Command depends on.

Lets say you have a lambda that just updates some variable and you want it to
be encased in a Command. Instead of writing an entire class, or using some
builder, you can just run `CMD.of(lambda, "name", Set.of(...))`. Of course,
overloads exist so there is a default name and empty set if unneeded.

The lambda argument is also overloaded. It can either take an Effect or a
Consumer<Coroutine>. In both cases, the lambda invocation is wrapped in a
try...finally block, where the finally block tells the coroutine to yield, as
that is required.


# org/team4153/core/config

The config is where this library shines. Using Typesafe's HOCON config, we
describe the entire bot in one file, the application.conf. Or atleast what we
can.

The issue is that we can't fully describe the bot because of mechanisms and
commands. Unless we use reflection or some incredibly complicated system, it
simply isn't possible to handle triggers.

## org/team4153/core/config/Log.java

This is a custom logger that fills the void of `Commands.print`. It yields
differences between debug, info, warnings, and errors. It also prints the date
time of when the message was created. This (naturally) can be configured though
the config system.

If specified, after printing to the console, it will append to a specified log
file.

## org/team4153/core/config/Prelog.java

This is similar to Log.java, just edited to not use the config. Why you ask?
Simple. When we are running the static initializer of the configuration class,
we run through a lot of code. Much of this code needs to print to the console.
However, if the configuration is not finished, and you access the main Log
class, it will run its own static initializer, which will try to fetch the
uninitialized variables off of the config class. Basically, you end up screwing
over the entire execution.

Anyway, this class exists so you can print to the console while the
configuration is still being initialized. Generally, the user will not use this.

## org/team4153/core/config/Conf.java

This has the actual 'config' field, which is the full config class. In addition,
it holds fields of important and commonly accessed config properties.

As previously stated, the Conf class has a complicated static initializer. One
of the main reasons for this is because it goes through all of the hardware
declared in the config, and initializes classes for them.


# org/team4153/core/config/fromconfig/

This package contains utilities for converting Config classes to WPI constructs
such as Rotation2d or Odometry classes.


# org/team4153/core/hardware/

This package contains a lot of hardware interfaces that describe exactly what
the component is. Additionally, the 'impl' subpackage contains.. well..
implementation classes for the hardware.

The Hardware interface contains a method used by the Conf class's static 
initializer that actually creates the hardware instances. It takes a Config
instance and checks 2 things: the id (int), which will be used to save it
within the hardwareArray static field; and the type (string), which it uses to
delegate the construction to another method.

The hardwareArray field is quite literally just a Hardware\[\]. The reason why
it exists is because of the id field. Put simply, instead of saving strict
CAN ids (which don't exist for all hardware elements) or variable references
(which need annoying refactoring for one change and don't work for the config
file idea), we have the 'id' field for every hardware component. This field
is completely internal to our library but is the best way to handle other
configurations referencing hardware components.

I was asked why not just use a string when showing this to someone. The simple
answer is that strings are really slow. In order to have this same idea with
strings, you'd need a HashMap, which adds a lot of overhead.

After the Hardware interface delegates the Config instance to another interface,
that interface will then continue with its own requirements, often using the
'class' field to figure out what implementation to use.

There is one outlier to the "implementations are in the impl subpackage" idea.
That is for the controller inputs.


# org/team4153/core/mechanisms/

Mechanisms were originally called subsystems before 2027. This package contains
default mechanisms that pretty much every bot will use. For example, the actual
drivetrain or controller input.

Some mechanisms will be split into 2 classes: FooHW and FooAPI. This is the
same idea as the Robot versus RobotContainer. The FooHW, or Foo Hardware, class
handles the low-level api. It deals with the actual WPILib API and some
supervisor calls. The FooAPI, or Foo API, class handles most of what we see.
It deals with defining the actual execution of the mechanism.


Our team also consistently places one giant LED sign on all of our robots. This
acts as a detailed status light so there is also a mechanism for that. (WIP).


# org/team4153/core/mechanisms/input/

Okay the input isn't really a mechanism. I may refactor it to the hardware
package later but that is unimportant.

This contains wrappers over WPILib inputs. Thats it really.

There is also a special class, InputProxy.

## org/team4153/core/mechanisms/input/InputProxy.java
public class InputProxy<T extends InputSource>

The InputProxy is a thread-safe class that stores a single field 'src' of type
'T'. The reason why is because 'src' can be mutated. The idea is that you read
from the input using an InputProxy just by doing `proxy.src.someInput()` this
way, you can safely (and cleanly) swap between two or more different inputs.

The best example use case for this is auto recording. The proxy can make
swapping the input from the controller to the recorder extremely safe.

Additionally, if the raw volatile field makes one uncomfortable, you can use
the synchronized methods 'get' and 'set'. Though the volatile modifier should
be enough.


This also can be used for more than just recording for the auto period. Auto
of any kind can be used to make simple 'scripts' that temporarily take control
of the robot and run some physical function.

