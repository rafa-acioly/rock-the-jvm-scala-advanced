package lectures.part2apf

object CurriesPAF extends App {

  val superAdder: Int => Int => Int = x => y => x + y
  val add3 = superAdder(3)
  println(add3(5))
  println(superAdder(3)(5))

  def curriedAdder(x: Int)(y: Int): Int = x + y
  println(curriedAdder(3)(5))
  val add4: Int => Int = curriedAdder(4)
  println(add4(4))

  def inc(x: Int): Int = x + 1
  List(1, 2, 3).map(inc) // ETA-expansion --> the compiler will transform this to .map(x => inc(x))

  val add5 = curriedAdder(5)_ // Int => Int
  println(add5(5))

  val simpleAddFunction = (a: Int, b: Int) => a + b
  def simpleAddMethod(a: Int)(b: Int): Int = a + b
  def curriedAddMethod(a: Int)(b: Int): Int = a + b

  val add7 = (x: Int) => simpleAddFunction(7, x)
  val add7_2 = simpleAddFunction.curried(7)

  val add7_3 = curriedAddMethod(7)_
  val add7_4 = curriedAddMethod(7)(_)

  val add7_6 = simpleAddFunction(7, _: Int)

  def concatenator(a: String, b: String, c: String) = a + b + c
  val insertName = concatenator("Hi, ", _: String, " how are you?")
  val insertName_2 = (name: String) => concatenator("Hi, ", name, " how are you?")
  println(insertName("Rafael"))

  val fillTheBlank = concatenator("Hello, ", _: String, _: String)
  println(fillTheBlank("rafael", " scala is awesome"))

  def curriedFormatter(s: String)(n: Double): String = s.format(n)
  val numbers = List(Math.PI, Math.E, 1, 9, 1.3e-12)

  val simpleFormat = curriedFormatter("%4.2f")_
  val seriousFormat = curriedFormatter("%8.6f")_
  val preciseFormat = curriedFormatter("%14.12f")_

  println(numbers.map(curriedFormatter("%8.6f")))
}
