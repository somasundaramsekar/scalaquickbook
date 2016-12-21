package basics

/**
  * Created by somasundar.sekar on 11/23/2016.
  */
class BasicMethods extends App {

  def add(x: Int, y: Int) = x + y

  def numberState(x: Int) =
    if (x < 10) "Less than 10"
    else if (x > 10) "Greater that 10"
    else "x is 10"


}
