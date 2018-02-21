case class Person(firstName: String, lastName: String) {
  def name = firstName + " " + lastName
}

val dilyan = new Person("Dilyan", "Damyanov")
Person
dilyan.firstName
dilyan
dilyan.copy() eq dilyan
dilyan.copy() == dilyan
val informalDilyan = dilyan.copy(firstName = "Dilyata")
Person("Бай", "Хуй") eq Person("Бай", "Хуй")


// Case Cats

case class Cat(colour: String, food: String)


// Roger Ebert Said It Best...

case class Director (firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String =
    firstName + " " + lastName
}

object Director {
  def older(dir1: Director, dir2: Director): Director =
    if (dir1.yearOfBirth > dir2.yearOfBirth)
      dir2
    else
      dir1
}

case class Film (name: String, yearOfRelease: Int, imdbRating: Double, director: Director) {
  def directorsAge =
    yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director): Boolean =
    if (d == director)
      true
    else
      false
}

object Film {
  def newer(film1: Film, film2: Film): Film =
    if (film1.yearOfRelease > film2.yearOfRelease)
      film1
    else
      film2
  def highestRating(f1: Film, f2: Film): Double =
    if (f1.imdbRating > f2.imdbRating)
      f1.imdbRating
    else
      f2.imdbRating
  def oldestDirectorAtTheTime(f1: Film, f2: Film): Director = {
    if (f1.directorsAge > f2.directorsAge) f1.director else f2.director
  }
}


// Case Class Counter

class Adder(amount: Int) {
  def add(in: Int): Int = in + amount
}

case class Counter (count: Int = 0) {
  def inc = copy(count = count + 1)
  def dec = copy(count = count - 1)
  def adjust(adder: Adder) = copy(count = adder.add(count))
}

Counter().inc.dec.inc.inc.count
Counter(5).adjust(new Adder(3)).count
Counter(0)
Counter().inc
Counter().inc.dec == Counter().dec.inc
Counter().inc.dec eq Counter().dec.inc


// Application, Application, Application

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    apply(parts(0), parts(1))
  }
}
