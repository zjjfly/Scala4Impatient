package chapt13

/**
  * Created by zjjfly on 16/6/16.
  */

import scala.collection.SeqView
import scala.math._
object Views {
  def main(args: Array[String]) {
    //集合的view方法得到懒视图,作用类似Stream,但它是未被求值的集合(流的第一个元素被求值,和它不一样)。
    val powers: SeqView[Double, Seq[_]] = (0 to 1000).view.map(pow(10,_))
    println(powers)
    println(powers(3))//得到pow(10,3),和流不同的是,视图不会缓存结果,下次调用,还会重新计算
    val force: Seq[Double] = powers.map(1 / _).force//和流一样,强制求值
    println(force)
  }
}
