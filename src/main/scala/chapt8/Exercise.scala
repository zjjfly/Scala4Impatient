package chapt8

import java.awt.Rectangle


/**
  * Created by zjjfly on 16/5/27.
  */
object Exercise {
  def main(args: Array[String]) {
    val account: BankAccount = new BankAccount(0)
    println(account.deposit(100))
    val check = new CheckingAccount(1)
    println(check.deposit(10000))
    //4
    val car: SimpleItem = new SimpleItem(10000,"car")
    val bike: SimpleItem = new SimpleItem(100,"bike")
    val bus: SimpleItem = new SimpleItem(1,"bus")
    val bundle: Bundle = new Bundle
    bundle.addItem(car)
    bundle.addItem(bike)
    bundle.addItem(bus)
    println(bundle)
    println(bundle.price)

    //6
    val points = List.newBuilder[Point]
    points += new Point(1,2)
    points += new Point(2,2)
    points += new Point(1,3)
    points += new Point(2,3)
    val rectangles: Rectangles = new Rectangles(points.result())
    println(rectangles.centerPoint)
  }
}

class BankAccount(initBalance: Double) {
  protected var balance = initBalance

  def deposit(amount: Double) = {
    balance += amount
    balance
  }

  def withDraw(amount: Double) = {
    balance -= amount
    balance
  }

}

//1
class CheckingAccount(initBalance: Double) extends BankAccount(initBalance) {
  private val charge = 1

  override def deposit(amount: Double) = super.deposit(amount - charge)

  override def withDraw(amount: Double) = super.withDraw(amount + charge)
}

//2
class SavingsAccount(initBalance: Double) extends BankAccount(initBalance: Double) {
  private val charge = 1
  private var freeTime = 3

  override def deposit(amount: Double) = {
    freeTime -= 1
    if (freeTime < 0) super.deposit(amount - charge) else super.deposit(amount)
  }

  override def withDraw(amount: Double) = {
    freeTime -= 1
    if (freeTime < 0) super.withDraw(amount + charge) else super.withDraw(amount)
  }

  def earnMonthlyInterest = {
    freeTime = 3
    super.deposit(balance * 0.01)
  }
}

//4
abstract class Items {
  def price: Double

  def description: String
}

class SimpleItem(override val price: Double, override val description: String) extends Items

class Bundle() extends Items{
  private val items=Array.newBuilder[SimpleItem]

  override def price: Double = items.result().map(_.price).sum

  def addItem(s:SimpleItem)=items +=  s

  override def toString=description

  override def description: String ="this bundle contains "+items.result().map(_.description).mkString(",")
}

//5
class Point(val x:Double,val y:Double){
  override def toString ="("+x+","+y+")"
}

class LabeledPoint( val label:String,x:Double, y:Double) extends Point(x,y)

//6
abstract class Shape{
  def centerPoint:Point
}

class Rectangles(list:List[Point]) extends Shape{
  override def centerPoint: Point = {
    val x=list.map(_.x).sum / 4
    val y=list.map(_.y).sum /4
    new Point(x,y)
  }
}
class Circle (center: Point,r:Double) extends Shape{
  override def centerPoint: Point = center
}
//7
class Square private(x:Int,y:Int,width:Int) extends Rectangle(x,y,width,width){
  def this(width:Int)={
    this(0,0,width)
  }
  def this()={
    this(0,0,0)
  }
}