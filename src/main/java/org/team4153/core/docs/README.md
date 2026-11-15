# Documentation

Normally, you don't want to put documentation inside of your code. And you would instead put it in a separate directory, usually in the root of the project.

Our case is different as this "pseudo-library" needs to be able to be copied and pasted into other projects.

Oh by the way, I recommend viewing these files with a Markdown viewer.
I personally recommend [Obsidian](https://obsidian.md), as this guide was made using Obsidian. Though, some IDE's often have the ability to render Markdown.

## Subdirectory Overview
### 00 Reference
The big book of reference material. Heard some random term that you don't know? It is probably here. Trying to find a specific class from WPILib or this library? It is probably here. Need to ....? Its probably here.


### 01 Java Quickstart
This directory contains a quick-start guide for Java. It isn't a complete guide to the language, and it skips over a lot of simpler details. So if this is your first time using Java, you may want to consult a more comprehensive resource.

What these documents aim to do is to map what you may know of other languages onto Java. For example, if you know Python, then this will try to explain the differences between functions and classes in the two languages. Additionally, it will also try to explain some Java-specific concepts such as generics and inheritance.


### 02 WPILib and FRC Introduction
This is a guide to using WPILib and FRC in general. It is not specific to this library, but rather is a general introduction to the concepts and tools used in FRC. In contrast with "01 Java Quickstart", this will be much more comprehensive.

Why you may ask? Simple. As I have been developing this library, I have found that most FRC documentation is quite sparse, and at that, quite bad. Additionally, WPILib 2027 did a complete rewrite of the entire FRC programming environment so documentation is unusually bad.

My goal with this is to make it easy-ish for people to actually get into FRC programming as a whole. It will explain what does what and why. Why are things so complicated and whatever may be needed. Hopefully, you should be able to make a simple robot from the ground up with this guide (by ground up, I mean literally from nothing, not even using the pseudo-library).


### 03 Core Architecture
This is where this library comes in. This section will explain the architecture of this library. It will be less of "how" and more of "why". It will explain the design decisions made and why they were made. It will also explain the various components of the library and how they work together.

If you have read the first 2 sections, then you should be able to understand the problems that this library is trying to solve, understand how we solve them, and (hopefully) be able to improve on my design.


### 04 Hardware and Linux
This section explains hardware, Linux, and also the Java Virtual Machine. It mainly covers potential issues and how we can solve them.

With hardware, it will go over a lot of the restrictions that FRC bots are placed under and what interesting things we can do. What is "CAN?" So on and so forth. As of writing this, we are going to be using a "Raspberry Pi CM5."

For Linux, this will go over a couple of weird Linux shenanigans.

Finally, the Java Virtual Machine. This subsection talks a lot about the Java Virtual Machine and how it can be used to our advantage. What does it like and hate? How does it run? Most importantly, the memory management.