// A Calculator


sealed trait Expression

final case class Number(value: Double) extends Expression
final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression

sealed trait Calculation

final case class Success(result: Double) extends Calculation
final case class Failure(reason: String) extends Calculation

final case object ExpressionOps extends Expression {
  def eval(expression: Expression): Calculation = {
    expression match {
      case Number(v) => Success(v)
      case Addition(l, r) =>
        eval(l) match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            eval(r) match {
              case Failure(reason) => Failure(reason)
              case Success(r2) => Success(r1 + r2)
            }
        }
      case Subtraction(l, r) =>
        eval(l) match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            eval(r) match {
              case Failure(reason) => Failure(reason)
              case Success(r2) => Success(r1 - r2)
            }
        }
      case Division(l, r) =>
        eval(l) match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            eval(r) match {
              case Failure(reason) => Failure(reason)
              case Success(r2) =>
                r2 match {
                  case 0 => Failure("Can't divide by zero.")
                  case _ => Success(r1 / r2)
                }
            }
        }
      case SquareRoot(v) =>
        eval(v) match {
          case Failure(reason) => Failure(reason)
          case Success(r) =>
            if(r < 0)
              Failure("That is not a real number.")
            else
              Success(Math.sqrt(r))
            }
    }
  }
}

ExpressionOps.eval(Addition(SquareRoot(Number(-1.0)), Number(2.0)))
ExpressionOps.eval(Addition(SquareRoot(Number(4.0)), Number(2.0)))
ExpressionOps.eval(Division(Number(4), Number(0)))


// JSON

/**
  * Json       ::= JsNumber value:Double
  *            | JsString value:String
  *            | JsBoolean value:Boolean
  *            | JsNull
  *            | JsSequence
  *            | JsObject
  * JsSequence ::= SeqCell head:Json tail:JsSequence
  *            | SeqEnd
  * JsObject   ::= ObjectCell key:String value:Json tail:JsObject
  *            | ObjectEnd
  */

sealed trait Json {
  def print: String = {
    def quote(s: String): String = {
      '"'.toString ++ s ++ '"'.toString
    }

    def seqToJson(seq: SeqCell): String = {
      seq match {
        case SeqCell(head, tail@SeqCell(_, _)) => s"${head.print}, ${seqToJson(tail)}"
        case SeqCell(head, SeqEnd) => head.print
      }
    }

    def objectToJson(obj: ObjectCell): String = {
      obj match {
        case ObjectCell(key, value, tail@ObjectCell(_, _, _)) => s"${quote(key)}: ${value.print}, ${objectToJson(tail)}"
        case ObjectCell(key, value, ObjectEnd) => s"${quote(key)}: ${value.print}"
      }
    }

    this match {
      case JsNumber(value) => value.toString
      case JsString(value) => quote(value)
      case JsBoolean(value) => value.toString
      case JsNull => "null"
      case s@SeqCell(_, _) => "[" ++ seqToJson(s) ++ "]"
      case SeqEnd => "[]"
      case o@ObjectCell(_, _, _) => "{" ++ objectToJson(o) ++ "}"
      case ObjectEnd => "{}"
    }
  }
}
final case class JsNumber(value: Double) extends Json
final case class JsString(value: String) extends Json
final case class JsBoolean(value: Boolean) extends Json
final case object JsNull extends Json
sealed trait JsSequence extends Json
final case class SeqCell(head: Json, tail: JsSequence) extends JsSequence
final case object SeqEnd extends JsSequence
sealed trait JsObject extends Json
final case class ObjectCell(key: String, value: Json, tail: JsObject) extends JsObject
final case object ObjectEnd extends JsObject

SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqEnd))).print

ObjectCell(
  "a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber(3.0), SeqEnd))), ObjectCell(
    "b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell(JsString("c"), SeqEnd))), ObjectCell(
      "c", ObjectCell("doh", JsBoolean(true),
        ObjectCell("ray", JsBoolean(false),
          ObjectCell("me", JsNumber(1.0), ObjectEnd))), ObjectEnd
    ) )
).print


// Music