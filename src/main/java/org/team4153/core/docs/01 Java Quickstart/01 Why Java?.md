Chapter Sections:
- [Packages](#Packages)
- [Runtime versus Compiletime](<#Runtime versus Compiletime>)
- [Java for \_\_\_\_ Devs](<#Java for \_\_\_\_ Devs>)


---
You may be wondering why we chose Java instead of any alternative like C++ or Python.

Well, C++ adds a lot of low-level control and you could theoretically get your robot to run as fast as possible. Though with great power comes greater responsibility. In C++, you must be very careful to make sure that you don't accidentally mess up memory management. Additionally, C++ went through a phase in development that  professionals call "putting whatever the heck they wanted into the language." Basically, if you want something, C++ has it. But that means it has EVERYTHING. This can make it difficult to learn and use, especially for new programmers.

Python sounds great as it is perfect for beginners. Sure, it definitely isn't as fast as many other languages, but it should be good, right? For the most part, yes actually. Although there is one issue: Python makes things *too* easy. This means that it gets incredibly difficult incredibly quick to write code that does specific complicated things.


Java on the other hand gets a good median between the two languages. It is fast enough to run your robot, gives you enough control to get precise control over your robot, and it lets you do extremely specific complicated things easily. That last part will be explained in more detail in section 04, [chapter 02](<../04 Hardware and Linux/02 Memory Management in Java.md>) and [03](<../04 Hardware and Linux/03 The CPU and Cache.md>).

## Packages

The biggest benefit to Java is that it makes it incredibly easy to use other people's code. If you take AP Computer Science with Mr. Didier, you will most likely hear him praising Java for this aspect. And for good reason.

Packages are what makes this so possible. They separate your code into sections and subsections for related code. For example, we keep all of our hardware-related code in a specific package, "hardware." This makes it very clear what something may do if you just know what the package is related to.

Packages also let you have things named the same thing exist. If you have something named "xyz" in package "a.b.c," then it won't conflict with something named "xyz" in package "a.b.c.d," or anywhere else.

Java developers also usually place all of their code into a larger package. This pseudo-library puts its code in the package "org.team4153.core." The "hardware" package from before is actually package "org.team4153.core.hardware."

In addition to packages, what makes Java so good for using other people's code is the static typing and class system. Classes will be explained later. Static typing is explained in the [Java for Python Devs](01B%20Java%20for%20Python%20Devs.md) guide.

## Runtime versus Compiletime 

Getting these two definitions out of the way off the bat, these two words explain what stage the code is of execution.

[Runtime](Glossary.md#Runtime) is... well... the time when your code runs.

[Compiletime](Glossary.md#Compiletime) happens before your code runs. During this stage, whatever is being used to convert your code into executable code (an interpreter or compiler) will attempt to check and optimize what it can. For statically typed languages, it will validate the types of everything and if anything doesn't work, then it errors. Additionally, if a compiler sees "1 + 2," then it will often rewrite the entire expression into just "3" instead of having your code compute 1 + 2 at runtime. This is called [constant folding](Glossary.md#Constant%20Folding).

These two terms will be used quite a lot.

## Java for \_\_\_\_ Devs

This specific file is going to be quite short as it is split into multiple files, each for languages that you are most likely to have experience with.

I am also going to preface this by saying I have minimal experience with any of these languages, these are just the ones that high schoolers most likely to already have some experience with.

Said languages are:
- [LabVIEW](01A%20Java%20for%20LabVIEW%20Devs.md)
- [Python](01B%20Java%20for%20Python%20Devs.md)
- [C#](01C%20Java%20for%20CSharp%20Devs.md)