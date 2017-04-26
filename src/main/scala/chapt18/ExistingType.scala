package chapt18

import chapt18.PathAndAlias.Network

/**
  * Created by zjjfly on 16/7/11.
  */
object ExistingType {
  def main(args: Array[String]) {
  }
  //存在类型加入scala是为了和java的类型通配符兼容,语法forSome{...}
  def dosome(array: Array[T] forSome {type T <: Ordered[String]})={
    array.foreach(println)
  }
  //和上面的方法等价
  def dosome1(array: Array[_ <: Ordered[String]])={
    array.foreach(println)
  }
  //forSome表示更复杂的关系
  def dosome2(m:Map[T,U] forSome {type T;type U <: T})=m.size

  //forSome中使用val,这个方法接受相同网络的成员,但拒绝不同网络的成员
  def process[M <: n.Member forSome {val n:Network}](m1:M,m2:M)=(m1,m2)
}
