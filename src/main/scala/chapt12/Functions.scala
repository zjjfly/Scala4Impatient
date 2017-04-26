package chapt12

/**
  * Created by zjjfly on 16/6/8.
  */

import scala.annotation.tailrec
import scala.math._

object Functions {
  def main(args: Array[String]) {
    val num = 3.14
    val fun = ceil _ //技术上,_把ceil方法变成了函数,scala中无法直接操作方法,只能操作函数

    //柯里化
    val a = Array("1", "2")
    val b = Array("1", "2")
    val corresponds: Boolean = a.corresponds(b)(_.equalsIgnoreCase(_))
    println(corresponds)

    val sum2=sums(2) _
    println(sum2(2))

    //自定义控制结构
    var x=10
    util(x<0){
      x -= 1
      println(x)
    }

    val of: Int = indexOf("asdaaf",'s')
    println(of)
  }

  //自定义控制结构
  @ tailrec
  def util(conditon: => Boolean)(block: => Unit) :Unit={
    if (!conditon) {
      block
      util(conditon)(block)
    }
  }

  def sums(x:Int)(y:Int)=x*y
  //return的用处,在一个匿名函数中返回值给一个带名函数
  def indexOf(s:String,ch:Char):Int={
    var i=0
    util(i == s.length){
      if(s(i)==ch) return i else i+= 1
    }
    -1
  }
}


