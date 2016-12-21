package classesandtypes

trait Expr
case class Num(x: Int) extends Expr
case class Sum(x: Expr, y: Expr) extends Expr

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

case class Product(e1: Expr, e2: Expr) extends Expr

