

Scala Quickbook
=======

[TOC]

What is this about?
-------
This material is consolidated with an intention to provide quick but comprehensive introduction on some of core concepts of the Scala language.

The material draws it's content heavily from the book [Scala for the Impatient Second Edition](https://www.safaribooksonline.com/library/view/scala-for-the/9780134540627/) and an online course from  [coursera.org - ](https://coursera.org) [Functional Programming Principles in Scala](https://www.coursera.org/learn/progfun1)
 
 
## Pre-requisites ##

####**Skillset**

 1. Familiarity with Java 5 or later
 2. Basic knowledge on fundamental datastructures & algorithms
 

####**System Requirements & set-up**

**Java 8 must be installed!**  

The following commands must work on your command line:
    javac -version
    java -version

If these do not render a version, please fix your JAVA_HOME environment variable and your PATH.

Also be sure to install SBT, which is a Scala based build tool.

Get the latest sbt at http://www.scala-sbt.org/download.html

Then, if applicable follow directions at this location:

http://www.scala-sbt.org/0.13/docs/Manual-Installation.html

Once done, you should be able to verify that sbt is working by invoking the following at the command line:

sbt sbtVersion

Finally, install respective Scala plugins for your IDEs or Editors. For Eclipse, checkout scala-ide.org for the update site, you call also visit the Eclipse Marketplace and install Scala-IDE plugin. 

For IntelliJ IDEA visit the plugins section in your IDE and install the 'Scala' plugin (Do not install the IntelliJ 'sbt' plugin as it is incompatible with the Scala plugin)

NOTE: There is no need to download Scala itself, since SBT will handle the Scala set up for you.

Trying the samples out
-------
The instructions given are for IntelliJ Idea, but Eclipse(scala-ide) should be similar.

Scala unlike Java comes with a [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop), this makes testing code snippets easier and a lot faster. 
 
* Clone https://github.com/tigerrepository/Scala_Training
* run `sbt console` from project home folder (*scala-quick-book/*)

This should fire up the REPL, it should take sometime though, as it will download all the necessary dependencies.

####**Conventions**

The following conventions will be used through out this 

**REPL code block**
> **scala> val a = 5**

repl code blocks can be executed directly against the repl command line.

> **filename.scala or filename.sc**

filename with snippet and it's corresponding explanation will be written out, the file will be available, by default under `scala-quick-book/src/main/scala-2.11/` folder.

**Note**: `.sc` files are script files that works more like a repl, you can write individual scripts or class inside the `.sc` will evaluate every block individually. `.scala` files are like `.java` files but with a few differences like

 - It is not mandatory to place .scala files under folder structure that is as same as package names
 -  Filename need not match type name
 -  Any number of type definitions can be palced inside a single scala file

## Scala what? ##

[Scala](https://www.scala-lang.org) is a take on statically typed, fully object-oriented and functional programming language for the JVM, challenging the status quo of Java. The type system of Scala far outweighs Java in terms of features and has the potential to reduce the number of run time errors with its typesafe and concise api.

Scala allows us to use both [functional](https://www.sitepoint.com/functional-programming-pure-functions/) and [OOP](https://en.wikipedia.org/wiki/Object-oriented_programming) methodology at the same, enabling gradual migration to the land of radically less error prone and more concise [pure functions](https://www.sitepoint.com/functional-programming-pure-functions/). 

Scala eco-system has grown considerably over the last couple of years with rich set of tools for [database access](http://slick.lightbend.com/), [unit testing](https://www.scalacheck.org/), [web development](https://www.playframework.com/), [rich functional programming](https://github.com/scalaz/scalaz), [concurrent programming](http://akka.io/) etc., 

This material though, will focus only on the core library of the language *=)*.

![Enough Ceremony](https://cdn.meme.am/cache/instances/folder197/500x/64387197.jpg)

Everything is a type
--------------------
Everything in scala is a type, scala does not have a notion of primitives and static, which sets it apart from Java. This fundamental notion is key for making scala fully object-oriented language.

    scala> 8
    res1: Int = 8

the above code resolves to an `Int` an type, which is in stark contrast to java, where we have a primitive `int`, any expression that is executed in the repl is assigned a variable name if not done. Here `res1` is assigned an object of type `Int` with the value of `8`.

Type Inference
--------------

Scala has a beautiful feature of type inference. Scala compiler will try to infer type based on the given context.

 
```scala
    scala> val answer = 8 + 5 * 2
    answer: Int = 18 
    
    scala> val answer = 8 + 5 * 2
    answer: Int = 18
    
    scala>  var int = 10
    int: Int = 10
    
    scala> int = "String"
    <console>:12: error: type mismatch;
         found   : String("String")
         required: Int
         int = "String"
```
Here we see scala compiler has inferred the types and has prevented us from assigning object of incompatible types to the variables. 

While we can still provide the types ourselves, type inference saves us a lot of key strokes, where appropriate.

```scala
    scala>  var int: Int = 10
    int: Int = 10
    
```

The Predef object
-----------------
Scala compiler automatically imports the `Predef` object, which defines or imports several useful types, objects, and functions. 

Scala defines wrappers for java primitives of int, float, double etc., where as it uses type alias for Java String, the definition of which is available in the Predef class.
```scala
    type String = java.lang.String
```

Predef also defines a lot of implicits for type conversion and type enrichment. For example

```scala
    scala> 1.to(10)
    res0: scala.collection.immutable.Range.Inclusive = Range 1 to 10
```
to() is not a method of scala.Int but scala.RichInt, the Predef class automatically imports the Implicits that converts the `int -> Int -> RichInt`

Note: Implicits shall be discussed in detail in the later part of the material.

val vs var
----------
Scala has a concise syntax for variable declaration, `val and var`.

val defines  immutable values, analogous to final keyword of Java, while var defines mutable values. 

syntax for declaring a variable in scala is

    {val or var} variableName: optionalType = value  
the optional type can be filled by the compiler type inference where appropriate. 

```scala
    scala> val a = 10
    a: Int = 10
    
    scala> a = 5
    <console>:12: error: reassignment to val
           a = 5
             ^
```

`val` is defaulted in most places in scala, which is similar to most functional programming languages, which has support only for immutable values and datastructures. Additionally scala compiler provides freebies in some places like automatically providing getter's when the constructor parameters are val.

```scala
    scala> var a = 10
    a: Int = 10
    
    scala> a = 5
    a: Int = 5
```

`var` on the other hand is used only if absolutely necessary in scala. var will let you reassign values to the variable.

Some involved examples of val vs var below

```scala
    scala> :paste
    // Entering paste mode (ctrl-D to finish)
    
    class A(val i: Int)
    
    // Exiting paste mode, now interpreting.
    
    defined class A
    
    scala> val a = new A(10)
    a: A = A@33d02cb2
    
    scala> a.i
    res1: Int = 10
 -


    scala> :paste
    // Entering paste mode (ctrl-D to finish)
    
    class B(i: Int)
    
    // Exiting paste mode, now interpreting.
    
    defined class B
    
    scala> val b = new B(10)
    b: B = B@4d73f0de
    
    scala> b.i
    <console>:13: error: value i is not a member of B
           b.i
         ^
```
When the constructor parameters are declared as `val` (we will look at constructors later ) scala compiler provides public accesser for those attributes.

Note: `scala> :paste` mode lets you type(or paste) multiple lines of code as a single block in repl, might come handy for class definitions in repl. After typing press ctrl+d to compile, execute and exit.

val and var are evaluated in place, meaning when scala interpreter encounters them `val a = 5 + 6` the expression is executed and value of a is assigned to be 11. To defer the evaluation until used we could use lazy version of it.

```scala
    scala> lazy val n = {println("Evaluated"); 5}
    n: Int = <lazy>
    
    scala> n
    Evaluated
    res0: Int = 5
    
    scala> n
    res1: Int = 5
```

This, at times gives opportunity for proper initialization, say a value depends on some heavy code block, so when accessed it may throw error due to some uninitialized dependencies, but when accessed, say a bit later, will run as expected if the dependencies are initialized properly.

```scala
    scala> var divisor = 0
    divisor: Int = 0
    
    
    scala> lazy val quotient = 40 / divisor
    quotient: Int = <lazy>
    
    
    scala> quotient
    java.lang.ArithmeticException: / by zero
      at .quotient$lzycompute(<console>:12)
      at .quotient(<console>:12)
      ... 37 elided
    
    
    scala> divisor = 1
    divisor: Int = 1
    
    scala> quotient
    res4: Int = 40
```

Operator overloading
--------------------
Scala allows operator overloading, which give developers opportunity to define natural, concise, but sometimes terse methods and functions.

```scala
    scala> val n: BigInt = 1000000
    n: BigInt = 1000000
    
    scala> n * n * n
    res12: scala.math.BigInt = 1000000000000000000
```
here we see the type of the augmented `BigInt` class of java having a method `*` that is very intuitive and natural than  `n.multiply(n).multiply(n)`  which is much verbose. 

Infix notation
--------------

Infix notations helps us write code that looks like plain english, 

`(1 + 3) should be (4)` is a valid expression with ScalaCheck library. Infis notation is the key to writing method calls like 
```scala
    scala> n * n * n
```
which is inferred by the compiler as `n.*(n).*(n)`, the infix notation can be used with methods that expects only one parameter.

Note: where appropriate the `. and () are optional` in scala. 

**Right Associative Colon**
Right associative colon are the special form of infix notation. Whenever a method name is suffixed with `:` the argument should precede the object name.

```scala
    class RightAssociativeColon {
      def +: (i: Int) = i + 10
    }
```
   *RightAssociativeColon.scala*

below code shows the variants of the usage of method `+:` 

```scala
    scala> val foo = new RightAssociativeColon
    foo: RightAssociativeColon = RightAssociativeColon@70303d16
    
    scala> foo.+:(5)
    res2: Int = 15
    
    scala> foo +: 5
    <console>:13: error: value +: is not a member of Int
    	   foo +: 5
    		   ^
    
    scala> 5 +: foo
    res3: Int = 15
```

this is a special notation that is used in many of the core collection libraries, for example you can prepend an element to a list with `5 +: List(1, 2)` 

Functions vs Methods
--------------------

Scala has both functions and methods. Functions are first class citizen in scala.

Methods are bound to a context of a type or object, but functions are not bound to a type and can be passed around. Java static methods and anonymous inner classes comes close to functions, but due to the lack of better choice, are still accessed only with the name of the type. Some of the basic method definition that are tied the context of the type BasicMethods is shown below. To access these methods you need an instance of the type BasicMethods
```scala
    class BasicMethods extends App {
      def add(x: Int, y: Int) = x + y
      
      def numberState(x: Int) =
        if (x < 10) "Less than 10"
        else if(x > 10) "Greater that 10"
        else "x is 10"
    }
```
*BasicMethods.scala*

**Note**: App is an special super class in scala, that makes you write executable code easier, without having to write the dreaded `static main()` method. The body of the class is executed when this executed. 

**Note**: package objects are special object types in scala, that can be leveraged to park context less functions. 

```scala
    package scala
    
    /** The package object `scala.math` contains methods for performing basic
      * numeric operations such as elementary exponential, logarithmic, root and
      * trigonometric functions.
      */
    package object math {
    	.....
    }
```
Evaluation
----------
Scala method/function evaluation follows inplace evaluation of the parameters. 

```scala
    scala> 1 to (5 + 5)
    res3: scala.collection.immutable.Range.Inclusive = Range 1 to 10
```
The parameters are evaluated in place before the method is executed. This is called **call-by-value**.

but this could be problematic in cases like below

```scala
    scala> def loop: Int = loop
    <console>:11: warning: method loop does nothing other than call itself recursively
           def loop: Int = loop
                           ^
    loop: Int
    
    scala> def call(x: Int, y: Int) = x
    call: (x: Int, y: Int)Int
    
    scala> call(1, loop)
```
the above method call never terminates as `loop` is an infinite loop and the process has to be killed to exit. The loop is evaluated to obtain a value before call() is executed.

To navigate this tricky situation scala has call-by-name

```scala
    scala> def call(x: Int, y: => Int) = x
    call: (x: Int, y: => Int)Int
    
    scala> call(1, loop)
    res1: Int = 1 
```
`y: => Int` is an short hand for `y: () => Int` which is a function that needs to evaluated to obtain the value, and since `call()` never need to evaluate `y` , evident from it's definition, `call()` terminates in the case of **call-by-name**.

**Note**: call-by-name is used wherever lazy evaluation of the parameter may be required. 

val vs def
----------

val and def has the same distinction as call-by-value and call-by-name, val is evaluated in place but def is evaluated when invoked.

```scala
    scala> def loop: Int = loop
    <console>:11: warning: method loop does nothing other than call itself recursively
           def loop: Int = loop
                           ^
    loop: Int
    
    scala> def k = loop
    k: Int
    
    scala> val y = loop
    --repl died 
```
syntax for def 

```scala
    def methodName(argName: agrType, ....): returnType = expression
```

in scala, a function always returns a value, hence body of the function is always an expression. but in cases where a method or function is side-effecting*, it still return a value but this time it is an object of type `Unit`, which is an equivalent of Java `void`.

\*A function  is said to have **side-effect**, if apart from executing an expression using the passed parameters, it modifies the parameters, prints to the console, persists in the database etc, all of which has the possibility of failure and may not return the same value every time it is called with the same set of parameters. A function without side-effect is called a **pure function***.

Scala Strings
-------------
Scala used `java.lang.String` with an type alias `String`, the scala string though, is implicitly an subclass of `Seq[T]` which is also the super class of `List[T]`, thus we can use all Seq operations like `map( ), flatMap( )` etc., apart from all the regular properties of a Java string, String in scala is sprinkled with lot of useful functionalities, below are the examples.

```scala
    object Strings extends App {
    
      /*
      Java String.format( )
       */
      val str = String.format("this is a %s", "test")
    
      println(str)
    
      val scalaStr = "This is a %s".format("testz")
    
      println(scalaStr)
    
      /*
      String.format( ) with positional and type based formatting
      %1$s: %1 represent first element of the format(Object... parameter)
      and $s represents that it is an String argument
       */
      println("Because you are %1$s, %2$s %3$s times a man".format("Three", "Twice", "Once"))
    
    
    
    
      import java.time._
    
      /*
      more involved example, where date format is used in String.format( ),
      refer https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html for more details
      on the API
      */
      println("We will be eating lunch on %1$tB the %1$te in the year %1$tY".format(LocalDate.now))
    
    
    
      /*
      String interpolation is one of the handy feature of Scala String, that helps the drop the
      ceremonies of Java String. Interpolated string starts witha an s"" or f"", where f"" for
      formatting like date formatting we have seen above.
      s"${a} interpolated String"
      ${a} represents the variable name that in the scope of the string.
       */
      val a = "This is a "
      println(s"${a} interpolated String")
    
    
    
      val ticketCost = 350
      val movieName = "Dr. Strange"
    
      //for formatting while interpolating - f interpolator
      println(f"$movieName%s tickets would cost us $$$ticketCost%1.2f")
    
    
    
      val percentage = 120
    
      println(
        f"""
           |$movieName%s tickets would cost us $$$ticketCost%1.2f
           |That is a $percentage%% increase because everyone loves MCU
         """.stripMargin)
    
    
    
    
      val multiLineString =
        """
          |This is a multi line String, that carries the formatting with it
          |second line here tests that,
          |     Third line reinforces that
          |The default delimiter is used here as a parameter to stripMargin, though it is optional
          |you can replace it with any character
        """.stripMargin('|')
    
      println(multiLineString)
    
    
    
      val message = "We are meeting them at 12:00PM"
      /*
      plain regex without the java boilerplates of escape characters,
      multiline string saves a lot of keystrokes
       */
      val regex = """(\s|[0-9])?[0-9]:[0-5][0-9]\s*(AM|PM)""".r
    
      println(regex.findAllIn(message).toList)
    
    }
```

Recursion and Tailrecursion
---------------------------
Recursion, as we know it, helps us solve large problem by identifying and solving the smallest piece, and applying it recursively and finally combining the result.

```scala
    object TailRecursion extends App{
    
      /**
        * Below are the recursive evaluation steps  for the above function
        * gcd(14, 21)
        * if(21 == 0) 14 else gcd(21, 14 % 21)
        * if(false) 14 else gcd(21, 14 % 21)
        * gcd(21, 14 % 21)
        * gcd(21, 14)
        * if(14 == 0) 21 else gcd(14, 21 % 14)
        * gcd(14, 7)
        * ....
        */
        
      def gcd(a: Int, b: Int): Int =
        if(b == 0) a else gcd(b, a % b)
    
      
    /**
        * factorial(4)
        * if(4 == 0) 1 else 4 * factorial(3)
        * 4 * factorial(3)
        * 4 * 3 * factorial(2)
        * 4 * 3 * 2 * factorial(1)
        * 4 * 3 * 2 * 1 * factorial(0)
        * 4 * 3 * 2 * 1 * 1
        */
        
      def factorial(x: Int): Int =
      if(x == 0) 1 else x * factorial(x - 1)
     
      def factorial(x: Double): Double = {
        @tailrec
        def loop(acc: Double, y: Double): Double =
          if(y == 0) acc
          else loop(acc * y, y -1)
        loop(1, x)
      }
    }
```
*TailRecursion.scala*

Recursion is the fundamental "*conceptual*" control structure of functional programming languages, as they closely match mathematical evaluations. However powerful they may be recursion may lead to stack overflow when applied for huge calculation as every consecutive step in the recursion add an entry into the stack.

           /**
            * factorial(4)
            * if(4 == 0) 1 else 4 * factorial(3)
            * 4 * factorial(3)
            * 4 * 3 * factorial(2)
            * 4 * 3 * 2 * factorial(1)
            * 4 * 3 * 2 * 1 * factorial(0)
            * 4 * 3 * 2 * 1 * 1
            */
The above evaluation for `factorial(Int)` creates a new entry in the stack for every step, as the jvm has to keep track of the calculation to be able to reduce it to the final result. If the recursion are written in such a way that, it need not keep the intermittent results in the stack then the recursion will occupy single stack space for the entire calculation, however big it might be, this is called `Tail Recursion`.

```scala
    def factorial(x: Double): Double = {
            @tailrec
            def loop(acc: Double, y: Double): Double =
              if(y == 0) acc
              else loop(acc * y, y -1)
            loop(1, x)
          }
        }
```
the factorial calculation here is implemented as an tail recursive function, with the help of an accumulator, that carries the intermittent result along. Scala additionally provides `@tailrec` annotation that adds a compile time check to ensure if the given function definition is tail recursive. 

**Note**: Scala cannot type infer recursive function, hence return type must be specified. 

Higher order function
--------------------
Higher order function is the corner stone of functional programming, higher order functions are functions that take another function as a parameter or function that returns another function. Higher order function lets you pass around behavior like data, which can then be executed. Java anonymous inner classes that can be passed around as parameters or returned like values.

```scala
    scala> def cube(a: Int) = a * a * a
    cube: (a: Int)Int
    
    scala> def sumInts(a: Int, b: Int): Int = if(a > b) 0 else a + sumInts(a + 1, b)
    sumInts: (a: Int, b: Int)Int
    
    scala> def sumCubes(a: Int, b: Int): Int = if(a > b) 0 else cube(a) + sumCubes(a + 1, b)
    sumCubes: (a: Int, b: Int)Int
```
both `sumInts and sumCubes` are special cases of `[n(a, b)Σ f(n)]` where f is the function applied for each value of n.

   

 
```scala
    scala> def sumOf(f: Int => Int, a: Int, b: Int): Int = if(a > b) 0 else f(a) + sumOf(f, a + 1, b)
    sumOf: (f: Int => Int, a: Int, b: Int)Int
```
sumOf takes an anonymous function `f` of type `Int => Int`, function that takes an Int (Left hand side) and returns an Int (Right hand side) along with the bounds `a and b`.

```scala
    scala> def sumOfCube(a: Int, b: Int) = sumOf(cube, a, b)
    sumOfCube: (a: Int, b: Int)Int
```

Now the specific functions can be implemented by passing the right functions, sumOfCube is implemented by passing `cube` in the place of `f`. `cube` matches the required type of `Int => Int`. 

```scala
    scala> def id(a: Int) = a
    id: (a: Int)Int
    
    scala> def sumOfInts(a: Int, b: Int) = sumOf(id, a, b)
    sumOfInts: (a: Int, b: Int)Int
    
    scala> sumOfInts(1, 5)
    res1: Int = 15
    
    scala> sumOfCube(1, 4)
    res2: Int = 100
```

Scala provides a nice syntactic sugar for passing around anonymous functions called `Lambda expressions`. 

```scala
    scala> def sumOfInts(a: Int, b: Int) = sumOf((x: Int) => x, a, b)
    sumOfInts: (a: Int, b: Int)Int
    
    scala> def sumOfCube(a: Int, b: Int) = sumOf(x => x * x * x, a, b)
    sumOfCube: (a: Int, b: Int)Int

```
previously we saw how to declare the type of the expected function, here we how the function itself can be passed as a argument without the intermediate variable. 

`sumOf((x: Int) => x, a, b)` shows the function definition `(x: Int) => x`, where (x: Int) is the argument `x` of type `Int` and it return back the same x.

The next function `sumOfCube` adds more sugar to the syntax `x => x * x * x` by dropping the verbose braces around the argument and leaves the argument type inference to the compiler.

for multiple arguments braces must be added to the argument and for multi line body curly braces must be added 

    (x, y) => {
    	if(x > y) 
    	x 
    	else 
    	y
    }

**Note**: **return** statement though available, is not used in scala, the last line of an expression is always the value that is being returned. In the case based on the condition x or y will be returned with out the explicit need for the return statement. Return statement when used will be done so, will be done to break the control flows in iterations.

####**Currying**
A function with multiple parameters that can be applied to wide scenarios based on based on fewer parameters than all can be curried. 

A curried function, returns another function that can be applied to actual arguments when required.

As an example the `sumOf` can be rewritten in a more concise form during application, that will seem very natural when we get used to it.
 
```scala
    def sum(f: Int => Int): (Int, Int) => Int = {
        def sumF(a: Int, b: Int): Int = {
          if (a > b) 0
          else f(a) + sumF(a + 1, b)
        }
        sumF
      }
```
Scala provides a syntactic sugar for the curried functions

```scala
    def sum(f: Int => Int)(a: Int, b: Int): Int = {
            if (a > b) 0
            else f(a) + sum(f)(a + 1, b)
    }
```

here `sum` takes a function `Int => Int` and returns a function `(Int, Int) => Int`. The scala compiler has interpreted that as `sum: (f: Int => Int)(Int, Int) => Int`. That is sum is a function that takes 3 parameters in curried form `f: ( parameter set 1) ... (parameter set n)` and returns an Int. 

the function can be applied as a total function or partial function as below.

*Partial function* 

```scala
    scala> def sumCube = sum(x => x * x * x)
    sumCube: (Int, Int) => Int
    
    scala> sumCube(1, 3)
    res5: Int = 36
```
*Totalfunction*

```scala
    scala> sum(x => x * x * x)(1, 3)
    res4: Int = 36
```

Here is an example Array function that uses currying

```scala
      val a = Array("Hello", "World")
      val b = Array("hello", "world")
    
      //def corresponds[B](that: Seq[B])(p: (A, B) => Boolean): Boolean
      a.corresponds(b)(_.equalsIgnoreCase(_))  
```
Let's look at an example that will let us model a function as sequence of statements with no parameters or return statement `( ) => Unit`

```scala
     def runInThread(block: () => Unit) {
    
        new Thread {
    
          override def run() {
            block()
          }
    
        }.start()
    
      }

    //that can be used as 
    runInThread { () => println("Hi"); Thread.sleep(10000); println("Bye") }

we could also use call-by-name instead to pass the function, to get rid of ( )

    def runInThread(block: => Unit) {
   
        new Thread {
    
          override def run() {
            block
          }
    
        }.start()
    
      }
 

    runInThread {
		println("Hi"); Thread.sleep(10000) 
		println("Bye")
    }  
```  

 using currying and the statement execution blocks we could design language keyword like feature

```scala
    def until(condition: => Boolean)(block: => Unit) {
       if (!condition) {
          block
          until(condition)(block)
       }
    }

    var x = 10
    until(x == 0) {
      x -= 1
      println(x)
    }
```
Classes and Types
-----------------
Scala class definitions closely resembles that of Java except for a few features, that actually sets it apart.

* classes are not mandated to be placed inside folder structure reflecting the package name
* a *.scala* file can have as many traits, classes, objects, abstract classes, only requirement is they must be unique in their namespace(package)
* public is the default access modifier for classes and constructors
* Scala traits which are similar to Java interfaces, but can have concrete methods
* Scala allows multiple inhertiance with the help of traits, the [Diamond Problem](https://en.wikipedia.org/wiki/Multiple_inheritance) which forced Java to not allow it, is resolved using a technique called [Linearization](http://jim-mcbeath.blogspot.in/2009/08/scala-class-linearization.html)
*  Scala does not have the notion of `static`, instead has `object` which are singleton instances, that can mimic the functionalities static provides.

Let's have a look at the design of a type representation of Rational numbers

 
```scala
    class Rational(x: Int, y: Int) {
    
      /*
      unlike java body of scala class can contain statements
      that gets initialized when the object is instantiated.
      require( ) is a function from Predef class that is executed,
      everytime a new object is instantiated.
       */
      require(y != 0, "Cannot divide by zero")
    
      /*
      anything that is supposed to be private to the instance,
      is marked with private keyword. In addition to private scala has protected
      access modifier
       */
      private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    
      /*
      primary constructors are placed with the class name, secondary constructor
      can be defined with this keyword
       */
      def this(x: Int) = this(x, 1)
    
      private val g = gcd(x, y)
    
      /*
      we can replace def numer = x / g with def numer = x / gcd(x, y)
      saving the extras variable, or, we can change the numer and denom to val, so they are computed only once
      no matter what we do the clients knowledge about the Type remains intact
      This is called data abstraction
       */
      def numer = x / g
    
      def denom = y / g
    
      /*
      operator overloading to mimic mathematical operations of a Rational number,
      in programming language.
       */
      def +(two: Rational) = {
        /*
        Immutablity: any mutable operation will return a new object,
        leaving the original instance untouched. This is great to write
        concurrent programs that can be easily reasoned
         */
        new Rational(
          numer * two.denom + two.numer * denom,
          denom * two.denom
        )
      }
    
      /*
      DRY(Don't Repeat Yourself): creating abstractions for common functionalities
       */
      def neg = new Rational(-x, y)
      
      def -(two: Rational) = this + two.neg
    
      def <(two: Rational) = numer * two.denom < two.numer * denom
    
      def max(two: Rational) = if (this < two) two else this
    
      def mkString = s"$numer / $denom"
    
      override def toString = mkString
    }
```
*Rational.scala*

####**Type Hierarchy**

![Scala Type Hierarchy](http://docs.scala-lang.org/resources/images/classhierarchy.img_assist_custom.png)

Just as in Java `java.lang.Object` is supertype of all types. In addition, all types implicitly inherit type `Any`, which is now the super type of all Scala types. 

Then it branches into `AnyVal` and `AnyRef`, AnyVal being super type of all value types like Int, Boolean, Float, Double etc. and AnyRef for all other types. There is however one key difference here, scala has a powerful type variance mechanism and type called `Nothing` which is a subtype of all types of `AnyRef` supports this as we will see later. 

####**Trait**
Trait is similar to Java interfaces but with a twist, trait lets you define concrete methods that can be inherited, and allows classes to extend multiple trait, which is knows as mixin, that you can mixin properties from multiple traits into a concrete type. 

Mixin is resolved using the process of Linearization, explained below ![Type Linearization](http://image.slidesharecdn.com/scala-types-of-types-lambda-days-2014-short-140226180059-phpapp02/95/scala-types-of-types-lambda-days-15-638.jpg?cb=1415031035)

The traits are listed, reversed, expanded, right unique traits are kept and common super classes are added during linerarization.

```scala
    abstract class Animal {
      def speak
    }
    
    trait WaggingTail {
      def startTail
      def stopTail
    }
    
    trait FourLeggedAnimal {
      def walk
      def run
    }

    class Dog extends Animal with WaggingTail with FourLeggedAnimal {
      // implementation code here ...
    }
```
**Abstract and concrete fields in traits**

In a trait, define a field with an initial value to make it concrete, otherwise give it no initial value to make it abstract:

```scala
    trait PizzaTrait {
      var numToppings: Int     // abstract
      val maxNumToppings = 10  // concrete
    }
```
In the class that extends the trait, you’ll need to define the values for the abstract fields, or make the class abstract. The Pizza class demonstrates how to override the numToppings field:

```scala
    class Pizza extends PizzaTrait {
      var numToppings = 0      // override not needed
    }
    
    trait PizzaTrait {
      var numToppings: Int     // abstract
      val maxNumToppings = 10  // concrete
    }
```
To implement a simple mixin, define the methods you want in your trait, then add the trait to your class using extends or with. In the following example we define a Tail trait:

```scala
    trait Tail {
      def wagTail { println("tail is wagging") }
    }
```
This trait can be used with an abstract Pet class to create a Dog:

```scala
    abstract class Pet (var name: String) {
      def speak  // abstract
      def ownerIsHome { println("excited") }
    }
    
    class Dog (name: String) extends Pet (name) with Tail {
      def speak { println("woof") }
    }
```
A Dog can now use the methods defined by both the abstract Pet class, as well as the Tail trait:

```scala
    object Test extends App {
      val zeus = new Dog("Zeus")
      zeus.ownerIsHome
      zeus.wagTail
      zeus.speak
    }
```

**Limiting which classes can use a trait**

You can limit a trait so it can only be added to classes which extend a specific subclass. To do this, use the “trait [TraitName] extends [SubclassName]” syntax. For instance, in the following example the Starship and WarpCore both extend the common superclass StarfleetComponent, so the WarpCore trait can be mixed into the Starship class:

```scala
    class StarfleetComponent
    trait WarpCore extends StarfleetComponent
    class Starship extends StarfleetComponent with WarpCore
```

However, in the following example, the Warbird can’t extend the WarpCore trait because Warbird and WarpCore don’t share the same superclass:

```scala
    class StarfleetComponent
    trait WarpCore extends StarfleetComponent
    class RomulanStuff
    class Warbird extends RomulanStuff with WarpCore   // won't compile

```
A trait inheriting a class is not a common occurrence, and in general the following approach is preferred.

You can mark your traits so they can only be used by subclasses of a certain type. To do this, begin your trait with the “this: LimitingType =>” statement, as shown here:

```scala
    trait MyTrait {
      this: LimitingType =>
      // more code here ...
    For instance, to make sure a WarpCore can only be used in a Starship, mark the WarpCore trait like this:
    
    trait WarpCore {
      this: Starship =>
    }
```
Now this code will work:

```scala
    class Starship
    class Enterprise extends Starship with WarpCore
```
But other attempts like this will fail:

```scala
    class RomulanShip
    class Warbird extends RomulanShip with WarpCore   // this won't compile

```
Adding a trait to an object instance
This next example shows that you can add a trait to an instance of a class (an object):
```scala
    class DavidBanner
    
    trait Angry {
      println("You won't like me ...")
    }
    
    object Test extends App {
      val hulk = new DavidBanner with Angry
    }
```
####**Singleton Objects**

You create singleton using the keyword `object` instead of class keyword. Since you can't instantiate a singleton object, you can't pass parameters to the primary constructor.

All other properties of a class holds true for `object` as well. We have seen plenty of examples of `object` already.

####**Companion Object**

A companion object is an object with the same name as a class or trait and is defined in the same source file as the associated file or trait. A companion object differs from other objects as it has access rights to the class/trait that other objects do not. In particular it can access methods and fields that are private in the class/trait. 

One of the most common uses of a companion object is to define factory methods for class, just like Java's static factory methods. A second common use-case for companion objects is to create extractors for the class. 

```scala
    class MyString(val jString:String) {
      private var extraData = ""
      override def toString = jString+extraData
    }
    object MyString {
      def apply(base:String, extras:String) = {
        val s = new MyString(base)
        s.extraData = extras
        s
      }
      def apply(base:String) = new MyString(base)
    }
```
Now that the companion object is defined, we could use them to create an object of MyString type as 

```scala
    MyString("hello"," world")
    MyString("hello")
```
As you can see, this looks a lot like a function call. It is because in scala `apply( )`is a method with a special meaning by convention, any type with apply method can be invoked with the syntactic sugar `ObjectName( .. )` or `ObjectName.apply( .. )`.

####**The Function class**

In scala functions are indeed objects, for every lamda expression you define, compiler automatically creates an anonymous instance of class/type `FunctionN` where N -> 1 to 22, yes scala, as of now limits you in defining a function by 22 parameters.

Here is a look at how are the function classes defined in scala, in relaity the Function class has a lot more features than just `apply( ) ` method.

```scala
    /*
    Functions are classes with apply method and has a syntactic sugar of an lambda expression
    Here Functions 1 represents a functions that takes one parameter.
    It is the expansion of lambda expression A => B
     */
    trait Function1[A, B] {
      def apply(a: A): B
    }
    
    object Functions {
      def l = (x: Int) => x * x
    
    
      /*
      Here the l is theoritically expanded to AnonFun      as below
       */
      class AnonFun extends Function1[Int, Int] {
        override def apply(a: Int): Int = a * a
      }

      //or we could even expeanded to an Anonymous function, just like Java
    
      val f = new Function1[Int, Int] {
        override def apply(a: Int): Int = a * a
      }

	//the functions could then be applied as 
      l.apply(5)
      f.apply(5)
     
     //or 
     
	  l(5) 
      f(5)
    }
```
*Functions.scala*

Functions are objects of Function type, but methods are not, if methods were objects of Function, then apply would be an instance of an Function object with an apply method which would be an instanc of an another Function and so on. 

####**Persistent datastructure**

Persistent datastructure preserves the previous version of the data even when it is modified. 

Let's consider implementing sets as binary trees. IntSet in the following example is an persistent datastructure and below is the pictorial representation of the same.

<a href="http://imgur.com/peZ09mg"><img src="http://i.imgur.com/peZ09mg.png" title="source: imgur.com" /></a>



The invariant that we want to maintain is that for each node, the nodes on its right hand side all have integer values that are higher than the node, and the nodes on its left all have values that are less. Here's how it works. We'd create the new node 3, with two empty subtrees, which would be the left subtree of a new node 5, with an empty right hand subtree, and finally, the tree would be a new tree, with the node 7, and the same right hand side tree as before.

<a href="http://imgur.com/RzBsBif"><img src="http://i.imgur.com/RzBsBif.png" title="source: imgur.com" /></a>

So really, we end up with two trees - the old one, and the new one. The two trees share the subtree on the right hand side, but they differ on the left tree.

Let us define a base trait IntSet with tow methods, one for adding a new element, one to check if a given element exists.

```scala
abstract class IntSet {
  def incl(x:Int): IntSet
  def contains(x: Int): Boolean
} 
```
*IntSet.scala*

`Empty` and `NonEmpty` both extend the base class IntSet - this implies that the types Empty and NonEmpty conform to the type IntSet. In other words, an object of type Empty or NonEmpty can be used where an object of type IntSet is required.

Empty is a value more than a type where all the behavior remains constant, hence can be defined as an singleton using the `object` keyword.

```scala
object Empty extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false

  override def toString: String = "."
}
```
*IntSet.scala*

NonEmpty can be represented by a class that takes an element (the integer stored in the node), and a left and a right subtree (in this case, an IntSet).

```scala
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet =
    if(x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if(x < elem) left contains x
    else if(x > elem) right contains x
    else true

  override def toString: String = s"{ $left $elem $right }"
}
```
*IntSet.scala*

`def incl(x: Int): IntSet` is represented as an recursive function, that checks if the given int is greater than or not starting from the given element and tries to add it either to the right or the left side of the element,

`def contains(x: Int): Boolean` works like a binary search. 

####**Case classes**
Case classes are special type of classes in scala that comes in very handy while defining Algebric data types in scala. They define the objects of the algebric data types which then requires operations to transform them to another types.

Let us consider a simple case class

```scala
case class Person(lastname: String, firstname: String, birthYear: Int)
```
When you declare a case class the Scala compiler does the following for you:

* Creates a class and its companion object.
*  Implements the apply method that you can use as a factory. This lets you create instances of the class without the new keyword. E.g.:

```scala
val p = Person("Lacava", "Alessandro", 1976)

// instead if the slightly more verbose:
val p = new Person("Lacava", "Alessandro", 1976)
```
* Prefixes all arguments, in the parameter list, with val. This means the class is immutable, hence you get the accessors but no mutators. E.g.:

```scala
val lastname = p.lastname
// the following won't compile:
p.lastname = "Brown"
```
* Adds natural implementations of hashCode, equals and toString. Since == in Scala always delegates to equals, this means that case class instances are always compared structurally. E.g.:

```scala
val p_1 = Person("Brown", "John", 1969)
val p_2 = Person("Lacava", "Alessandro", 1976)

p == p_1 // false
p == p_2 // true
```
* Generates a copy method to your class to create other instances starting from another one and keeping some arguments the same. E.g.: Create another instance keeping the lastname and changing firstname and birthYear:

```scala
// the result is: Person(Lacava,Michele,1972), my brother :)
val p_3 = p.copy(firstname = "Michele", birthYear = 1972)
```
* Probably, most importantly, since the compiler implements the unapply method, a case class supports pattern matching. This is especially important when you define an Algebraic Data Type (ADT). 

```scala
sealed trait Maybe[+T]
case class Value[T](value: T) extends Maybe[T]
case object NoValue extends Maybe[Nothing]

val v: Maybe[Int] = Value(42)
val v_1: Maybe[Int] = NoValue

def logValue[T](value: Maybe[T]): Unit = value match {
  case Value(v) => println(s"We have a value here: $v")
  case NoValue => println("I'm sorry, no value")
}

logValue(v) // prints We have a value here: 42
logValue(v_1) // prints I'm sorry, no value
```
As you probably already know, when your class has no argument you use a case object instead of a case class with an empty parameter list.

* Apart from being used in pattern matching the unapply method lets you deconstruct a case class to extract it’s fields, both during pattern matching and as a simple expression to extract some of its fields. E.g.:

```scala
val Person(lastname, _, _) = p

println(lastname) // prints Lacava
```
**Not so common knowledge about case classes**

* What if you need a function that, given your case class arguments as parameters, creates an instance of the class? Here’s how you can do it by partially applying apply (no pun intended :)):
```scala
val personCreator: (String, String, Int) => Person = Person.apply _

// the result is: Person(Brown,John,1969)
personCreator("Brown", "John", 1969)
```
* What if you want your function, from the previous point, to be curried? Enters the curried method:
```scala
val curriedPerson: String => String => Int => Person = Person.curried

val lacavaBuilder: String => Int => Person = curriedPerson("Lacava")

val me = lacavaBuilder("Alessandro")(1976)
val myBrother = lacavaBuilder("Michele")(1972)
```
* What about obtaining a function that accepts a tuple whose arity is equal to the number of the case class arguments, and produces an instance of the class? Well, there’s the tupled method for that:

```scala
val tupledPerson: ((String, String, Int)) => Person = Person.tupled

val meAsTuple: (String, String, Int) = ("Lacava", "Alessandro", 1976)

val meAsPersonAgain: Person = tupledPerson(meAsTuple)
```
* You could also need a function that, given an instance of your class as input, produces an Option[TupleN[A1, A2, ..., AN]] as output, where N is the number of the case class arguments and A1, A2, ..., AN are their types. E.g.:

```scala
val toOptionOfTuple: Person => Option[(String, String, Int)] = Person.unapply _

val x: Option[(String, String, Int)] = toOptionOfTuple(p) // Some((Lacava,Alessandro,1976))
```
The curried and tupled methods are inherited from AbstractFunctionN which is extended by the autogenerated companion object. N is the number of the case class formal parameters. Note that, of course, if N = 1 you won’t get curried and tupled because they wouldn’t make sense for just one parameter!

**Defining a case class using the curried form**

There’s another less-known way of defining a case class, e.g.:
```scala
case class Keyword(text: String)(source: String, foo: Int)
```
The formal parameters in the first parameter section of a case class (just text in this case) are called elements; they are treated specially. All the goodies you get when you define a case class (accessors, pattern matching support, copy method, …) only apply to the first section. For example you don’t have an accessor for source since the compiler didn’t implicitly prefix it with val, like it did for text instead. 

```scala
val k1 = Keyword("restaurant")("storage", 1)

// won't compile
val source = k1.source
```
You can solve the accessor problem by prefixing the parameters with val. 
```scala
case class Keyword(text: String)(val source: String, val foo: Int)
```
Anyway you still won’t get all the other case class features. For instance, you cannot use the copy method by specifying only the source parameter. You have to specify, at least, all the parameters of the sections successive to the first. E.g.:
```scala
// won't compile
val k2 = k1.copy()(source = "web")

// will compile
val k3 = k1.copy()(source = "web", foo = 1)
```
Finally, the companion object of a case class defined in such a way won’t extend AbstractFunctionN, so the tupled and curried methods are not available.

At this point the natural question that may arise is: “Why on earth should I want to define a case class in such a way?” Apparently there are cases when it could be a reasonable choice. For example suppose that, for your business model, two instances of Keyword are to be considered equal iff they have the same text field. Well, in such a case by defining the case class using the curried form you’ll get what you want. E.g.:
```scala
val k1 = Keyword("restaurant")("storage", 1)
val k2 = Keyword("restaurant")("web", 2)

k1 == k2 // true!
```
That’s because also the equals implementation, you get for free for case classes, only applies to the first parameter section, so only to text in this case. I’m not saying here that this is always the best choice but it could be of help in certain situations.

In fact, you could define your case class as usual and override equals on your own. However overriding equals is not very trivial. Indeed, before doing that I recommend you read the chapter 30 of Programming in Scala: A Comprehensive Step-by-Step Guide, 2nd Edition - Odersky, Spoon, Venners. Its title is Object Equality and it’s just 25 pages long!


####**Type Variance**
As stated several times before scala has pretty strong type system, that can radically reduce the errors and help us write code that delegates lot of error checking to the compiler, enabling us to write fewer test to verify behavior. Let us see that with an example

Let us use IntSet to see how type variance works. Let's define a function that check if all the elements of a IntSet is positive.

```scala
    def assertAllPos(set: IntSet): IntSet = ???
```
if the set is all positive returns the set itself, else it will throw an exception and if it is an empty set it should return an empty set, if you provide an non empty set, it will should return an non empty set. With an simple type system like S, now in this example below are the boundry cases we have to check

* When you pass an empty set, it should only return only an empty set
* when you pass an non empty set, it should return only an non empty set
* when you pass an non empty set, we need check if all the elements are positive.

trying to redefine our function to let compiler does some testing for us

```scala
def assertAllPos[S <: IntSet](set: S): S = ???
```
now the method parameter is definesd as `[S <: IntSet](set: S): S`  `<:`  indicates `S` is bounded to derived types, that is, the type `S` that extends `IntSet` and the return type must be the same type that is passed or a type that derives from `S`.

so we can rest assured that the below statement always now returns an `NonEmpty` with the help of compiler.

```scala
assertAllPos(new NonEmpty(1, Empty, Empty))
```
More [examples](http://blog.kamkor.me/Covariance-And-Contravariance-In-Scala/) here.

Scala let's you define [co-variance and contra-variance](https://msdn.microsoft.com/en-in/library/dd799517%28v=vs.110%29.aspx). 

**Covariance**
if `A <: B` will `List[A] <: List[B]`, if this holds true, then the List is covariant, example `List[NonEmpty] <: List[IntSet]`, actually in scala List are covariant, which is indicated by List[+T]. 

In Java however Arrays are not covariant, let us see why that is a problem.

we expect `NonEmpty[] <: IntSet[]`

```java
NonEmpty[] n = new NonEmpty(1, new Empty(), new Empty());
IntSet[] i = n
i[0] = new Empty()
NonEmpty s = n[0]
```
now we can get back an Empty while we expect it to be NonEmpty as per our original declarion (now you may also see why mutability is also evil), however java takes an additonal pain of validating it during runtime to prevent it from happening throwing `ArrayStoreException`.

In scala Array is not covariant, it is Invariant, it takes only the same type as it was declared

```scala
val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))
val b: Array[IntSet] = ???
b(0) = Empty
```

Covariance in scala is governed by [Liskov Substitution principle](http://stackoverflow.com/questions/56860/what-is-the-liskov-substitution-principle).

Given `A <: B`, the relationship between `C[A]` and `C[B]` can be
Covariant: `C[A] <: C[B] = C[+A]`
Contravariant:  `C[A] >: C[B] = C[-A]`
Invariant: `C[A]` has no relationship to `C[B]`

Let's look at an example proof
```scala
type K = IntSet => NonEmpty
type L = NonEmpty => IntSet
```

in the above example `K <: L` as we L can take an `NonEmpty` set and return an `IntSet` and K can also take an `NonEmpty` set and return an `IntSet`, so K and L are covariant

In General for `A1 => B1` and `A2 => B2`, and if  `A2 <: A1` and `B1 <: B2`, then `A1 => B1 <: A2 => B2`, this is the general rule of variance for functions.

That leads us to a general rule that Functions are actually contravariant in the argument types and covariant in the result types

```scala
trait Funtion[-T , +U] {
  def apply(x: T): U
}
```
can we make functions covariant or contravariant as we please, no it has some rules.
1. Scala compiler will check Contravarint is used only in the argument
2. Covariant only as result
3. Invariant can appear anywhere

Pattern Matching
----------------
Pattern Matching is similar to switch case in Java, but very powerful. Pattern matching allows you type test a given value and also deconstruct and navigate datastructures, particularly recursive datastructures with a bit of a help from compiler.

Pattern matching are partial function, a total function is fuction that is defined for all values in a domain for example with total function of `Int => String` you can pass any integer and get back a String, partial functions are fuction that are defined only for a few values of the domain for example partial function of `Int => String`, is not applicable for all the values of Int and if you try to invoke with undefined values you get back an error.

**Note**: Partial functions are different from Partially applied function(remember currying?).

`case` classes have built in support for pattern matching, pattern matching on a type required an implementation of `unapply( )` method in a class, and `case` class does that for you and are best fit for pattern matching.

**Note**: If you are pattern matching on any other types, other than supported library types and user defined `case` classes, unapply( ) method must be overridden. 

Let us take an example of type Expression, which is an abstraction for any expression that can be evaluated example num(x) will return an x, sum(x, y) will return x + y, etc., This could be defined as the below type hierarchy 
```scala
trait Expr
case class Num(x: Int) extends Expr
case class Sum(x: Expr, y: Expr) extends Expr
```
*Expr.scala*

The evaluation can now be placed in any object, here we place inside an companion object of `Expr`

```scala
object Expr {
  def eval(e: Expr): Int = e match {
    case Num(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
    case Product(e1, e2) => eval(e1) * eval(e2)
    case Sum(Num(x), Sum(_, _)) => x 
    case x => -99
    //all the code we write below are this dead code, as we match anything to -99 
    case _ => -1 //In this case we will not get receive error, as _ matches aything in this context
  }
}
```
*Expr.scala*


```scala
e match {
	case x => result
}
```
is an instance of type `scala.PartialFunction`

There are three kinds of patterns that can be used for matching 
**Constructor pattern**: that matches any constructor with a given pattern `Sum(e1, e2)` or  `Sum(Num(x), Sum(_, _))`
**Variable pattern**: Just binds the instance to an variable `case x => x.toString` which can then be used in the accompanying expression
**Constant pattern**: matches to the given constant value

**Note**: If multiple pattern matches a given expression, only the first one is evaluated

The pattern matching can be placed inside the base `trait Expr` to. There is a tradeoff between going with class Hierarchy and using pattern matching. If we more often create new classes as a part of extensibility then hierarchy will make more sense if we are creating new methods, which may not be a local change lets assume where we want to implement `show(x: Expr): String` which will change to every class in the hierarchy.

###**Collections**

The Scala collections hierarchy is very rich (both deep and wide), and understanding how it’s organized can be helpful when choosing a collection to solve a problem.

At a high level, Scala’s collection classes begin with the Traversable and Iterable traits, and extend into the three main categories of sequences (Seq), sets (Set), and maps (Map).

![A high-level view of the Scala collections](http://alvinalexander.com/sites/default/files/2-scala-collections-hierarchy.png)
*A high-level view of the Scala collections*

The Traversable trait lets you traverse an entire collection, and its Scaladoc states that it “implements the behavior common to all collections in terms of a foreach method,” which lets you traverse the collection repeatedly.

The Iterable trait defines an iterator, which lets you loop through a collection’s elements one at a time, but when using an iterator, the collection can be traversed only once, because each element is consumed during the iteration process.

####**Sequences**

Digging a little deeper into the sequence hierarchy, Scala contains a large number of sequences, many of which are shown below

![A portion of the Scala sequence hierarchy](http://alvinalexander.com/sites/default/files/3-scala-sequences.png)
*A portion of the Scala sequence hierarchy*

####**List**
Scala has 2 variants of List, immutable and mutable list, mutable list is similar to Java list, that can be mutated inplace, immutable `List` are special though and will be referred by default in this material as just `List`.

Scala List is an functional-recursive datastructure, and is an implementation of `Cons( :: )` list

<a href="http://imgur.com/TJwzi3K"><img src="http://i.imgur.com/TJwzi3K.png" title="source: imgur.com" /></a>
*Cons List*

The `Cons` list is shown in the diagram above, the list has a head and a tail, head is the value of the top most element and tail is a `Cons` list or `Nil` - which is an singleton instant of an Empty element.

This can be represented as 

```scala
trait List[T] {
  def isEmpty: scala.Boolean
  def head: T
  def tail: List[T]
}
```
The implementation of Nil 
```scala
class Nil[T] extends List[T] {
  override def isEmpty: scala.Boolean = true

  override def head: T = throw new NoSuchElementException("Nil.head")

  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")
}
```

Now the definition of Cons is very simple 
```scala
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: scala.Boolean = false
}
```
**Note**: We only have to implement isEmpty as `val head, tail` can be a legal implementation of the `def head and tail`. val definition of classes can override methods or extend abstract methods, difference between val and def is when it is initialized or evaluated.

Now this is an Algebric Data Type and the we could effectively use Pattern Matching to effectively implement various functions .

further we could provide function like factories for constructing instances of Cons and Nil with companion object
```scala
object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, new Nil))
  def apply[T]: List[T] = new Nil[T]
}
```

####Utilities of List
List as said earlier is the fundamental datastructure of many functional languages. Scala however has a very broad hierarchy  under the umbrella type Seq[T] of which List[T] which are functionally similar.

we will look at some of the functions and uses of List with some examples below

* Defining List using the factory provided by List companion object
```scala
scala> val fruits: List[String] = List("apples", "oranges", "pineapple", "pear")
fruits: List[String] = List(apples, oranges, pineapple, pear)


scala> val nums: List[Int] = List(1, 2, 3)
nums: List[Int] = List(1, 2, 3)

scala> val diags: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
diags: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))

scala> val empty: List[Nothing] = List()
empty: List[Nothing] = List()

```

* defining List using the `Cons(::)` operator
```scala
scala> val consList = "apples" :: ("oranges" :: ("pears" :: Nil))
consList: List[String] = List(apples, oranges, pears)
```
the `Cons(::)` always associate to the right implicitly, as we know from the RightAssociativeColon and hence the braces can be dropped for conciseness

```scala
scala> val simpleConsList = 1 :: 2 :: 3 :: Nil
simpleConsList: List[Int] = List(1, 2, 3)
```

* Simple list operations
```scala
scala> simpleConsList.head
res0: Int = 1

scala> simpleConsList.tail
res1: List[Int] = List(2, 3)

scala> simpleConsList.isEmpty
res2: Boolean = false
```
* Pattern matching on List

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

simpleConsList match {
  case 1 :: 2 :: xs => xs
  case 1 :: Nil => println("Single element list")
  case List() => println("matches empty list")
  case List(x) => println("matched x :: Nil")

}

// Exiting paste mode, now interpreting.

res3: Any = List(3)
```
* Insertion Sort
The insertion sort algoritm is an recursive algorithm, that compares consecutive elements and rearranges them as per required sort order, below is a pictorial representation of insertion sort of Int
<a href="http://imgur.com/fj3qsi7"><img src="http://i.imgur.com/fj3qsi7.gif" title="source: imgur.com" /></a>
*Insertion Sort*

using the recursive property of head and tail of the List this can be implemented in a very consice way

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

def iSort(x: List[Int]): List[Int] = x match {
  case List() => List()
  case y :: ys => insert(y, iSort(ys))
}

def insert(x: Int, xs: List[Int]) : List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if(x <= y) x :: xs else y :: insert(x, ys)
}

// Exiting paste mode, now interpreting.

iSort: (x: List[Int])List[Int]
insert: (x: Int, xs: List[Int])List[Int]

```
```scala
scala> iSort(List(7, 9, 2, 3))
res4: List[Int] = List(2, 3, 7, 9)
```

* Other functions

```scala
scala> val listX  = List(1, 2, 3, 45, 5, 6, 7, 8, 9)
listX: List[Int] = List(1, 2, 3, 45, 5, 6, 7, 8, 9)

scala> val listY = List(3, 24 ,5 ,6 ,8)
listY: List[Int] = List(3, 24, 5, 6, 8)

scala> listX.length
res5: Int = 9

scala> listX.take(2)
res6: List[Int] = List(1, 2)

scala> listX.drop(2)
res7: List[Int] = List(3, 45, 5, 6, 7, 8, 9)

scala> listX.head
res8: Int = 1

scala> listX.tail
res9: List[Int] = List(2, 3, 45, 5, 6, 7, 8, 9)

scala> listX.reverse
res10: List[Int] = List(9, 8, 7, 6, 5, 45, 3, 2, 1)

scala> listX ++ listY //list concatenation
res11: List[Int] = List(1, 2, 3, 45, 5, 6, 7, 8, 9, 3, 24, 5, 6, 8)

//functional equivalent of mutation, return a new list though
scala> listX updated (2, 5)
res12: List[Int] = List(1, 2, 5, 45, 5, 6, 7, 8, 9)

scala> val listX  = List(1, 2, 3, 45, 5, 6, 7, 8, 9)
listX: List[Int] = List(1, 2, 3, 45, 5, 6, 7, 8, 9)

scala> listX last
res0: Int = 9

```

let us try and implement a function `last` which is a functional equivalent of `List.last()`

the function is implemented as an tail recursive function with pattern matching, that seeks out till the last element and returns that element

```scala
def last[T](x: List[T]): T = x match {
  case List() => throw new Error("Empty list")
  case List(x) => x
  case y :: ys => last(ys)
} 

scala> last(listX)
res4: Int = 9

```

init() is a function that leaves all the elements except the last element and can be implemented as

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

def init[T](x: List[T]): List[T] =  x match {
  case List() => throw new Error("Empty list")
  case List(x) => Nil
  case y :: ys => List(y) ++ init(ys)
}

init(listX)

// Exiting paste mode, now interpreting.

init: [T](x: List[T])List[T]
res7: List[Int] = List(1, 2, 3, 45, 5, 6, 7, 8)
```

similarly concat can be implemented as 

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

def concat[T](xs: List[T],
              ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

concat(listX, listY)

// Exiting paste mode, now interpreting.

concat: [T](xs: List[T], ys: List[T])List[T]
res9: List[Int] = List(1, 2, 3, 45, 5, 6, 7, 8, 9, 11, 12, 13, 14)

```

Scala also provide Tuple,  a collection of multiple types and there are multiple variants of Tuple like pair - collection of two elements may be of different type, Triple - collection of three elements, Quadraple - collection of five element etc.,

A list when `split()` creates a `Pair` of two `List`

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

val pair = listX splitAt (listX.length / 2)
val (left, right) = pair
pair._1

// Exiting paste mode, now interpreting.

pair: (List[Int], List[Int]) = (List(1, 2, 3, 45),List(5, 6, 7, 8, 9))
left: List[Int] = List(1, 2, 3, 45)
right: List[Int] = List(5, 6, 7, 8, 9)
res10: List[Int] = List(1, 2, 3, 45)
```

this property can be used with pattern matching to perform some nimble tasks

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length / 2
  if(n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, ys1) => ys1
        case (xs1, Nil) => xs1
        case (x :: xs1, y :: ys1) =>
          if(ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}

msort(listX)

// Exiting paste mode, now interpreting.

msort: [T](xs: List[T])(implicit ord: Ordering[T])List[T]
res11: List[Int] = List(1, 2, 3, 5, 6, 7, 8, 9, 45)
```
merge sort divides the list in half and orders them and finally merges them all recursively to efficiently sort a `List`

In the above function we create a tuple of two List `xs` and `ys` and pattern match on the tuple. 

`def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T]` is a generic sort function, that can take any type T and sorts them, the sorting mechanism however is encoded in `(implicit ord: Ordering[T])` the parameter is implicit meaning compiler will try to find an instance of Ordering[T] in appicable scope and binds to the method call automatically. 

```scala
scala> val strList = List("This", "is", "a", "List", "of", "String")
strList: List[String] = List(This, is, a, List, of, String)

scala> msort(strList)(Ordering.String)
res12: List[String] = List(List, String, This, a, is, of)

```
here the implicit parameter is explicitly passed, incases of an arbitrary type `T` whose instance of `Ordering[T]` that may not be available in any scope, it must be passed in as argument to the function invocation as above.

####**Higher order list functions**

List or for that matter provides a variety of higer order functions, that let's us work with collection in declarative style. It empowers us to move away from using imperative style iterators to perform opertaion or transform collections. We will look at some of the higher order list functions below

**map( )**

<a href="http://imgur.com/eK2mwz1"><img src="http://i.imgur.com/eK2mwz1.png" title="source: imgur.com" /></a>
*map( )*

The map operation takes a collection of values, performs some transformation on each element, and creates a new collection containing those new elements. As shown above function (n: Int) => n * n will square each element on a `List[Int]`

```scala 
scala> val f = (n: Int) => n * n
f: Int => Int = $$Lambda$1497/436841191@30f53f2

scala> listY map f
res13: List[Int] = List(121, 144, 169, 196)

```
**reduce( )**

The reduce operation takes all the elements in a collection and combines them in some way to produce a single value. 

<a href="http://imgur.com/Qi0HeLi"><img src="http://i.imgur.com/Qi0HeLi.png" title="source: imgur.com" /></a>
*reduce( )*

```scala
scala> val reduceF = (x: Int,y: Int ) => x + y
reduceF: (Int, Int) => Int = $$Lambda$1499/202685711@7035aa96

scala> listY reduce reduceF
res14: Int = 50

```
**filter( )**

The filter method allows you to pare down a collection by specifying a criterion. Any element in the collection that does not meet that criterion is dropped, and you get a new collection consisting only of the elements that meet the criterion.

<a href="http://imgur.com/UeFdRpO"><img src="http://i.imgur.com/UeFdRpO.png" title="source: imgur.com" /></a>
*filter( )*

```scala
scala> val filterF = (x: Int) => x % 2 == 0
filterF: Int => Boolean = $$Lambda$1557/122548647@65114581

scala> listY filter filterF
res15: List[Int] = List(12, 14)
```

**foldLeft( )**

`reduce( )` has three properties

* Using reduce on an empty collection yields an exception.
* You can only reduce a collection to a value of the same type as the elements in the collection.
* The order of the items in the collection (usually) matters.

We also noticed that there are several common operations—sum, product, and string concatenation—that are just special cases of reduce.

As it happens, reduce is itself a special case of a more fundamental operation: `foldLeft`. Furthermore, while order still matters, foldLeft can

* handle empty collections, and
* reduce a collection of one type to a value of a different type.

Why is that? First, foldLeft takes a binary operation just as reduce does, but it also takes a starting value in addition to the collection. That is how foldLeft can handle empty collections. If the collection is empty, you’re just left with the starting value. Second, because you give foldLeft a starting value, that starting value could be of any type; it doesn’t have to match the type of the items in the collection. The reason reduce can only reduce a collection to a value of the same type is because the only starting value it has is the first value in the collection.

We implemented a product operation last week with reduce. Let’s do it this week with foldLeft.

<a href="http://imgur.com/zjZWkYF"><img src="http://i.imgur.com/zjZWkYF.png" title="source: imgur.com" height="300" width="300"/></a>

*fold( )*

```scala
scala> val f = (x: Int, y: Int) => x * y
f: (Int, Int) => Int = $$Lambda$1558/1849065253@1e32d917

scala> listY.fold(1)(f)
res17: Int = 24024
```

**flatMap( )**

`map()` transform each element in a collection to the same or different type, flatMap can 

* convert a collection of element into a collection of collection of elements and finally faltten it to a collection of elements. 
* or can directly work on a collection of collections, transform each collection and finally flatten them 

```scala
scala> listY map (x => (1 to 5) map (x * _))
res22: List[scala.collection.immutable.IndexedSeq[Int]] = List(Vector(11, 22, 33, 44, 55), Vector(12, 24, 36, 48, 60), Vector(13, 26, 39, 5
2, 65), Vector(14, 28, 42, 56, 70))

scala> listY flatMap (x => (1 to 5) map (x * _))
res21: List[Int] = List(11, 22, 33, 44, 55, 12, 24, 36, 48, 60, 13, 26, 39, 52, 65, 14, 28, 42, 56, 70)
```

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

listOfLists flatMap {
	x => x map (_ * 2)
}

// Exiting paste mode, now interpreting.

res20: List[Int] = List(2, 4, 6, 90, 10, 12, 14, 16, 18, 22, 24, 26, 28)
```

**Run length encoding**

Let us implement a run length encoding function using the higher order list funtions we have seen

Run length encoding is a commonly used compression technique that packs the repetative values to reduce the space consumption

To achieve this let us divide our problem into two smaller sub problems. 

1. for a given list, create a list of list, such that  
* each List contains a single element if the consecutive elements are not equal
* if the consecutive elements are same then, they are added to the same list.

2. encode the lists with the element and it's count

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

// Exiting paste mode, now interpreting.

pack: [T](xs: List[T])List[List[T]]

scala> val someList = List("a", "a", "b", "b", "b", "c", "a")
someList: List[String] = List(a, a, b, b, b, c, a)

scala> pack(someList)
res25: List[List[String]] = List(List(a, a), List(b, b, b), List(c), List(a))

```

```scala
def encode[T](xs: List[T]): List[(T, Int)] = pack(xs).map(l => (l.head, l.length))
```

above we have used a function called `span` recursively, `span` is a function that takes a predicate and splits the given `Seq` into two, one until the conditions are met, and the other with rest of the element.

```scala
scala> someList span (_ == "a")
res30: (List[String], List[String]) = (List(a, a),List(b, b, b, c, a))
```

