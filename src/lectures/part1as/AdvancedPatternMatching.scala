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

}
