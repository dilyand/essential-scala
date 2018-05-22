sealed trait Feline {
  def dinner: Food
}

final case class Lion() extends Feline {
  def dinner: Food =
    Antelope
}

final case class Tiger() extends Feline {
  def dinner: Food =
    TigerFood
}

final case class Panther() extends Feline {
  def dinner: Food =
    Licorice
}

final case class Cat(favouriteFood: String) extends Feline {
  def dinner: Food =
    CatFood(favouriteFood)
}

sealed trait Food
final case object Antelope extends Food
final case object TigerFood extends Food
final case object Licorice extends Food
final case class CatFood(food: String) extends Food

sealed trait AltFeline {
  def dinner: Food =
    this match {
      case AltLion() => Antelope
      case AltTiger() => TigerFood
      case AltPanther() => Licorice
      case AltCat(favouriteFood) => CatFood(favouriteFood)
    }
}

final case class AltLion() extends AltFeline
final case class AltTiger() extends AltFeline
final case class AltPanther() extends AltFeline
final case class AltCat(favouriteFood: String) extends AltFeline

Cat("Chips").dinner
Lion().dinner
Tiger().dinner
Panther().dinner

AltCat("Fish").dinner
AltLion().dinner
AltTiger().dinner
AltPanther().dinner

// Traffic Lights

sealed trait TrafficLight {
  def next: TrafficLight
}

final case object Red extends TrafficLight {
  def next: TrafficLight =
    Green
}

final case object Green extends TrafficLight {
  def next: TrafficLight =
    Yellow
}

final case object Yellow extends TrafficLight {
  def next: TrafficLight =
    Red
}

Red.next
Green.next
Yellow.next
Yellow.next.next


sealed trait AltTrafficLight {
  def next: AltTrafficLight =
    this match {
      case AltRed => AltGreen
      case AltGreen => AltYellow
      case AltYellow => AltRed
    }
}

final case object AltRed extends AltTrafficLight
final case object AltGreen extends AltTrafficLight
final case object AltYellow extends AltTrafficLight

AltRed.next
AltGreen.next
AltYellow.next
AltGreen.next.next == AltYellow.next

// Calculation

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation

object Calculator {
  def +(calc: Calculation, int: Int): Calculation =
    calc match {
      case s: Success => Success(s.result + int)
      case f: Failure => Failure(f.message)
    }
  def -(calc: Calculation, int: Int): Calculation =
    calc match {
      case s: Success => Success(s.result - int)
      case f: Failure => Failure(f.message)
    }
  def /(calc: Calculation, int: Int): Calculation =
    calc match {
      case s: Success =>
        int match {
          case 0 => Failure("Division by zero")
          case _ => Success(s.result / int)
        }
      case f: Failure => Failure(f.message)
    }
}

Calculator.+(Success(1), 1)
Calculator.-(Success(1), 1)
Calculator.+(Failure("Badness"), 1)

Calculator./(Success(4), 2)
Calculator./(Success(4), 0)
Calculator./(Failure("Badness"), 0)
