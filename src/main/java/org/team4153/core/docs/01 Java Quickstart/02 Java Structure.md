Chapter Sections:
- [src/main/java](#src/main/java)
- [Top of the File](<#Top of the File>)
- [Class](#Class)
- [Functions](#Functions)
- [Variables](#Variables)
- [Fields](#Fields)
- [Simple Expressions](<#Simple Expressions>)
- [If Else](<#If Else>)
- [While](#While)
- [For](#For)
- [Switch](#Switch)
- [Enhanced Switch](<#Enhanced Switch>)
- [Return](#Return)
- [Break and Continue](<#Break and Continue>)
- [Labelled Break and Continue](<#Labelled Break and Continue>)
- [Ternary Operator](<#Ternary Operator>)


---
## src/main/java
If you're here, you probably had to navigate some ridiculous file path that appears something along the lines of `src/main/java/org/team4153/core/docs/01 Java Quickstart/02 Java Structure.md`. But why?

That last part (01 Java Quickstart/02 Java Structure.md) was purely my decision to make these tutorials easy-ish to navigate.

But that first part? Why not just "src/Main.java"? Well.. thats how most other languages do it. Especially native and interpreted languages. Java on the other hand wanted maximum scalability, so they redid the entire thing.

The "main" directory is the code that gets provided to users as a library, or ran. In the same place, "main" can be replaced with "test". In that case, the code will use the code defined in "main" as a library, and throw random nonsense at it. Some of the nonsense should fail, others shouldn't. If something fails when it shouldn't and vice versa, something critical has broken in your code. This is called unit testing. You write more code to test the code you wrote.

We also separate the code into the language. For the most part, the language separation doesn't really matter. Instead what does matter is the "resources" directory.  This directory is for files that get included into the executable Java code. If "resources" and "java" were the same directory, many build tools would include all of the source code in the executable. Owch.

FRC has an additional directory along with "resources" and "java". Called "deploy". This is for files that are placed onto the RoboRIO/Raspberry Pi/whatever. This is separate from the resources because resources cannot be changed after the code is compiled.

After the "java" comes the package. I'm not sure why, but Java build tools are allergic to just letting people do "src/main/java/Main.java", if Main.java's package is defined.
Seriously, when making this library, I first tried to just do "src/main/java/core/Robot.java" with package "org.team4153.core". Turns out, if you do this, Gradle just freaks out and can't link any of your compiled code correctly! So two directories is a small price to pay for code that actually runs.


## Top of the File
Now we're inside a file. Say "src/main/java/example/Main.java", and we want to make a program that reads an integer from the user and prints "Hello, World!" that number of times.

Lets start by just printing "Hello, World!" once.

The first thing we have is: 
```java
package example;
```
This declares the package (wow shocking). Due to the Gradle linkage issue mentioned above, the package declared here must exactly match the directories from src/main/java to this file.
We can omit the package declaration entirely. In that case, the file would have to be placed at src/main/java/Main.java.


After the package declaration comes imports. These are technically optional. Everything that you import can also be referred directly. Importing just makes it easier and more readable:
```java
import java.util.Scanner;
```
We will use the Scanner class when we want to read input from the command line.

You also can import everything from a package like this:
```java
import java.util.*;
```

Keep in mind that you cannot import packages. Every import statement must be the full package length:
```java
import java.util;   // error: import is a package
import util.Scanner; // error: package "util" not found
```

Import statements must also stay at the top of the file, and cannot be placed anywhere else.

There is also one import statement by default: `java.lang.*`. This imports classes such as "System", "IO", "Integer", and more.

Java has a really big standard library. You can find the Javadoc at https://docs.oracle.com/en/java/javase/25/docs/api/index.html.


## Class
All definitions after the package and imports must be the "class". Classes are something that we will get more into in [04 Classes](<./04 Classes.md>). So for now, just trust me on this.

Before the class, we have a visibility modifier, then "class", and the name of the class:
```java
public class Main {}
```

The "public" visibility modifier means that anyone, anywhere can see and use this class. These modifiers will also be explained in [04 Classes](<./04 Classes.md>).

The name of the class has certain restrictions. For public classes, the name must exactly match the name of the file. Also meaning that there is only one public class in each file.

However, you can have "package private" classes (again, will be explained in [04 Classes](<./04 Classes.md>)) that do not have to comply with the name of the file, also letting there be unlimited classes in a file:
```java
class A {}
class B {}
class C {}
```


## Functions
Now that we are inside of a class, we can define functions.

Functions are laid out similarly to classes. There is a visibility modifier, then the return type, then the name of the class, parameters, and the body:
```java
public static int add(int a, int b) {}
```

For functions that do not return anything, the "void" type is used.

But what about the "static" keyword? Not very shockingly, this will be explained in [04 Classes](<./04 Classes.md>).


There exists one special function in Java, how you actually start running your code: the main function. It returns void and takes a [String](<./03 Types.md#Strings>) [array](<./05 Arrays and Generics.md>), acting as the command line parameters.

Pulling everything together, this is what we have:
```java
package example;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// code here...
	}
}
```


While we're at it, lets print to the console. To do this, we must access the "println" method on the standard output (the console). This is accessed via the field "out" on the class java.lang.System. Confusingly worded, here is the actual code:
```java
System.out.println("Hello, World!");
```

From what we already know:
- System is a class, similar to our "Main", just in package java.lang.
- out is a field. We haven't gone over that yet but it will be explained soon.
- println is a function/method, just like our "main" function.


## Variables
Variables should be a familiar concept. They store data. In contrast with functions, that compute data.

In Java, variables are declared as the type of the data, followed by the name, an equals, then what the variable starts as:
```java
String message = "Hello, World!";
```

We then mutate (update/change) the variable by just having its name, an equals, and the new value:
```java
message = "Goodbye, World!";
```

Similarly, we can get the value of the variable by just typing its name:
```java
System.out.println(message);
```


## Fields
Fields are just variables, but inside of classes instead of functions. They follow similarly to functions, just without the parameters:
```java
public static String message = "Hello, World";
```

This functions pretty much the exact same to having `String message = ...;` in a function, but the variable stays after mutation. Better explained in an example:
```java
public class Main {
	public static int value = 0;

	public static void main(String[] args) {
		doThing();
		doThing();
		System.out.println(value);
	}
	
	public static void doThing() {
		value = value + 1;
	}
}
```
This will print "2". 

This is also exactly what the "out" is in `System.out.println`. We now know that "out" is just a field.


I would also like to note that fields (and variables) can be declared without being "initialized". By just having a semicolon instead of equals:
```java
public static int value;
```

For fields, accessing this value provides the integer 0. You can also freely mutate it.

However, for variables inside functions:
```java
int value;
```
While it is valid, attempting to access it without assigning it a value first is a compiletime error.

## Simple Expressions
The `System.out.println(...);` piece of code from above is an expression. Expressions are what runs. These return either a value or void.

There are many different kinds of expressions, so here is just a quick overview of them:
```java
// numbers:
1 + 2    // addition, 3
4 - 5    // subtraction, -1
9 * 9    // multiplication, 81
50 / 7   // division (ignores remainder), 7
9 % 4    // modulo or the remainder of division, 1
1 | 2    // bitwise OR, 3.   in bits, this is 0001 | 0010 == 0011
6 & 5    // bitwise AND, 4.  in bits, this is 0110 & 0101 == 0100
4 ^ 7    // bitwise XOR, 3.  in bits, this is 0100 ^ 0111 == 0011. NOT EXPONENTATION
4 == 5   // equality check, false
4 <  5   // less than, true
5 <= 5   // less than or equal to, true
3 >  5   // greater than, false
4 >= 5   // greater than or equal to, false
+4       // identity, 4
-4       // negate, -4
~4       // bitwise NOT, -5.   in bits, this is ~...0100 == ...1011
1 << 2   // left shift, 4.     in bits, 0001 << 2 == 0100
-5 >> 2  // signed right shift, -1    in bits, 1011 >> 2 == 1110
-5 >>> 2 // unsigned right shift, 2   in bits, 1011 >>> 2 == 0010

// booleans:
true  && true   // a and b, true
true  && false  // false
false && false  // false
true  || true   // a or b, true
true  || false  // true
false || false  // false
true  ^ true    // a or b but not a and b, false
true  ^ false   // true
false ^ false   // false
!false          // not, true        
```

These operations are known as operators. They only exist for primitive types, as well as one for String, `+`:
```java
"Hello, " + "World!" // "Hello, World!"
```
This technically shouldn't be possible for reasons that will be more explored in [03 Types](<./03 Types.md>). But you can consider it a "favor" from the compiler.

I will also go over some of the bitwise operators more in-depth in [03 Types](<./03 Types.md>)

Method invocations and field access are also considered expressions. Field access is interesting however, as if you don't save the value, it yells at you:
```java
int a = 5; // good
int b = a; // good
a;         // evil!!
```


## If Else
We are now getting to control structures.

If statements check a condition (boolean expression), and if the condition is true, then executes. If it was false, then it continues to the "else" branch.

```java
int a = 5;
int b = a * 2;

if (a == 5) {
	System.out.println("everything looks good");
}

if (b == a * 3) {
	System.out.println("something bad has happened!");
} else {
	System.out.println("everything also looks good here");
}
```

If statements also let you remove the braces if the block is only one expression. The first condition can be rewritten as:
```java
if (a == 5)
	System.out.println("everything looks good");
```

This is also what happens with "else if":
```java
if (b == a * 3) {
	// ...
} else if (b == 10) {
	// ...
} else {
	// ...
}
```


## While
This repeats the block *while* a condition is true.

```java
int a = 5;

while (a > 0) {
	System.out.println(a);
	a = a - 1;
}
```

Just like If blocks, while loops can have the braces ignored if there is one expression.

## For
For loops are glorified while loops. But any variables you declare stay within the scope of the for block.

The chunk from above can be rewritten as:
```java
for (int a = 5; a > 0; a = a - 1) {
	System.out.println(a);
}
```

Just like If blocks, for loops can have the braces ignored if there is one expression.

For loops have a distinct advantage to while loops because the variable declared stays within the for loop. If you try to access `a` outside of the for loop, you get a compilation error.

Additionally, for loops give us 2 new operators that will be explained more in [03 Types](<./03 Types.md>):
```java
a++; // a = a + 1;
a--; // a = a - 1;
```

These don't need to be inside for loops, and can be placed anywhere. But you often see them in the third expression of the loop.
```java
for (int a = 5; a > 0; a--) {
  // ...
}
```

The conditional in the for block also can have all 3 expressions be empty:
```java
int i = 0
for (; i < 10; i++) { block }

for (int j = 0; ; j++) { block }

for (int k = 0; k < 10; ) { block }
```
And yes, this does mean that `for (;;)` is completely valid. It is just a `while (true)`.

To make it weirder, due to the braces being optional with one expression and expressions being able to be empty, you can even have `for(;;);` which will loop forever and do nothing.


## Switch
Switch statements are for when you need to test if a value equals many other values. Think of it like a really long else-if chain, even though it isn't.

The switch statement works by having labels (using the keyword "case") with a value, and a colon. If the expression being switched strictly equals this value, then that part of the code is executed.

The reason why I say labels instead of branches like an if statement is because execution can continue into another case if not told to escape using the "break" keyword. This keyword will be explained later in [Break and Continue](<#Break and Continue>).

```java
int a = 5;

switch (a) {
	case 1:
		System.out.println("a is 1");
		break;
	case 2:
	case 3:
	case 4:
		System.out.println("a is 2, 3, or 4");
		break;
	case 5:
		System.out.println("a is 5");
		break;
	default:
		System.out.println("a is not between 1 and 5");
		break; 
}
```

Having multiple case labels allows people to have multiple conditions for one part of code, but it also means that if you forget the "break" then some issues can occur really easily.

The "default" label is for a value that didn't get a match. Though, this is usually optional.

Now, other than the labels, why is this different than an else-if chain? Switch statements are REALLY FAST.
In an else-if chain, for every single instance, the computer must test the difference of the value from the condition. Then jump to the proper branch, whether or not it was true or false. These few operations are some of the slowest in a CPU.
Switch statements instead use math to calculate the offset of the label, and one jump to that offset. This lets them "test" for infinite conditions in nanoseconds. 
[03 The CPU and Cache](<../04 Hardware and Linux/03 The CPU and Cache.md>) has more details if you want to know more, but that is optional.


## Enhanced Switch
Now take what you have learned from the switch and throw it out of the window.

Enhanced switch statements operate pretty much the same to normal switch statements, but are a bit cleaner to use.

NOTE: this section is missing an aspect of the enhanced switch called pattern matching. This is because pattern matching is really complicated and best explained in [09 Interfaces, Enums, and Records](<./09 Interfaces Enums and Records.md>).

Changes of enhanced switches over the normal switch:
- arrow syntax: uses `->` instead of `:`
- fall-through prevention: no longer needs `break` statements
- multi-cases: multiple conditions can be placed in one `case` in comma-separated arguments
- expressions: enhanced switches can be expressions instead of just control structures
- requires a default case unless if an [enum](<./09 Interfaces Enums and Records.md>) is being switched, and all cases are covered.

Example: basic switch:
```java
int a = 5;

switch (a) {
	case 1 -> System.out.println("a is 1");
	case 2, 3, 4 -> System.out.println("a is 2, 3, or 4");
	case 5 -> System.out.println("a is 5");
	default -> System.out.println("a is not between 1 and 5");
}
```

Example: multiline case:
```java
int a = 5;

switch (a) {
	case 1, 2, 3, 4 -> {
		if (a % 2 == 0)
			System.out.println("a is 2 or 4");
		else
			System.out.println("a is 1 or 3");
	}
	default -> System.out.println("a is not between 1 and 4");
}
```

Example: expression:
```java
int a = 5;

int b = switch (a) {
	case 1, 3, 5, 6, 7 -> a * 2;
	case 2, 4, 8 -> a / 2;
	default -> {
		int x = (a / 5) - 20;
		
		if (x % 2 == 0)
			x = x * 2 + 1;
			
		yield x; // return x as the value of the expression
	}
};
```


## Return
Return functions similarly to `yield` above, but it works in methods that *return* something.

```java
public static int add(int a, int b) {
	return a + b;
}

public static void main(String[] args) {
	int x = 5;
	int y = 20;
	int z = add(x, y);
	
	System.out.println(z); // 25
}
```

For methods that don't return anything (void), then the method will automatically exit when it gets to the bottom. However methods that do return something, there must be a `return` statement always reachable

Return statements can be placed any other control structure. The return statement at the bottom can be removed with special conditions:
- if all branches of an if statement result in a return
- while true loops that have a return value internally (usually in an if statement)

Functions that return nothing can use `return` statements. Their restrictions are practically nothing, just that the return doesn't return anything:
```java
public static void doThing(int x) {
	if (x < 0) return;
	
	System.out.println(x * 2);
}

public static void main(String[] args) {
	doThing(-2); // nothing
	doThing(0); // 0
	doThing(2); // 4
}
```

Here's a tip: returning early is far more readable than having nested if statements. With the doThing function above, we could easily flip the condition and place the println in the true part. But what if we have multiple exit conditions? Just keep adding more and more if statements? How long until you have 8 nested if statements? Believe me, that's happened.


## Break and Continue
We saw these earlier in the switch statement. But what are they? Well they originally got their origin in for loops. They control if the for loop should *return* early or skip this iteration.

Example: break:
```java
for (int i = 0; i < 10; i++) {
	if (i == 5) break;
	
	System.out.println(i);
}
```
This will print the numbers 0 to 4. Notice the similarity to the doThing function from above returning early? You can think of these as practically the same thing but for for loops.

Example: continue:
```java
for (int i = 0; i < 10; i++) {
	if (i == 5) continue;
	
	System.out.println(i);
}
```
This will print the numbers 0 to 4, skip 5, and print 6 to 9. This basically jumps to the end of the for loop, but does not end it.

These two also work in while loops.


## Labelled Break and Continue
What happens to `break` and `continue` if you have nested for and while loops?

Alone, the break and continue will just run on the most nested loop there is. But we can "label" the outer loops and specify them after the break and continue:
```java
outer:
for (int i = 0; i < 10; i++) {
	for (int j = 0; j < 10; j++) {
		if (j == 5) continue;
		
		if (i == 5 && j == 7) break outer;
		
		System.out.println("i = " + i + ", j = " + j);
	}
}
```
This will print i, j pairs. But when j is 5, it skips. However, if i is 5 and j is 7, then the entire loop stops.

Again, these also work in while loops.

Labelled break and continue aren't used often, but are incredibly useful when you do need them.


## Ternary Operator
What do you do if you have a variable's value to be determined by a condition, without having a clunky if..else block?

This is where the ternary operator comes in:
```java
int a = 5;

String b = a >= 0 ? "a is >= 0" : "a is < 0";
```

The structure of a ternary operator is identical to an if statement:
```java
if (cond) { trueBlock } else { falseBlock }

cond ? trueBlock : falseBlock;
```

This also means that nested ternary operators are possible, but they get hard to read quite fast:
```java
int a = 5;
int b = 6;

String c = a >= 0 ? b <= 0 ? "a >= 0 and b <= 0" : "a >= 0 and b > 0" : "a < 0";
```
...ew.

If you have to (and yes there are circumstances where you need nested ternary operators), I recommend using multiple lines and parenthesis:
```java
int a = 5;
int b = 6;

String c = (a >= 0)
	? (b <= 0)
		? "a >= 0 and b <= 0"
		: "a >= 0 and b > 0"
	: "a < 0";
```
Much more readable.


## Using what we know
At the start of this file, I said we would make an application to take an input from the console and print a message a number of times.

Lets do that now!

```java
package example;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// create a new scanner with the console input.
		// this will be explained more in 04 Classes.
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		
		for (int i = 1; i <= n; i++) {
			System.out.println("Hello, World! " + i);
		}
	}
}
```