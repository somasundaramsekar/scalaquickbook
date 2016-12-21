package basics

import scala.annotation.tailrec

/**
  * Created by somasundar.sekar on 11/23/2016.
  */
object TailRecursion extends App{

  def gcd(a: Int, b: Int): Int =
    if(b == 0) a else gcd(b, a % b)

  /**
    * gcd(14, 21)
    * if(21 == 0) 14 else gcd(21, 14 % 21)
    * if(false) 14 else gcd(21, 14 % 21)
    * gcd(21, 14 % 21)
    * gcd(21, 14)
    * if(14 == 0) 21 else gcd(14, 21 % 14)
    * gcd(14, 7)
    * ....
    */

  def factorial(x: Int): Int =
  if(x == 0) 1 else x * factorial(x - 1)

  /**
    * factorial(4)
    * if(4 == 0) 1 else 4 * factorial(3)
    * 4 * factorial(3)
    * 4 * 3 * factorial(2)
    * 4 * 3 * 2 * factorial(1)
    * 4 * 3 * 2 * 1 * factorial(0)
    * 4 * 3 * 2 * 1 * 1
    */


  def factorial(x: Double): Double = {
    @tailrec
    def loop(acc: Double, y: Double): Double =
      if(y == 0) acc
      else loop(acc * y, y -1)
    loop(1, x)
  }
}
