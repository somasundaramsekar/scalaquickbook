package classesandtypes

/**
  * Created by somasundar.sekar on 12/19/2016.
  */
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

