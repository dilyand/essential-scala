class Timestamp(val seconds: Long)

object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}

Timestamp(1, 1, 1).seconds
Timestamp

// Friendly Person Factory

class Person (val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}

object Person {
  def apply(name: String): Person =
    new Person(firstName = name.split(" ")(0), lastName = name.split(" ")(1))
}

Person("Dilyan Damyanov").firstName


// Extended Body of Work

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

object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int): Director =
    new Director(firstName, lastName, yearOfBirth)
  def older(dir1: Director, dir2: Director): Director =
    if (dir1.yearOfBirth > dir2.yearOfBirth)
      dir2
    else
      dir1
}

val lynch = Director("David", "Lynch", 1946)
lynch.name

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

object Film {
  def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director): Film =
    new Film(name, yearOfRelease, imdbRating, director)
  def newer(film1: Film, film2: Film): Film =
    if (film1.yearOfRelease > film2.yearOfRelease) film1 else film2
  def highestRating(f1: Film, f2: Film): Double =
    if (f1.imdbRating > f2.imdbRating)
      f1.imdbRating
    else
      f2.imdbRating
  def oldestDirectorAtTheTime(f1: Film, f2: Film): Director = {
    if (f1.directorsAge > f2.directorsAge) f1.director else f2.director
  }
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

Director.older(eastwood, nolan)
Film.newer(memento, inception)
Film.highestRating(unforgiven, invictus)
Film.oldestDirectorAtTheTime(dieHard, thomasCrownAffair)


// Type or Value?


val prestige: Film = bestFilmByChristopherNolan()
// type

new Film("Last Action Hero", 1993, mcTiernan)
// type

Film("Last Action Hero", 1993, mcTiernan)
// value

Film.newer(highPlainsDrifter, thomasCrownAffair)
// value

Film.type
// value


