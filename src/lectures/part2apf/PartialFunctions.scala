package lectures.part2apf

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // equivalent to Function1[Int, Int] or Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 123
    else if (x == 2) 456
    else if (x == 5) 999
    else throw new FunctionNotApplicationException

  class FunctionNotApplicationException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 123
    case 2 => 456
    case 5 => 999
  }

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 123
    case 2 => 456
    case 5 => 999
  } // partial function value
  println(aPartialFunction(1))
//  println(aPartialFunction(3)) // MatchError

  // PartialFunction utilities
  println(aPartialFunction.isDefinedAt(65))

  // lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(1))
  println(lifted(3))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }
  println(pfChain(2))
  println(pfChain(45))

  // PartialFunction extends normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial function as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
    Note: PartialFunction can only have ONE parameter type
   */

  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 5
  }

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "bye" => "Once you start talking to me, theres no return, human!"
    case "call mom" => "Unable to find your phone without your credit card."
  }
  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)
}
