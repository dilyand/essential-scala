object Test {}
Test

object Test2 {
  def name: String = "Probably the best object ever"
}
Test2.name

object Test3 {
  def hello(name: String) =
    "Hello " + name + "!"
}
Test3.hello("Dilyan")

object Test4 {
  val name = "Noel"
  def hello(other: String): String =
    name + " says hi to " + other + "."
}
Test4.hello("Dilyan")

object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }
  def noParameterMethod = {
    println("Evaluating noParameterMethod")
    42
  }
}
Test7
Test7.simpleField
Test7.simpleField
Test7.noParameterMethod
Test7.noParameterMethod



//Cat-o-matique

object Cat1 {
  val name = "Oswald"
  val colour = "Black"
  val food = "Milk"
}

object Cat2 {
  val name = "Henderson"
  val colour = "Ginger"
  val food = "Chips"
}

object Cat3 {
  val name = "Quentin"
  val colour = "Tabby and white"
  val food = "Curry"
}



// Square Dance!

object calc {
  def square(x: Double): Double = {
    x * x
  }
  def cube(x: Double): Double = {
    square(x) * x
  }
}
calc.square(2)
calc.square(5)
calc.cube(2)
calc.cube(5)
//val x: Int = calc.square(2)
val y: Int = calc.square(2).toInt
y



// Precise Square Dance!

object calc2 {
  def square(value: Double) = value * value
  def cube(value: Double) = value * square(value)
  def square(value: Int) = value * value
  def cube(value: Int) = value * square(value)
}
calc2.square(2.0)
calc2.cube(4)



// Order of evaluation

object argh {
  def a = {
    println("a")
    1
  }
  val b = {
    println("b")
    a + 2
  }
  def c = {
    println("c")
    a
    b + "c"
  }
}
argh.c + argh.b + argh.a



// Greetings, human

object person {
  val firstName = "Dilyan"
  val lastName = "Damyanov"
}

object alien {
  def greet(p: person.type) =
    "Greetings, " + p.firstName + " " + p.lastName + "!"
}

alien.greet(person)



// The Value of Methods

object calculator {
  def square(x: Int) = x * x
}
//val someField = calculator.square
val someField = calculator.square(2)

object clock {
  def time = System.currentTimeMillis
}
val now = clock.time
val aBitLaterThanNow = clock.time
