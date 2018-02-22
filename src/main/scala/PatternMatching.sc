case class Person(firstName: String, lastName: String)

object Stormtrooper {
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, last) => s"Move along, $first"
    }
}

Stormtrooper.inspect(Person("Dilyan", "Damyanov"))
Stormtrooper.inspect(Person("Han", "Solo"))

// Feed the Cats

case class Cat (name: String, color: String, food: String)
val oswald = Cat("Oswald", "Black", "Milk")
val henderson = Cat("Henderson", "Ginger", "Chips")
val quentin = Cat("Quentin", "Tabby and white", "Curry")

object ChipShop {
  def willServe(c: Cat): Boolean =
    c.food match {
      case "Chips" => true
      case _ => false
  }
}

ChipShop.willServe(oswald)
ChipShop.willServe(henderson)
ChipShop.willServe(quentin)


// Get Off My Lawn!

case class Director (firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String =
    firstName + " " + lastName
}

case class Film (name: String,
                 yearOfRelease: Int,
                 imdbRating: Double,
                 director: Director) {
  def directorsAge =
    yearOfRelease - director.yearOfBirth
  def isDirectedBy(d: Director): Boolean =
    d match {
      case this.director => true
      case _ => false
    }
  def copy(name: String = this.name,
           yearOfRelease: Int = this.yearOfRelease,
           imdbRating: Double = this.imdbRating,
           director: Director = this.director): Film =
    Film(name, yearOfRelease, imdbRating, director)
}

object Dad {
  def rate(film: Film): Double =
    film.director match {
      case Director(_, "Eastwood", _) => 10.0
      case Director(_, "McTiernan", _) => 7.0
      case Director(_, _, _) => 3.0
    }
}

val eastwood = Director("Clint", "Eastwood", 1930)
val mcTiernan  = Director("John", "McTiernan", 1951)
val nolan  = Director("Christopher", "Nolan", 1970)

val memento  = Film("Memento", 2000, 8.5, nolan)
val darkKnight  = Film("Dark Knight", 2008, 9.0, nolan)
val inception  = Film("Inception", 2010, 8.8, nolan)
val highPlainsDrifter  = Film("High Plains Drifter", 1973, 7.7, eastwood)
val outlawJoseyWales  = Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
val unforgiven  = Film("Unforgiven", 1992, 8.3, eastwood)
val granTorino  = Film("Gran Torino", 2008, 8.2, eastwood)
val invictus  = Film("Invictus", 2009, 7.4, eastwood)
val predator = Film("Predator", 1987, 7.9, mcTiernan)
val dieHard = Film("Die Hard", 1988, 8.3, mcTiernan)
val huntForRedOctober = Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

Dad.rate(memento)
Dad.rate(highPlainsDrifter)
Dad.rate(dieHard)

