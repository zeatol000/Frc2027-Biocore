Chapter Sections:
- [What is a Type?](<#What is a Type?>)
- [Primitive Types](<#Primitive Types>)
- [Primitive Casting](<#Primitive Casting>)
- [Operators](#Operators)
- [Complex Assignment Operators](<#Complex Assignment Operators>)
- [Strings](#Strings)
- [Method Overloading](<#Method Overloading>)
- [Final Variables](<#Final Variables>)
- [Var](#Var)

---
In chapter 1, we mentioned mainly 3 types: `int`, `boolean`, and `String`. But what are they? How can we use them? Are there more?

## What is a Type?
Types are descriptions of data that explain both how we use them and what they are. Take `int` and `String` for example. An `int` is a numerical type of data that represents a whole number. A `String` however, represents any sequence of readable text.

Types are useful because we don't have to check if, say, a parameter is the right type before we run the actual function. It would make no sense to put a `String` where an `int` is expected. 

Languages like Java are statically typed, where all types must be known at compiletime. Other languages like Python are dynamically typed, where types aren't known at compiletime, but verified on demand.


## Primitive Types
Java comes with a few types out of the box, called primitive types. Please note that primitive types are very different from types defined in the Java standard library.

#### int, long, short, byte:
These 4 represent whole numbers. What makes them different from each other is how many bytes they use:
- `int` uses 4 bytes (32 bits) and can represent any integer from -2,147,483,648 to 2,147,483,647.
- `long` uses 8 bytes (64 bits) and can represent any integer from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807.
- `short` uses 2 bytes (16 bits) and can represent any integer from -32,768 to 32,767.
- `byte` uses 1 byte (8 bits) and can represent any integer from -128 to 127.

If you forget the maximum and minimum numbers of any of these, the minimum can be found from `-(2^n)` and the maximum can be found from `(2^n) - 1`, where `n` is the number of bits.

If you are wondering why the negative bound is 1 larger than the positive bound, it comes down to something called two's constant, which is a formula for representing negative numbers in just bits.

Lets take a `byte`. What are the bits for 0, 1, 10, -1, and -10?
- 0    - 0000 0000
- 1    - 0000 0001
- 10  - 0000 1010
- -1   - 1111 1111
- -10 - 1111 0110
You can convert back to human numbers by assigning each bit a number in the base of 2. The furthest right bit is 1, one left is 2, one left is 4, one left is 8, so on until the end. Then you just multiply that constant by the bit value (1 or 0), and add them all together.
But in two's constant, the leftmost one becomes negative of what it would be. So `10000000` is -128. Adding 1 after that brings it closer to 0. With all the bits 1, the value is -1.

#### boolean:
Booleans represent true or false. Logical conditions.

Internally, a boolean is represented using 1 byte, as opposed to 1 bit. This is because bits are really hard to use. So yes, 7 out of 8 bits are wasted.

#### char
These are individual characters of a string. E.g., 'a', '6', '@', but not something like "ab". In Java, a char can be made via the character literal, which is single quotes. String literals are double quotes.

In most native languages, a char is only one byte. But in Java, a char is two bytes. It is also the only unsigned data type in Java. (Meaning it doesn't have negative values, going from 0 to 65535).

#### float and double
These are your decimal numbers. Also known as floating point numbers.

Floats are 4 bytes and are "single precision floating point"

The bit representation starts off with 1 bit for the sign (0 for positive, 1 for negative), followed by 8 bits for the exponent, and 23 bits for the fraction.

Floating point numbers also handle specific edge cases:
- exponent is 0 and fraction is 0: the number is 0, but can be +0 or -0.
- exponent is 0 and fraction is nonzero: the leading bit becomes 0 instead of 1, allowing smaller values near zero. Whatever that means.
- exponent bits are all 1 and fraction is 0: infinity, can be +inf or -inf.
- exponent bits are all 1 and fraction is nonzero: [NaN](<../00 Reference/Glossary#NaN>).

Doubles are 8 bytes and are "double precision floating point". These let you be more precise than floats, letting you store finer data, but because of them being so much more precise, math operations on these become far slower.


### Primitive Literals in Java
Using the primitive types, we can represent most in Java source code easily.
```java
int i1 = 23;     // integers in decimal
int i2 = 0xA;    // hexadecimal, 10
int i3 = 0b1111; // binary, 15
int i4 = 024;    // octal, 20. Yes that is a 0 at the start. Beware of that.

int i5 = 300_000; // underscores can be used to visually separate the digits.
				  // this does not change the value. It is the same as how we
				  // use commas to separate digits. E.g., 300,000,000

long l1 = 23L;   // long literals are any integer literal followed by 'l' or 'L'
long l2 = 0xAL;

char c1 = 'a';   // char literals
char c2 = '\n';  // char escapes, this is a newline
char c3 = '\u03A9'; // unicode escapes, this is the Greek character Omega
char c4 = '\000'; // octal escapes

boolean t = true;
boolean f = false;

double d1 = 0.1; // doubles are the default for decimal numbers
double d2 = 3.0e8; // scientific notation is possible.
double d3 = 4.0d;  // explicitly mark this as a double

float f1 = 0.1f; // just like longs and ints, floats just append 'f' or 'F'
float f2 = 1f; // you also don't need a decimal if its a whole number
```

What about bytes and shorts? Do those get literals? Well no not really. To get bytes and shorts, you need to learn about casting, the next section.


## Primitive Casting
Casting is a way of converting types between each other.

Casting is written as the type inside parenthesis, then the expression to cast:
```java
byte b = (byte) 32;
```

Casting for primitives is based on value, not bits. Given:
```java
byte b = (byte) -1;
int i = (int) b;
```
`i` is not 255, as you might expect. It is -1. If you do want to get the raw bytes of b without keeping the negative, you must do type promotion, which will be described next section.

Type casting works with practically any two primitive types, except for booleans. For some reason, trying to cast to boolean just fails. Instead, use `i != 0` to turn it into a boolean and `b ? 1 : 0` to turn it into an int.

Casting floats to ints results in the decimal place to be chopped off, not rounded:
```java
float f = 2.7f;
int i = (int) f; // 2
```

Also casting has high priority:
```java
// this results in a compiler warning and 0.
int i = (int) 0.4 + 0.6;

// this results in 1.
int j = (int)( 0.4 + 0.6 );
```
But why does that first statement result in a warning and 0?
In truth, you can convert between two primitives without casting, but Java often warns that there is a loss of data that may be unintentional if you do. `int i = 1.5` compiles but produces a warning.
The 0 is because the expression is parsed as `((int) 0.4) + 0.6`, reduce one step and you get `0 + 0.6`, then `0.6`, then an implicit conversion to `int`.


## Operators
In chapter 1, I added a sort of cheat-sheet for operators. This cheat sheet contains most of the operators that do not assign values. I will place it here again for reference:
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

Most operators do what they say on the tin, so I won't go over the simple math ones. However the bitwise operators can be confusing without an explanation.

#### and (&)
The bitwise `and` takes every bit in the left and right sides. If bit N in both sides is 1, then bit N in the result is 1. For example:
```java
	0b0011_0100_0010_1011
  & 0b0101_1010_1110_1110
 == 0b0001_0000_0010_1010
```

#### or (|)
The bitwise `or` takes every bit in the left and right sides. If bit N in either side is 1, then bit N in the result is 1. For example:
```java
	0b0010_0010_1010_0101
  | 0b1001_0100_1000_1101
 == 0b1011_0110_1010_0101
```

#### xor (^)
The bitwise `xor` (exclusive or) takes every bit in the left and right sides. If bit N in one of the two sides, but not both, is 1, then bit N in the result is 1. For example:
```java
	0b1010_1001_0010_1101
  ^ 0b1111_1010_0110_1010
 == 0b0101_0001_0100_0111
```

#### unary not (~)
The unary bitwise `not` takes every bit in the input. If bit N is 1, then bit N in the result is 0, and vice versa. For example:
```java
  ~ 0b1010_1101_0010_1001
 == 0b0101_0010_1101_0110
```

#### Shifts (<<, >>, >>>)
The shifting operators take the bits of the left side and move them left or right by the value in the right side.

The left shift (<<) is easy. It just fills the right with zeros.
```java
	0b1100_0000_1010_0011
 << 2
 == 0b0000_0010_1000_1100
```

The unsigned right shift operates the same as the left shift, but to the right.
```java
	0b1011_0110_0000_0011
>>> 2
 == 0b0010_1101_1000_0000
```

The signed right shift operator is a bit weird since it takes the sign of the left input into consideration. While the unsigned right shift just fills the space with zeros, the signed right shift fills the space with zeros for positive numbers, or ones for negative numbers:
```java
	0b0011_0110_0000_0011
 >> 4
 == 0b0000_0011_0110_0000
 
 
 	0b1011_0110_0000_0011
 >> 4
 == 0b1111_1111_0110_0000
```

### Type Promotion
In Java, every primitive type will try to "upgrade" to other primitive types if the other type has (1) more bits and (2) is a floating point number.

Take the operation `4 + 5L`. This results in the value `9L` because the highest tier type there was `long`. However in the operation `4 + 0.5f` results in the value `4.5f` because `float` is higher than `int`. Finally in `4.5f - 0.5`, you get the value `4.0` because `double` is higher than `float`.


## Complex Assignment Operators
When introducing for loops, I mentioned two new operators, `a++` and `a--` but did not include them in the normal operator list.

This is because those two are assignment operators, they update the value of a variable. Specifically, they are called the *post increment* and *post decrement* operators respectively.

But why "post"? In Java, all assignments return the new value. This means that the following is valid:
```java
int a = 5, b = 3, c = 1, d = -1;

a = b = c = d = 6;
```
This sets all of the variables to 6. Though you really shouldn't do this because you might get burned at the town square. With `a++` and `a--` however, it returns the value of the variable, then mutates it. Hence the name, "post increment/decrement".

In addition to the post increment/decrement operators, we also have *pre increment and decrement* operators, denoted as `++a` and `--a`. These two work in the opposite way as the post operators, mutating the variable, then returning the value.

This means that you can have some... interesting code:
```java
int a = 5;
int b = 5;

int c = add(++a, b--);

// c is 11  (6 + 5)
// b is 4
// a is 6
```


We sometimes need to increment a value by more than one. In this case the mutate-and-assign operators are here to save the day.
```java
int a = 5;

a += 4;
a *= 9;

// a is 81
```

Most of the operators specified above work with the mutate-and-assign system. With some exceptions. Namely, there are 11 mutate-and-assign operators:
- `+=`
- `-=`
- `*=`
- `/=`
- `%=`
- `&=`
- `|=`
- `^=`
- `<<=`
- `>>=`
- `>>>=`
All other operators do not have a mutate-and-assign counterpart. 


## Strings
Among other special types, Strings are treated specially by the Java compiler. For now, we will only address Strings.

String is a class. So I will yet again have to say bear with me until chapter [4 Classes](<./04 Classes>).

In short, a string is too large to hold in the CPU. As such, it must be stored in the RAM. This means that every variable that holds a "String" is actually holding a number that states where the String is in memory. This is known as a pointer.

Such types of data can only have methods run off of them and fields fetched. Strings on the other hand have one operator, `+`. This operator is a shorthand for concatenating a String with some other data type. I won't get into what `+` is a shorthand for until chapter 4 as seeing that now would not help with the confusion.

In addition to the `+` operator, Strings are also one of the few classes that have favors from the compiler. Specifically, string literals. We've been using them this whole time, the double quotes with whatever we want inside of them.


## Method Overloading
You might've noticed when we were talking about the String `+` operator or `System.out.println` that some methods don't care about the type of the input. How is that?

The method actually cares a lot about the type, but we have been running different methods. Lets take the `System.out.println` as an example. There are actually 9 whole `println` definitions. 8 take one of the primitive types, while the last takes any class.

What? Lets use it in an example ourselves.
```java
public static void doThing(float f) {
	System.out.println(f);
}

public static int doThing(int a, int b) {
	return a + b;
}

public static void main(String[] args) {
	int x = 5;
	int y = 6;
	float z = 7f;
	
	doThing(z);
	doThing((float) doThing(x, y));
}
```
This will print 7 and 11.

Keep in mind that overloading only cares about the types and count of parameters. If doThing had an overload that took one int, it would be fine. Same thing with two floats. What doesn't work is a different return type. Java does not like that.


## Final Variables
This technically isn't a type, but I couldn't think of where else to put this section.

What do you do if you have a variable that you absolutely do NOT want to be changed? You can mark that variable with the keyword `final`.
```java
final int a = 5;

a = 6; // error: a is final.
```

This is called immutability. And Java loves this stuff.

For example, if you have a `static final int a = 5`, then run `a + 5`, the Java compiler will take the known value of `a` and substitute that value in at the call site, leaving `5 + 5`. From there, since both sides of the operation are known, Java will perform the math itself and emit `10` at compiletime, instead of getting the value and calculating 10 every single time at runtime.

Additional optimizations occur during runtime for final variables that just doesn't happen with mutable variables.

Now is probably a good time to mention that you can also mutate your parameter inputs. But only to yourself. The caller will experience no change if the parameters are mutated.
```java
public static int sum(int n) {
	int sum = 0;
	
	for (; n > 0; n--) sum += n;
	
	return sum;
}

public static void main(String[] args) {
	int a = 5;
	
	System.out.println(sum(a)); // 15
	System.out.println(a); // still 5
}
```
You also can mark parameters as final and mutation of parameters becomes illegal.

Finally, I would like to mention for future reference that `final` is not limited to variables. It has much more complicated functionality as will be explained in chapter [07 Inheritance](<./07 Inheritance.md>). Chapter 4 will also explain some even more complications to `final` in variables.


## Var
Now we have our final "type", `var`. This isn't actually a type, but instead declares a variable definition with "type evaluation".

Lets say you don't want to type `String s = "..."`. You can instead type `var s = "..."`. This gets especially useful with stupidly long class names.

`var` is also available for primitives.

Use of `var` has restrictions:
- cannot be a parameter
- the value must be immediately accessible. In other words, `var x;` is illegal.

Anyway there isn't much to say about `var`. Its a nice utility but isn't real.