import java.util.Date

trait Visitor {
  def id: String // Unique id assigned to each user
  def createdAt: Date // Date this user first visited the site
  // How long has this visitor been around?
  def age: Long = new Date().getTime - createdAt.getTime
}

case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

case class User(
                 id: String,
                 email: String,
                 createdAt: Date = new Date()
               ) extends Visitor

val newUser = Anonymous("anon1")

newUser.createdAt
newUser.age


// Cats, and More Cats

trait Feline {
  def colour: String
  def sound: String
}

trait BigCat extends Feline {
  val sound: String = "roar"
}

case class Cat(colour: String, sound: String = "meow", food: String) extends Feline
case class Tiger(colour: String) extends BigCat
case class Lion(colour: String, maneSize: Int) extends BigCat
case class Panther(colour: String) extends BigCat


// Shaping Up With Traits & Shaping Up 2 (Da Streets)

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = (2 * width) + (2 * height)
  val area = width * height
}

case class Circle (radius: Double) extends Shape {
  val sides = 0
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * (radius * radius)
}

case class Rectangle (width: Double, height: Double) extends Rectangular

case class Square (side: Double) extends Rectangular {
  def width = side
  def height = side
}

Circle(3).perimeter
Circle(2.1).area
Rectangle(3, 4.5).perimeter
Rectangle(1, 2.3).area
Square(1).perimeter
Square(1).area

