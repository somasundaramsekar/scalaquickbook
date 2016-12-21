package classesandtypes

trait List[T] {
  def isEmpty: scala.Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: scala.Boolean = false
}

class Nil[T] extends List[T] {
  override def isEmpty: scala.Boolean = true

  override def head: T = throw new NoSuchElementException("Nil.head")

  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")
}

object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, new Nil))

  def apply[T]: List[T] = new Nil[T]
}

