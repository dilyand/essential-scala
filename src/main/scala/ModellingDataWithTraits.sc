// Stop on a Dime

sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight


// Calculator

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation


// Water, Water, Everywhere

final case class BottledWater(size: Int, source: Source, carbonated: Boolean)
sealed trait Source
final case object Well extends Source
final case object Spring extends Source
final case object Tap extends Source



