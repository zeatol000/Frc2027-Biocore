
## Arrays
Written as "T\[\]" in Java (where 'T' is another type) and represent a list of data of type T.

Given an array 'a', you can access the length of the array using `a.length` (no function call), access a value at index 'i' using `a[i]`, and set a value at index 'i' to 'v' using `a[i] = v`.

## Constant Folding
A part of [compiletime](#Compiletime) where the compiler or interpreter will simplify expressions if both sides are known. E.g., "1 + 2" becomes "3" at [runtime](#Runtime).

## Classes
User defined data types that holds other (often simpler) data. Java revolves it's entire concept around classes and they cannot be described simply here. See [04 Classes](04%20Classes.md).

## Compiletime
The stage of execution before [runtime](#Runtime), where the compiler or interpreter will attempt to find potential issues and optimize the code.

## Data Type
A description of any value that explains how the actual data should be stored and used. Commonly used data types are [int](#Primitive%20Int), [char](#Primitive%20Char), [boolean](#Primitive%20Boolean), [array](#Arrays), and [classes](#Classes).

## Dynamic Typing
A language concept where [types](#Data%20Type) are not known at [compiletime](#Compiletime) and only verified on demand at [runtime](#Runtime). This is the opposite to [static typing](#Static%20Typing).

# NaN
Not A Number. It represents a value that is undefined or unrepresentative in floating point arithmetic, such as 0/0 or the square root of a negative number.

Mathematically, NaN remains true to its name and is not a number, but in computer science, NaN is classified as a number.

NaN also contains some quirks:
- not equal to itself (NaN == NaN --> false).
- any mathematical operation involving NaN almost always results in another NaN.
- to check for NaN, you have to do `Float.isNaN(f)` or `Double.isNaN(d)`.

## Primitive Boolean
Written as "boolean" in Java, these are [primitive types](#Primitive%20Types) that describe the values "true" or "false."

# Primitive Byte
Written as "byte" in Java, these are [primitive types](#Primitive%20Types) that describes an integer value of 1 byte length (8 bits), from -128 to 127.

## Primitive Char
Written as "char" in Java, these are [primitive types](#Primitive%20Types) that describe a single character of a string. In most languages, a char takes up only 1 byte, but Java stores them as 2. Chars are also the only primitive type in Java that do not have negative values (otherwise known as "unsigned").

## Primitive Double
Written as "double" in Java, these are [primitive types](#Primitive%20Types) that describes a [floating point](#Primitive%20Float) number with double precision. These are 8 bytes in length. While they are more precise than normal floats, the added precision means these are far slower to perform math on. 

## Primitive Float
Written as "float" in Java, these are [primitive types](#Primitive%20Types) that describes a number with a decimal place. These are 4 bytes in length. Floating point numbers hold their numbers similarly to scientific notation.

The bit representation starts off with 1 bit for the sign (0 for positive, 1 for negative), followed by 8 bits for the exponent, and 23 bits for the fraction.

Floating point numbers also handle specific edge cases:
- exponent is 0 and fraction is 0: the number is 0, but can be +0 or -0.
- exponent is 0 and fraction is nonzero: the leading bit becomes 0 instead of 1, allowing smaller values near zero. Whatever that means.
- exponent bits are all 1 and fraction is 0: infinity, can be +inf or -inf.
- exponent bits are all 1 and fraction is nonzero: [NaN](#NaN).

## Primitive Int
Written as "int" in Java, these are [primitive types](#Primitive%20Types) that describes an integer value of 4 byte lengths, from -2,147,483,648 to 2,147,483,647.

## Primitive Long
Written as "long" in Java, these are [primitive types](#Primitive%20Types) that describes an integer value of 8 byte lengths, from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807.

## Primitive Short
Written as "short" in Java, these are [primitive types](#Primitive%20Types) that describes an integer value of 2 byte lengths, from -32,768 to 32,767.

## Runtime
The stage of execution in which code is run.

## Static Typing
A language concept where [types](#Data%20Type) are known at [compiletime](#Compiletime) and must be strictly valid before the code even runs. This is the opposite to [dynamic typing](#Dynamic%20Typing).