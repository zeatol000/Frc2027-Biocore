# Package org.team4153.core.mechanisms

NOTE: In 2026 and prior, a mechanism was called a subsystem.

Mechanisms represent anything on a robot that can be controlled via commands.
E.g., an LED, a drivetrain, a climber, etc.

Most mechanisms are purely hardware, but some (such as a drivetrain) also need
a high level api. When this happens, the mechanism is split into `classHW` and
`classAPI`. In the drivetrain example, the drivetrain API is used for receiving
controller inputs and running the propper combinations of methods on the
drivetrain HW class.
