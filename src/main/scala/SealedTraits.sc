// Printing Shapes (see below)

// The Color and the Shape

sealed trait Color {
  def red: Int
  def green: Int
  def blue: Int
}

final case class Hue (red: Int, green: Int, blue: Int, name: String, light: Boolean = true) extends Color {
  val shade =
    light match {
      case true => "light"
      case false => "dark"
    }
}

val red = Hue(255, 0, 0, "Red")
val yellow = Hue(255, 255, 224, "Yellow")
val pink = Hue(255, 192, 203, "Pink")

pink.shade

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Hue
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = 2 * width + 2 * height
  val area = width * height
}

final case class Circle (radius: Double, color: Hue) extends Shape {
  val sides = 0
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * (radius * radius)
}

final case class Rectangle (width: Double, height: Double, color: Hue) extends Rectangular

final case class Square (side: Double, color: Hue) extends Rectangular {
  def width = side
  def height = side
}

Circle(3, red).perimeter
Circle(2.1, yellow).area
Rectangle(3, 4, pink).perimeter
Rectangle(1, 2, red).area
Square(2.1, yellow).perimeter
Square(1.2, pink).area

val shape = Circle(2, Hue(255, 233, 244, "myColor"))
shape.radius

object Draw {
  def apply(shape: Shape): String =
    shape match {
      case Circle(radius, Hue(_, _, _, "Red", _) | Hue(_, _, _, "Yellow", _) | Hue(_, _, _, "Pink", _)) =>
        "A " + shape.color.name.toLowerCase + " circle with radius " + radius + "cm."
      case Circle(radius, hue) =>
        "A " + hue.shade + " circle with radius " + radius + "cm."
      case Rectangle(width, height, Hue(_, _, _, "Red", _) | Hue(_, _, _, "Yellow", _) | Hue(_, _, _, "Pink", _)) =>
        "A " + shape.color.name.toLowerCase + " rectangle with width " + width + "cm and height " + height + "cm."
      case Rectangle(width, height, hue) =>
        "A " + hue.shade + " rectangle with width " + width + "cm and height " + height + "cm."
      case Square(side, Hue(_, _, _, "Red", _) | Hue(_, _, _, "Yellow", _) | Hue(_, _, _, "Pink", _)) =>
        "A " + shape.color.name.toLowerCase + " square with side " + side + "cm."
      case Square(side, hue) =>
        "A " + hue.shade + " square with side " + side + "cm."
    }
}

Draw(Circle(3, red))
Draw(Rectangle(2.5, 3.4, pink))
Draw(Square(4.6, yellow))
Draw(Circle(2.1, Hue(255, 233, 244, "myColor")))


// A Short Division Exercise

sealed trait DivisionResult

final case class Finite (result: Int = 0) extends DivisionResult
final case object Infinite extends DivisionResult

object Divide {
  def apply(a: Int, b: Int): DivisionResult =
    b match {
      case 0 => Infinite
      case _ => Finite()
    }
}

Divide(1, 2)
Divide(1, 0)

Divide(1, 0) match {
  case Finite(result) => s"It's finite: ${result}"
  case Infinite      => s"It's infinite"
}
