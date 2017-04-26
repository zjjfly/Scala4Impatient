package chapt18
package Exercise

import java.util

import scala.collection.immutable.IndexedSeq
import scala.collection.mutable

/**
  * Created by zjjfly on 16/7/12.
  */
object Exercise {
  //5
  type NetWorkMember = n.Member forSome {val n: Network}

  def process1(m: NetWorkMember, mm: NetWorkMember) = (m, mm)

  def process2[M <: n.Member forSome {val n : Network}](m1: M, m2: M) = (m1, m2)


  def main(args: Array[String]) {
    val bug = new Bug
    bug.move(4).show().move(6).show().turn().move(5).show()
    val b = new Bug
    b move 4 and show and then move 6 and show turn around move 5 and show
    val book: Book = new Book
    book set Title to "Scala for Impatient" set Author to "Cay Horstmann"
    println(book)

    val chatter = new Network
    val myFace = new Network
    val fred1 = chatter.join("Fred")
    val fred3 = chatter.join("Fred")
    val fred2 = myFace.join("Fred")
    println(fred1.equals(fred2))
    //process1可以接受不同NetWork的Member,但process2不行
    println(process1(fred1, fred2))
    println(process2(fred1, fred3))
    val ints: Array[Int] = Array(1, 4, 5, 6, 8, 13, 15, 44)
    println(getIndex(ints, 4))

    printValues((x: Int) => x * x, 3, 6)
    printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)

    val second: Seconds = new Seconds(2.5)
    val meter: Meters = new Meters(3.1)
    //    println(second+meter) //会报错

  }

  //7
  def getIndex(array: Array[Int], target: Int): Int Either Throwable = {
    val takeWhile: Array[(Int, Int)] = array.zipWithIndex.takeWhile(_._1 <= target)
    val lastOption: Option[(Int, Int)] = takeWhile.lastOption
    lastOption match {
      case None => if (array.isEmpty) {
        Right(new Exception("hehe"))
      } else Left(0)
      case Some((a, b)) => if (a == target || takeWhile.size == array.size) Left(b)
      else {
        val i = target - a
        val j = array(b + 1) - target
        if (i > j) Left(b + 1) else Left(b)
      }
    }
  }

  //7
  def dosomething[T <: {def close() : Unit}](c: T, process: (T) => Unit) = {
    try {
      process(c)
    } catch {
      case e: Exception => c.close
    }
  }

  //8

  def printValues[T](f: {def apply(i: Int): T}, from: Int, to: Int) = {
    val ts: IndexedSeq[T] = for (i <- from to to) yield f(i)
    println(ts mkString "\t")
  }
}

//1,2
trait AndAction

object show extends AndAction

object then extends AndAction

object around

class Bug {
  var position = 0
  var f = 1

  def move(i: Int): Bug = {
    position += (f * i)
    this
  }

  def turn(): Bug = {
    f *= -1
    this
  }

  def turn(s: around.type): Bug = {
    f *= -1
    this
  }

  def show(): Bug = {
    println(position)
    this
  }

  def and[T <: AndAction](s: T): Bug = {
    if (s == show) {
      show(); this
    } else this
  }
}

//3
trait BookAttr

object Title extends BookAttr

object Author extends BookAttr

case class Book(var title: String = "", var author: String = "") {

  private var useNextArgAs: Any = null

  def set[T <: BookAttr](obj: T): this.type = {
    useNextArgAs = obj; this
  }

  def to(arg: String): this.type = if (useNextArgAs == Title) {
    title = arg; this
  } else {
    author = arg; this
  }
}

//4
class Network {
  outer =>

  import scala.collection.mutable._

  type Index = mutable.HashMap[String, (Int, Int)] //类型别名

  val indexes: Index = new Index

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]

    override def equals(obj: scala.Any): Boolean = obj match {
      case m: Member => m.name == this.name
      case _ => false
    }
  }

  private val member = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    member += m
    m
  }
}

//9
abstract class Dim[T](val value: Double, val name: String) {
  protected def create(v: Double): T

  def +(other: Dim[T]) = create(value + other.value)

  override def toString = value + " " + name
}

class Seconds(v: Double) extends Dim[Seconds](v, "s") {
  this: Dim[Seconds] =>
  override protected def create(v: Double): Seconds = new Seconds(v)
}

class Meters(v: Double) extends Dim[Meters](v, "m") {
  this: Dim[Meters] =>
  override protected def create(v: Double): Meters = new Meters(v)
}
