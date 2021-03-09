package lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val desc: Unit = numbers match {
    case head :: Nil => println(s"Only one item: $head")
    case _ =>
  }

  class Person(val name: String, val age: Int)
  object Person {
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 21) None
      else Some((person.name, person.age))
    }

    def unapply(age: Int): Option[String] = Some(if (age < 21) "minor" else "major")
  }
  val bob = new Person("bob", 30)
  val greeting = bob match {
    case Person(str, i) => s"Hi, my name is $str, and im $i y.o"
    case _ => s"Not allowed"
  }
  println(greeting)

  val legal = bob.age match {
    case Person(status) => s"legal for: $status"
    case _ => "not legal"
  }
  println(legal)
  
  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val n: Int = 8
  val matchProp = n match {
    case singleDigit() => "single digit"
    case even() => "an even number"
    case _ => "no props."
  }
  println(matchProp)

  // infix patten
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
    // same as Or(number, string)
  }
  println(humanDescription)

  // decomposing sequences
  val varargs = numbers match {
    case List(1, _*) => s"starting with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }
  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](arg: MyList[A]): Option[Seq[A]] =
      if (arg == Empty) Some(Seq.empty)
      else unapplySeq(arg.tail).map(arg.head +: _)
  }
  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2"
    case _ => "something else"
  }
  println(decomposed)

  // custom return types for unapply
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(arg: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty: Boolean = false
      def get: String = arg.name
    }
    val person: String = bob match {
      case PersonWrapper(n) => s"This person name is: $n"
      case _ => "an alien."
    }
    println(person)
  }
}
