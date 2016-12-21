package classesandtypes

/**
  * Created by somasundar.sekar on 12/17/2016.
  */
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
