/*
class Person {
  val firstName = "Dilyan"
  val lastName = "Damyanov"
  def name = firstName + " " + lastName
}

val dilyan = new Person
dilyan.firstName
dilyan.lastName
dilyan.name

val newDilyan = new Person

object alien {
  def greet(p: Person) =
    "Greetings, " + p.name + "!"
}

alien.greet(dilyan)
alien.greet(newDilyan)
*/

class Person (val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}

val vanya = new Person("Vanya", "Damyanova")
vanya.name

val newPerson = new Person(lastName = "Last", firstName = "First")
newPerson.name


// Cats, Again

class Cat (val name: String, val color: String, val food: String)
val oswald = new Cat("Oswald", "Black", "Milk")
val henderson = new Cat("Henderson", "Ginger", "Chips")
val quentin = new Cat("Quentin", "Tabby and white", "Curry")


// Cats on the Prowl

object ChipShop {
  def willServe(c: Cat): Boolean =
    if (c.food == "Chips")
      true
    else
      false
}
ChipShop.willServe(oswald)
ChipShop.willServe(henderson)
ChipShop.willServe(quentin)


// Directorial Debut

class Director (
  val firstName: String,
  val lastName: String,
  val yearOfBirth: Int) {
  def name: String =
    firstName + " " + lastName
  def copy(
    firstName: String = this.firstName,
    lastName: String = this.lastName,
    yearOfBirth: Int = this.yearOfBirth): Director =
      new Director(firstName, lastName, yearOfBirth)
  }

class Film (
  val name: String,
  val yearOfRelease: Int,
  val imdbRating: Double,
  val director: Director) {
  def directorsAge =
    yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director): Boolean =
    if (d == director)
      true
    else
      false
  def copy(
    name: String = this.name,
    yearOfRelease: Int = this.yearOfRelease,
    imdbRating: Double = this.imdbRating,
    director: Director = this.director): Film =
      new Film(name, yearOfRelease, imdbRating, director)
}

val eastwood = new Director("Clint", "Eastwood", 1930)
val mcTiernan  = new Director("John", "McTiernan", 1951)
val nolan  = new Director("Christopher", "Nolan", 1970)
val someBody  = new Director("Just", "Some Body", 1990)

val memento  = new Film("Memento", 2000, 8.5, nolan)
val darkKnight  = new Film("Dark Knight", 2008, 9.0, nolan)
val inception  = new Film("Inception", 2010, 8.8, nolan)
val highPlainsDrifter  = new Film("High Plains Drifter", 1973, 7.7, eastwood)
val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
val unforgiven  = new Film("Unforgiven", 1992, 8.3, eastwood)
val granTorino  = new Film("Gran Torino", 2008, 8.2, eastwood)
val invictus  = new Film("Invictus", 2009, 7.4, eastwood)
val predator = new Film("Predator", 1987, 7.9, mcTiernan)
val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

eastwood.yearOfBirth
dieHard.director.name
invictus.isDirectedBy(nolan)

highPlainsDrifter.copy(name = "L'homme des hautes plaines").name
highPlainsDrifter.copy(name = "L'homme des hautes plaines").yearOfRelease
highPlainsDrifter.copy(name = "L'homme des hautes plaines").imdbRating
highPlainsDrifter.copy(name = "L'homme des hautes plaines").director.name

thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926)).name
thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926)).yearOfRelease
thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926)).imdbRating
thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926)).director.name

inception.copy().copy().copy().name


// A Simple Counter

class Counter (in: Int) {
  def inc = new Counter(in + 1)
  def dec = new Counter(in - 1)
  val count = in
}

new Counter(10).inc.dec.inc.inc.count


// Counting faster

class fastCounter (val count: Int) {
  def inc(in: Int = 1) = new fastCounter(count + in)
  def dec(in: Int = 1) = new fastCounter(count - in)
}

new fastCounter(10).inc(2).dec(4).inc(23).inc().count


// Additional Counting

class Adder(amount: Int) {
  def add(in: Int): Int = in + amount
}

class newCounter(val count: Int) {
  def dec = new newCounter(count - 1)
  def inc = new newCounter(count + 1)
  def adjust(adder: Adder) =
    new newCounter(adder.add(count))
}

new newCounter(5).adjust(new Adder(3)).count

