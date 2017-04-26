package chapt11

/**
  * Created by zjjfly on 16/6/7.
  */

import java.io.File

import scala.collection.mutable.ArrayBuffer
object Exercise {
  def main(args: Array[String]) {
    //1 +和->的优先级是一样的,所以从左到右顺序执行
    println(3 + 4 -> 5)
    //    println(3 -> 4 + 5)
    //2 scala中,**和*的操作符优先级相同,^比*的优先级低,都不符合需求
    //3
    val fraction1: Frac = new Frac(2, 4)
    val fraction2: Frac = new Frac(1, 3)
    println(fraction1)
    println(fraction1 + fraction2)
    println(fraction2 - fraction1)
    println(fraction2 * fraction1)
    println(fraction2 / fraction1)
    println(fraction1.newFrac())
    //4
    val m1: Money = Money(1, 75)
    val m2: Money = Money(0, 50)
    val m3: Money = Money(0, 50)
    println(m1 + m2 == Money(2, 25))
    println(m2 < m1)
    println(m2 < m3)

    //5
    val table = Table() | "s" | "v" || "ada"
    println(table)
    //7
    val sequence: BigSequence = BigSequence()
    println(sequence(13))
    sequence(0)=1
    sequence(63)=1
    println(sequence.pack())

    //9
    val RichFile(a,b,c,d,e)=new File("words.txt")
  }
}

class Frac(n: Int, d: Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);

  override def toString = num + "/" + den

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

  def +(that: Frac) = new Frac(num * that.den + den * that.num, den * that.den)

  def -(that: Frac) = new Frac(num * that.den - den * that.num, den * that.den)

  def *(that: Frac) = new Frac(num * that.num, den * that.den)

  def /(that: Frac) = new Frac(num * that.den, den * that.num)

  def newFrac() = {
    new Frac(num, den)
  }
}

case class Money(d: Int, c: Int) {
  private val dollar = d + c / 100
  private val center = c % 100

  override def toString = dollar + "." + center

  def +(that: Money) = new Money(dollar + that.dollar, center + that.center)

  def -(that: Money) = new Money(dollar - that.dollar, center - that.center)

  def ==(that: Money) = dollar == that.dollar && center == that.center

  def <(that: Money) = dollar < that.dollar || center < that.dollar
}

case class Table(){
  private[Table] class TR{
    private[TR] var tds=ArrayBuffer[String]()
    def add(s:String)=tds += s
    def toXml()= <tr>{tds.map(s => <td>{s}</td>)}</tr>
  }
  private[Table] var trs=ArrayBuffer[TR]()

  override def toString = <table>{trs map (_.toXml())}</table> toString()

  def |(s:String)={
      trs.lastOption match {
        case Some(t)=>t.add(s)
        case _=>{
          val tr: TR = new TR
          tr.add(s)
          trs += tr
        }
      }
    this
  }
  def ||(s:String)={
    val tr: TR = new TR
    tr.add(s)
    trs += tr
    this
  }
}
case class BigSequence(){
  private var seq=new Array[Short](64)
  for(i<- 0 until 64) seq(i)=0
  def apply(i:Int)={
    seq(i)
  }
  def update(i:Int,s:Short)={
    seq=seq.updated(i,s)
  }
  override def toString=seq.mkString
  //第一个位置是符号位
  def pack()=seq.head*seq.tail.zipWithIndex.filter(_._1==1).map(s=>math.pow(2,s._2).toLong).sum
}

object RichFile{
//  def unapply(f:File)={
//    val path: String = f.getAbsolutePath
//    val i: Int = path.lastIndexOf("/")
//    if (f.isFile)  {
//      val split: Array[String] = path.substring(i + 1).split("\\.")
//      if(split.size == 2 ) Some(path.substring(0,i),split(0),split(1)) else None
//    } else None
//  }
  def unapplySeq(f:File)= {
    val path: String = f.getAbsolutePath
    if (f.isFile) {
      Some(path.split("/").tail)
    }else None
  }
}