Chapter Secitons:
- [What is a Class?](<#What is a Class?>)
- [What is an Object?](<#What is an Object?>)
- [Constructors](<#Constructors>)
- [Fields](<#Fields>)
- [Methods](#Methods)
- [Static Initializers](<#Static Initializers>)
- [Public, Private, and Package-Private](<#Visibility Modifiers>)
- [Scope Priority](<#Scope Priority>)
- [this](<#this>)
- [Nested Classes](<#Nested Classes>)
- [toString](<#toString>)
- [Null](<#Null>)


---
Finally, we get to classes. This chapter will go over what a `class` is, what objects are, and much more.

## What is a Class?
A class is a type. Or rather, a *class*ification of data with methods/functions attached. It is a concept.

You can think of a class as anything that fits into a category. For example, you are a person, your friends are people, your parents are also people. From this, we can say that all of you are of class `Person`.

But what about your pet? A cat isn't a person, it's a cat. So we can say that your cat is of class `Cat`.

I'm trying to describe an abstract concept as an abstract concept and miserably failing. So just bear with me as I continue onto the next section.


## What is an Object?
An object is an instance of a class. It is something.

In Chapter [3 Types](<./3 Types.md#Strings]), I mentioned how an individual "String" variable is actually a number that explains how to find the actual data of the String. The data is effectively what an object is. The number is instead a reference to it.

Lets take primitive variables as an example:
```java
int a = 5;
int b = a;
a = 0;
```
Here, b is 5 and a is 0 because the value of a got copied into b, then a got set to 0.

Objects on the other hand don't waste time copying an entire object:
```java
String a = "hello";
String b = a;
```
Here, both a and b point to the exact same String in memory. Not the data is the same, the literal object is the same. Think of it like a cup that you have two of. The two cups are equal, but not the exact same one. For reasons that will be explained [later](#Fields), this causes some really weird behavior.

As such, you cannot rely on the `==` operator when comparing objects. You must instead do `.equals()`:
```java
String a = "hi";
String b = new String(a);
String c = a;

a == b; // false, these are two different strings
a.equals(b); // true, the data is identical, so the strings can be treated the same. 
a == c; // true, these are the same string
```

Another thing that we have not addressed yet is the "new". This keyword tells Java that a brand new instance of the class is being made, so memory should be reserved for said instance. After every 'new' comes the parameters for the [constructor](#Constructors), even if no parameters are needed.


## Constructors
Constructors are special functions that run after an object has been allocated, but are needed to set up the actual data.

Lets take an example class:
```java
public class Thing {
	public Thing(int n) {
		System.out.println("Hi!! " + n);
	}
}
```

A constructor is denoted by the same name of the class, with no return type. It also has no modifiers other than the [visibility](<#Visibility Modifiers>). Other than that, it is practically identical to a normal function.

We can then use our `Thing` class like this:
```java
Thing t = new Thing(5);
```
This will print "Hi!! 5" to the console, as a `Thing` object is being allocated, then the constructor is being ran.

Another thing to note is that multiple constructors can be made, just like how we overload methods. If you need to run a constructor within another constructor, you do it by running the method `this` like so:
```java
public Thing(long n) {
	this((int) n);
}
```
The `this` method is actually a special keyword. It will be further explained on the section ["this"](#this).


## Fields
We previously visited static fields in chapter [02 Java Structure](<./02 Java Structure.md>). These are variables that are always accessible.

Non-static fields, also known as instance fields, are where we actually start storing a lot of data. Lets make a new example class:
```java
public class Point {
	public int x;
	public int y;
	
	public Point(int x0, int y0) {
		x = x0;
		y = y0;
	}
}
```

Since these variables aren't static, to access them, we must either be inside an [instance method](#Methods) or access them off of the object via a dot.
```java
Point p = new Point(5, 7);

int a = p.x;
int b = p.y;
p.x = b;
```
Here, we access both variables off of the `p` object and save them into local variables. Then we update `x` off of `p` to be a new value. After this is done, `a` is 5 and `b`, `p.x`, and `p.y` are 7.

Lets use this to explain more of what an object really is.
```java
Point a = new Point(5, 7);
Point b = a;

a.x = 3;
```
Here, it is expected that a is a Point with x = 3 and y = 7. But it might be a surprise that b's x field is also 3.

This is exactly where references are really confusing. Think about it like this: you have two phones. Call your grandma on one phone and tell her something. Then hang up and call her on the other phone. She will (hopefully) remember what you told her. This is because your grandma is the same object, even though two variables (the phones) are referencing her.

This is also where `final` becomes really useful for classes. If we update our Point class to have x and y be final, then we can not mutate the fields and we no longer have the surprise of something being unexpectedly changed.

Now, lets recall: uninitialized variables. In Point's constructor, we set x and y to new values. If we instead empty the body of the constructor, the outcome becomes split depending on whether we have x and y be final or not.

In non-final x and y, we don't get any issues. Instead both x and y are set to 0 by default and we are free to update them as we see fit.

With final x and y, we instead get errors saying that x and y may not be initialized. Since we can't mutate x and y ourselves, we must set them to the initial values in the constructor.


## Methods
In addition to having instance fields, we can also remove static from our methods and get instance methods.

These work pretty much as expected, we just run the function off of the object:
```java
public class Point {
	// what we had before...
	
	public int something() {
		return x + y;
	}
}

Point p = new Point(4, 5);
int a = p.something();
```

This is where a lot of things like the `println` in `System.out.println` or `.equals()` start to make sense.

There isn't really much to say here.


## Static Initializers
Similar to final fields, static final fields will error if they aren't initialized. But how do you initialize them if there needs to be some complex logic? Is there a constructor but for classes instead of objects?

Yep. These are called static initializers and are... quite interesting if you haven't seen them before:
```java
public class Thing {
	public static int x;
	
	static {
		x = 5;
	}
}
```

Static initializers are interesting as they get run the first time that the class is even mentioned.


## Visibility Modifers
Until now, we have been marking everything as `public`, letting anyone and everyone access the classes, fields, and methods.

For fields and methods, there exists 4 visibility modifiers: `public`, `private`, `protected`, and package-private. Protected will not be gone over until [07 Inheritance](<./07 Inheritance.md>).

Private is the new one. It restricts any access to the field or method to that specific class. This is often used to protect sensitive data or methods. Often in Java, people will make all of their fields private and create "getter" and "setter" methods, that get and set the value respectively, usually named something like "getX" and "setX".

Package-private is not a keyword. In fact, to mark something as package-private, you don't add any visibility modifier. What these do is make it so only code within your class can access the field or method.

Classes on the other hand only have 2: `public` and package-private. This is because.. well how are you going to make use of a class that can only be accessed by itself?


## Scope Priority
In the Point constructor, we had the parameters be `x0` and `y0`. This is because of scope priority.

The idea of scope priority is to fix multiple variables named the same thing. If you have two variables named the same thing in the same scope, you get an error. But if they are in different scopes, the one in the scope closest to you is prioritized.

Lets rewrite the Point class and see how it is affected.
```java
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		x = x;
		y = y;
	}
}
```

Well first, if the fields x and y were final, we'd get an error that they are not initialized. But here, we just don't do anything to the fields, and instead mutate the parameters of the constructor to what they already were.

This is where the next section comes in...


## this
`this` is a special keyword that refers to the current object, as if it were on the outside.

We can rewrite the Point class again to use the same names for parameters using `this`:
```java
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
```

Here, `this` forcefully refers to the object and accesses the x and y fields. On the other hand, the x and y parameters are not parts of the object and therefore can not be fetched from `this`.

Oh and guess what? `this` gets more functionality in the next section...


## Nested Classes
Fun fact: classes inside of other classes exist, and are quite normal.

The first way of handling nested classes is by making them static:
```java
public class Utils {
	public static class Point {
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

You can then use `Point` by just accessing it from the `Utils` class:
```java
Utils.Point p = new Utils.Point(3, 4);
```

But what if you remove the `static`? It gets a bit more complicated but stays relatively similar to what we know.
```java
public class Outer {
	public int x;
	
	public Outer(int x) {
		this.x = x;
	}
	
	public void doThing() {
		Inner i = new Inner(x * 2);
		i.log();
		i.y = x;
		i.log();
	}
	
	
	public class Inner {
		public int y;
		
		public Inner(int y) {
			this.y = y;
		}
		
		public void log() {
			System.out.println("x is " + x ", y is " + y);
		}
	}
}
```

Here, we can use the outer class easily:
```java
Outer o = new Outer(50);
o.doThing();
```
This will print "x is 50, y is 100", then "x is 50, y is 50".

But `Inner` is public, and we can therefore use it from outside the `Outer` class.
```java
Outer o = new Outer(50);

Outer.Inner i = o.new Inner(10);

i.log();
```

Wonderfully disgusting, thank you Java.

#### Outer class selection
Lets now do a hypothetical and change the `y` value in Inner to be named `x`. How do we change the `log` method now?

You can refer to the outer class instance by using `Outer.this`, meaning our log method has become:
```java
public void log() {
	System.out.println(
		"outer x is " + Outer.this.x +
		  ", inner x is " + x
	);
}
```


## toString
For reasons that will be explained in [07 Inheritance](<./07 Inheritance.md>), there exists some special methods for classes. One of these is `String toString()`.

The toString method is expected to return the String representation of our class. All classes have it, even if it isn't defined. If you don't define it, then it returns the name of your class, an '@', then the hexadecimal "hash code" of your class. In other words, it returns practically garbage.

Lets take our point and add a toString.
```java
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
```

We can then easily use a Point object in a `println` call.
```java
Point p = new Point(3, 5);

System.out.println(p);
```
This should print "(3, 5)"


## Null
Recall how variables of classes are just numbers that explain how to find the data of the object. The `null` keyword exists as the number 0.

A "null" reference is a reference to an object that doesn't exist. If you try to print null, then you get "null". Not because it has a toString method, but because the println method checks to make sure that the input is not null.

If you instead try to access null.toString manually, you get a "**NullPointerException**". This won't be explained until [06 Exceptions](<./06 Exceptions.md>). But what you need to know right now is that it crashes your entire program if you don't handle it right.

The issue with Java is that `null` fits into every single class. All of this is valid code, at compile time. Not necessarily runtime:
```java
Point p = null;
String s = null;

boolean b = p == s; // true

boolean crash = s.equals(p); // crash
```

You can test if something is null quite easily:
```java
public static doThing(String str) {
	if (str == null) return;
	
	System.out.println(s);
}
```