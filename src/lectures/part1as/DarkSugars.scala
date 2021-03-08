package lectures.part1as

object DarkSugars extends App {

  // syntax sugar #1: method with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."
  val description = singleArgMethod {
    println("what")
    42 + 11
  }
  println(description)

  List(1, 2, 3).map { x =>
    x + 1
  }

  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }
  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }
  val aFunkyAction: Int => Int = (x: Int) => x + 1
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello scala!")
  })
  val aSweeterThread = new Thread { () => println("sweet scala!") }

  abstract class AnAbstractType {
    def implement: Int = 23
    def f(a: Int): Unit
  }
  val anAbstractInstance: Int => Unit = (a: Int) => println("sweet")

  // syntax sugar #3: the :: and #:: methods are special
  val prependList = 2 :: List(3, 4)
  1 :: 2 :: 3 :: List(4, 5)
  // List(4, 5).::(3).::(2).::(1)

  class MyStream[T] {
    def -->:(i: Int): MyStream[T] = this
  }
  val myStream = 1 -->: 2 -->: new MyStream[Int]

  // syntax sugar #4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }
  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "scala is so sweet!"

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Int Composite String = ???
  // same as Composite[Int, String]

  class -->[A, B]
  val towards: Int --> String = ???
  // same as -->[Int, String]

  // syntax sugar #6: update() is very special, muck like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewritten to anArray.update(2, 7)

  // syntax sugar #7: setter for mutable containers
  class Mutable {
    private var internalMember = 0
    def member: Int = internalMember // "getter"
    def member_=(value: Int): Unit = internalMember = value // "setter"
  }
  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // this is rewritten as aMutableContainer.member_=(42)
  println(aMutableContainer)
}
