package chapt2

import scala.annotation.tailrec
import scala.collection.immutable.Range.Inclusive

/**
  * Created by zjjfly on 16/5/13.
  */
object Exercise {
  //1
  def sigNum(i: Int) = i > 0 match {
    case true => 1
    case false if i < 0 => -1
    case _ => 0
  }

  def main(args: Array[String]) {
    println(sigNum(1))
    println(sigNum(-4))
    println(sigNum(0))
    //2
    val empty = {}
    println(empty)
    //3
    var y = 2
    val x: Unit = y = 1
    println(x)
    //4
    for (i <- (0 to 10) reverse) print(i + "\t")
    println()
    //5
    def countdown(n: Int): Unit = {
      (0 to n reverse) foreach print
    }
    countdown(11)
    println()
    //6
    var product = 1L
    for (i <- "Hello") product = i * product
    println(product)
    //7
    val reduce: Long = "Hello".map(_.toLong).product
    println(reduce)
    //8、9
    def products(s: String): Long = {
      if (s isEmpty) 1L else s.head.toLong * products(s.drop(1))
    }
    println(products("Hello"))
    def pow(x: Double, n: Int): Double = n > 0 match {
      case true if n % 2 == 0 => pow(x, n / 2) * pow(x, n / 2)
      case true => x * pow(x, n - 1)
      case false if n == 0 =>1
      case false =>1 / pow(x,-n)
    }
    println(pow(2,10))

  }
}



