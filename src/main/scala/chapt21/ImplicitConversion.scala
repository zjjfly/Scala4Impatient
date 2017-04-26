package chapt21

import java.io.File

import chapt11.Fraction

import scala.io.Source

/**
  * Created by zjjfly on 16/7/14.
  */
object ImplicitConversion {
  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  def main(args: Array[String]): Unit = {
    val re = 3 * Fraction(4, 5)
    println(re.a + "/" +re.b)
    val cont=new File("words.txt").read
    println(cont)
    val quote1: String = quote("wrw")
    println(quote1)
  }
  //2.10引入的隐式类更简单,不需要手动写隐式转换方法
  implicit class RichFile(val from:File){
    def read=Source.fromFile(from).mkString
  }

  def quote(what:String)(implicit delims:Delimiters)={
    delims.left+what+delims.right
  }
}


