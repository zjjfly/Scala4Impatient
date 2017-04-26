package chapt13

import scala.collection.immutable.Seq
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/6/8.
  */
object Collections {
  def main(args: Array[String]) {
    val itr = Iterable(1, 2) //实际产生的是List对象
    println(itr.isInstanceOf[List[Int]])
    println(digit(1231))
    //linkedlist,和不可变的list类似,但可以修改头尾
    val lst = mutable.LinkedList(1, -2, 7, 9)
    var cur = lst
    while (cur != Nil) {
      if (cur.elem < 0) cur.elem = 0
      cur = cur.next
    }
    println(lst)

    var cu = lst
    while (cu != Nil && cu.next != Nil) {
      cu.next = cu.next.next
      cu = cu.next
    }
    println(lst)

    val ints: Set[Int] = Set(1, 2, 3, 4, 5, 6) + 3
    println(ints)
    for (i <- ints) println(i) //set不保留元素插入顺序,默认的set实现是hashset
    val weekdays = mutable.LinkedHashSet("Mo", "Tu", "We", "Th", "Fr")
    for (i <- weekdays) println(i) //LinkedHashSet可以记住元素插入顺序
    val sortedSet: mutable.SortedSet[Int] = mutable.SortedSet(1, 2, 3, 4, 6, 5)
    println(sortedSet) //SortedSet会对元素排序,实际产生的是TreeSet对象

    val digits = Set(1, 7, 2, 9)
    println(digits contains 0)
    println(digits forall Set(1, 2))
  }

  def digit(n: Int): Set[Int] = {
    if (n < 0) digit(-n)
    else if (n < 10) Set(n)
    else digit(n / 10) + (n % 10)
  }
}
