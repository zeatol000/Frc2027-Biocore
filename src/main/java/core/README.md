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
And instead use companion classes for the given interface. (Eg. use the
`Motors` class for the `Motor` interface).


---
## Subpackages
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

**systems.drivetrain**:
The default implementation of a drivetrain as well as utilities.

**util**:
General utilities.
