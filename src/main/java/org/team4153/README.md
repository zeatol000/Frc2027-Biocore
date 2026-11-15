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