package chapt6

/**
  * Created by zjjfly on 16/5/23.
  */
object Exercise {
  def main(args: Array[String]): Unit = {
    //4
    Point(1, 2)
    val values: PokerSuit.ValueSet = PokerSuit.values
    values.foreach(println)
  }

  //7
  import PokerSuit._

  def isRed(c: PokerSuit) = c match {
    case Heart => true
    case Diamond => true
    case _ => false
  }
}

//1
object Conversions {
  def inchesToCentimeters(inches: Double) {
    30.48 * inches
  }

  def gallonsToLiters(gallons: Double) {
    3.7854118 * gallons
  }

  def milesToKilometers(miles: Double) {
    1.609344 * miles
  }
}

//2
abstract class UnitConversion {
  def convertion(i: Double): Double
}

object InchesToCentimeters extends UnitConversion {
  override def convertion(inches: Double): Double = 30.48 * inches
}

object GallonsToLiters extends UnitConversion {
  override def convertion(gallons: Double): Double = 3.7854118 * gallons
}

object MilesToKilometers extends UnitConversion {
  override def convertion(miles: Double): Double = 1.609344 * miles
}

//4
class Point(val x: Int, val y: Int)

object Point {
  def apply(x: Int, y: Int) = {
    new Point(x, y)
  }
  implicit object PointOrdering extends Ordering[Point]{
    override def compare(x: Point, y: Point): Int = if(x.x>y.x) 1 else if(x.x<y.x) -1 else x.y-y.y
  }
}

//5
object Reverse extends App {
  println(args.reverse.mkString(" "))
}

//6
object PokerSuit extends Enumeration {
  type PokerSuit = Value
  val Heart = Value("♥")
  val Spade = Value("♠")
  val Diamond = Value("♦")
  val Club = Value("♣")
}
//8
object RGB extends Enumeration with App {
  val RED = Value(0xff0000, "Red")
  val BLACK = Value(0x000000, "Black")
  val GREEN = Value(0x00ff00, "Green")
  val CYAN = Value(0x00ffff, "Cyan")
  val YELLOW = Value(0xffff00, "Yellow")
  val WHITE = Value(0xffffff, "White")
  val BLUE = Value(0x0000ff, "Blue")
  val MAGENTA = Value(0xff00ff, "Magenta")
}
