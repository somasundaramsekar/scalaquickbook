package basics

/**
  * Created by somasundar.sekar on 11/21/2016.
  */
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
