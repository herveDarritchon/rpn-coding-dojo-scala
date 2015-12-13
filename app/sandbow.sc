val expression = "2*4+(5+6)"
val terms: Array[String] = expression.split("(?<=[-+*/])|(?=[-+*/])")
expression.split("(?<=[-+*/])|")

import scala.util.parsing.combinator._

class ReversePolishCalculator extends JavaTokenParsers {
  def num: Parser[Float] = floatingPointNumber ^^ (_.toFloat)
}

object Calculator extends ReversePolishCalculator {

  def flatten(res: Any): List[String] = res match {
    case x ~ y => flatten(x) ::: flatten(y)
    case None => Nil
    case Some(x) => flatten(x)
    case x:String => List(x)
  }

  def main(args: Array[String]) {
    val result = parseAll(num, args(0)).getOrElse(None)
    println("Parsed " + flatten(result))
  }

}

Calculator.main(Array("2","5","7","34","1","23"))