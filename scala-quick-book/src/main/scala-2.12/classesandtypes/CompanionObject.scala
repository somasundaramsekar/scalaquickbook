package classesandtypes

class MyString(val jString:String) {
  private var extraData = ""
  override def toString = s"$jString $extraData"
}

object MyString {
  def apply(base:String, extras:String) = {
    val s = new MyString(base)
    s.extraData = extras
    s
  }
  def apply(base:String) = new MyString(base)
}
