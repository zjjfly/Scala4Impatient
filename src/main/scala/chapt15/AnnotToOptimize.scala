package chapt15

import scala.annotation.{elidable, switch, tailrec}

/**
  * Created by zjjfly on 16/6/28.
  */
object AnnotToOptimize {
  //@tailrec尾递归优化
  @tailrec
  def sum(xs: Seq[Int], partial: BigInt): BigInt = {
    if (xs.isEmpty) partial else sum(xs.tail, xs.head + partial)
  }

  //scala实现蹦床
  import scala.util.control.TailCalls._

  def evenLength(xs: Seq[Int]): TailRec[Boolean] = if (xs.isEmpty) done(true) else tailcall(oddLength(xs.tail))

  def oddLength(xs: Seq[Int]): TailRec[Boolean] = if (xs.isEmpty) done(false) else tailcall(evenLength(xs.tail))

  def main(args: Array[String]) {
    println(evenLength(Seq(2, 3, 4, 5)).result)

    //跳转表,java中把switch语句编译成跳转表,scala也会尝试对match语句生成跳转表
    //途观要检查scala的match语句是否真的被编译成跳转表,可以使用@switch
    val n: Int = 0
    val re=(n: @switch) match {
      case 0 => "zero"
      case _ => "?"
    }
    println(re)
    println(inline("afa"))

    //scala的Predef中有一个可被忽略的assert方法,可以通过在编译的时候加上-Xelide below 2001/MAXIMUM来禁用
    assert("a"=="aadad","acaa")

  }
  //内联,将方法调用语句替换成被调用的方法体
  //告诉编译器内联
  @inline def inline(x:String)=x.toUpperCase
  //告诉编译器不要内联
  @noinline var s="ada"

  //可省略方法,使用@elidable注解,使用scalac -Xelide-below 500 my.scala,其中数字如果比注解中的参数大,则该方法不会编译
  import scala.annotation.elidable._
  @elidable(FINE) def test=""

  //基本类型特殊化,为函数自动生成基本类型的版本,提高性能
  def allDifferent[@specialized T](x:T,y:T,z:T)=""//给所以基本类型都生成一个方法

  def allDiff[@specialized(Int,Double) T](x:T,y:T,z:T)=""//给指定的基本类型生成方法
}