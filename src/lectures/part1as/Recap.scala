package lectures.part1as

import scala.annotation.tailrec

object Recap extends App {

  val aCondition: Boolean = false
  val aConditionedVal = if (aCondition) 42 else 35
  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  val theUnit: Unit = println("Hello scala!")

  def aFunc(x: Int): Int = x + 1

  @tailrec
  def fact(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else fact(n - 1, n * acc)

  class Animal
  class Dog extends Animal

  val aDog: Animal = new Dog

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // same as above

  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("Crunch!!")
  }

  abstract class MyList[+A] // variance
  object MyList

  case class Person(name: String, age: Int)

  val throwsException = throw new Exception
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught!"
  } finally {
    println("some logs!")
  }

  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  incrementer(1)

  val anonIncrementer = (x: Int) => x + 1
  List(1, 2, 3).map(anonIncrementer)

  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield num + " " + char

  val aMap = Map(
    "Daniel" -> 789,
    "Jess" -> 4555
  )

  val anOption = Some(2)

  val x = 2
  x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greetings = bob match {
    case Person(n, _) => s"Hi, my name is $n"
  }


}
