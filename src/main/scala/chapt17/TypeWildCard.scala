package chapt17
package TypeWildCard

import java.util
import java.util.Comparator
/**
  * Created by zjjfly on 16/7/8.
  */
object TypeWildCard {
  def main(args: Array[String]) {
    makeFriend(new Pair(new Student,new Student))
    val i=min(new Pair(1,2))(new Comparator[Int] {
      override def compare(o1: Int, o2: Int): Int = if(o1>o2) o2 else o1
    })
    println("i = " + i)
  }
  def makeFriend(p:Pair[_ <: Person])=println("make friend")
  def min[T](p:Pair[T])(comp:util.Comparator[_>:T])=comp.compare(p.first,p.second)

}

class Pair[T](var first:T,var second:T)

class Person
class Student extends Person