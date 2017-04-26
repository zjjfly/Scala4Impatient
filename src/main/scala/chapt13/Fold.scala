package chapt13

import scala.collection.immutable.IndexedSeq

/**
  * Created by zjjfly on 16/6/16.
  */
object Fold {
  def main(args: Array[String]) {
    //可以使用/:操作符来写foldleft操作
    (0 /: List(1, 2)) (_ + _) //"/:"是一个右操作符,调用者实际是右边的集合
    (List(1, 2) :\ (0)) (_ + _) //":\"相当于foldRight

    //折叠有时候可以作为循环的替代
    //计算某个字符串中字母的出现频率
    val m = (Map[Char, Int]() /: "Mississippi") {
      (m, c) => m + (c.toLower -> (m.getOrElse(c.toLower, 0) + 1))
    }
    println(m)

    //scanLeft和scanRight方法将折叠和映射操作结合在一起,得到一个包含所有中间结果的集合
    val left: IndexedSeq[Int] = (1 to 10).scanLeft(0)(_ + _)
    println(left)

    //zipWithIndex在计算某种属性的元素的下标的时候很有用
    println("Scala".zipWithIndex.max._2)//获取字符串中最大的char的下标
  }
}
