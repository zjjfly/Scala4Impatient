package chapt11

import chapt15.Comparator

import scala.util.{Failure, Success, Try}

/**
  * Created by zjjfly on 16/6/7.
  */
object Unapplys {
  def main(args: Array[String]) {
    //提取器用于变量定义
    var Fraction(a,b)=Fraction(1,2)*Fraction(1,2)
    println(a+"/"+b)
    var Number(i)="1131"
    println(i)
    val author="zi jun jie"
    author match {
      case Name(first,last @ IsCompound())=>println("ok")
      case Name(first,last)=>println("good")
    }
    //可以匹配并提取任意数量的变量
    author match{
      case Author(first,last)=>println("f l")
      case Author(first,middle,last)=>println("f m l")
      case Author(first,"junjie","jie",last)=>println("hehe")
    }
  }
}
class Fraction(val a:Int,val b:Int) extends Comparable[Fraction]{
  def *(that:Fraction)=new Fraction(a*that.a,b*that.b)
  override def toString=a + "/" +b

  override def compareTo(o: Fraction): Int = a*o.b-b*o.a
}

object Fraction{
  //提取器.模式匹配可能会失败,所以提取器返回的是一个Option
  //如果这个类的参数是多个,则Option里是一个元组,如果是一个,则直接把这个参数放入Option
  def unapply(input:Fraction)=if (input.b == 0) None else Some((input.a,input.b))

  def apply(a:Int,b:Int)=new Fraction(a,b)

//  implicit val fraction=new Ordering[Fraction] {
//    override def compare(x: Fraction, y: Fraction): Int = x.a*y.b-x.b*y.a
//  }
//  implicit val comparator=new Comparator[Fraction] {
//   override def compare(o1: Fraction, o2: Fraction): Int = o1.a*o2.b-o1.b*o2.a
//  }
}

object  Number{
  //提取器可以从任何类型的对象中提取信息
  def unapply(input:String)={
    Try(input.trim.toInt) match {
      case Success(x)=>Some(x)
      case Failure(e)=> None
    }
  }
}
object  Name{
  //提取器可以从任何类型的对象中提取信息
  def unapply(input:String)={
    input.indexOf(" ") match {
      case -1 =>None
      case n=>Some((input.substring(0,n),input.substring(n+1)))
    }
  }
}
//提取器可可以值测试输入而不真的将值提取出来
object  IsCompound{
  def unapply(input: String)=input.contains(" ")
}

object Author{
  //提取任意长度的值的序列,返回的是一个Option[Seq[A]],A是被提取的值的类型
  def unapplySeq(input:String)={
    if(input.trim=="") None else Some(input.trim.split("\\s+"))
  }
}

