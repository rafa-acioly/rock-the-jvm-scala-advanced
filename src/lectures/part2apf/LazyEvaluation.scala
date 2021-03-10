package lectures.part2apf

object LazyEvaluation extends App {

  lazy val x: Int = throw new RuntimeException
  lazy val y: Int = {
    println("hello")
    42
  }

  def sideEffectCondition: Boolean = {
    println("Boo")
    true
  }
  def simpleCondition: Boolean = false
  println(if (simpleCondition && sideEffectCondition) "yes" else "no")
  // "no", the compiler does not evaluate the second condition if the first is already false

  def byNameMethod(n: => Int): Int = {
    lazy val t = n // lazy is only evaluated once
    t + t + t + 1
  }
  def retrieveMagicValue: Int = {
    println("waiting")
    Thread.sleep(1000)
    42
  }
  println(byNameMethod(retrieveMagicValue))


  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }
  def greaterThan20(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }
  val numbers = List(1, 24, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30)
  val gt20 = lt30.filter(greaterThan20)
  println(gt20)
  println

  val lt30Lazy = numbers.withFilter(lessThan30)
  val gt20Lazy = lt30Lazy.withFilter(greaterThan20)
  gt20Lazy.foreach(println)
}
