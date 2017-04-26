package chapt13

import java.util

import scala.collection.immutable.{StringLike, IndexedSeq}
import scala.collection.mutable

/**
  * Created by zjjfly on 16/6/16.
  */
object Exercise {
  def main(args: Array[String]) {
    val indexsOfChar: Map[Char, mutable.Seq[Int]] = indexsOfChar1("Mississippi")
    println(indexsOfChar)
    val indexs: mutable.Map[Char, mutable.SortedSet[Int]] = indexsOfChar3("Mississippi")
    println(indexs)
    val char = indexsOfChar2("Mississippi")
    println(char)

    //3
    println(removeZeroEle(new util.LinkedList[Int](){add(1);add(2);add(0);add(-1);add(-4);add(0)}))
    //4
    println(getValues(Set("1","2"),Map("1"->1,"2"->2,"3"->3)))

    //5
    println(mkString(List(1,2),","))

    //6
    val lst=List(1,3,5)
    println{
      (lst :\ List[Int]())(_::_)
    }
    println{
      (lst :\ List[Int]())((i,l)=>l :+ i)
    }
    println{
      (List[Int]() /: lst)(_:+_)
    }
    println{
      (List[Int]() /: lst)((l,i)=>i +: l)
    }

    //7
    val prices=List(10,50,100)
    val quantities=List(2,4,1)
    //tupled可以把有两个参数的函数变成参数是二元元组的函数
    println(prices.zip(quantities).map(((x:Int,y:Int)=>x*y).tupled).sum)
    //8
    println(twoDimensionalize(Array(1,2,3,4,5,6),3).toList.map(_.toList))
  }
  class a
  //2 ArrayBuffer
  def indexsOfChar1(s: String) = {
    s.zipWithIndex.foldLeft(Map[Char, mutable.Seq[Int]]()) {
      (m, c) => m + (c._1 -> m.getOrElse(c._1, mutable.Seq[Int]()).:+(c._2))
    }
  }
  //1 SortedSet
  def indexsOfChar3(s: String) = {
    s.zipWithIndex.foldLeft(mutable.Map[Char, mutable.SortedSet[Int]]()) {
      (m, c) => m +=c._1->(m.getOrElse(c._1, mutable.SortedSet[Int]()) += (c._2))
    }
  }
  //1 Vector
  def indexsOfChar2(s: String) = {
    s.zipWithIndex.groupBy(_._1).map(t=>t._1->t._2.map(_._2))
  }
  //3
  def removeZeroEle(ll:util.LinkedList[Int])={
    import scala.collection.JavaConversions.collectionAsScalaIterable
    ll.filterNot(_==0)
  }

  //4
  def getValues(s:Set[String],m:Map[String,Int])={
    s.flatMap(m.get(_))
  }
  //5
  def mkString(i:Iterable[Any],sep:String)={
      i.reduceLeft(_.toString()+sep+_.toString())
  }
  //8
  def twoDimensionalize(d:Array[Double],n:Int)={
     d.grouped(n).toArray
  }
  //10 使用aggregate
}
