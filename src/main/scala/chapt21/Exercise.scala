package chapt21

import java.lang.Number


/**
  * Created by zjjfly on 16/7/15.
  */
object Exercise {
  def main(args: Array[String]) {
    //1.Predef的ArrowAssoc隐式类,,可以把任意类型的对象self隐式转换成ArrowAssoc类,这个类有一个->方法
    //这个方法可以传入任意类型的一个参数y,返回Tuple2(self,y)
    val p = 1 -> 2
    println(120.1 +% 1L)
    println(12 !)
    println("abvc".map(_.toUpper))
    println("abvc".map(_.toInt))
  }
  //2
  implicit class AddPercent[A<% BigDecimal](i:A){
    def +%[B](p:B)(implicit ev:B => BigDecimal)={
      i  * (p + 100)/ 100d
    }
  }
  //3
  implicit class Ints(i:Int){
    def !(implicit ev:Int=>Ints):Long= if(i==1) 1*i else i * ((i-1).!)
  }

  //4略

  //5 见ImplicitArgs.scala
  //6 见chapt6/Exercise.scala中的Point类的伴生对象

  //7,8,9略
  //10 因为第一个map传入的方法是Char=>String ,所以需要的CanBuilderFrom是[Repr,Char,String]
  //在Predef中,有一个StringCanBuildFrom满足这个条件,所以最后是StringBuilder的result
  //而第二个转入的是Char=>Int,这次使用的是LowPriorityImplicits的StringCanBuildFrom
}
