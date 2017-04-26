package chapt4

import java.awt.Font
import java.util
import java.util.Properties

import scala.collection
import scala.collection.{mutable, SortedMap}

/**
  * Created by zjjfly on 16/5/18.
  */
object Maps {
  def main(args: Array[String]) {
    //    val scores=Map("Alice"->10,"Bob"->3)
    val scores = mutable.Map("Alice" -> 10, "Bob" -> 3)
    //    scores("Alice")=11
    scores("Alice") = 11
    println(scores)
    println(scores getOrElse("Bs", 0))
    //get返回一个Option对象
    println(scores get ("Bs"))
    //增加一个键值对
    scores("John") = 6
    println(scores)
    //添加多个映射
    scores +=("Bob" -> 10, "Fred" -> 7)
    println(scores)
    scores -= "Alice"
    println(scores)
    //遍历
    for ((k, v) <- scores) println(k + " " + v)
    //只需要键或者值
    scores.keySet
    for (v <- scores.values) println(v)
    //交换键值
    val swaped: mutable.Map[Int, String] = for ((k, v) <- scores) yield (v, k)
    println(swaped)

    //树形映射,scala只有不可变的
    val s = SortedMap("Alice" -> 10, "Bob" -> 3)
    //如果要可变树形映射,可以用java的TreeMap
    //如果要按插入顺序存放键值,可以用LinkedHashMap
    val months = mutable.LinkedHashMap("January" -> 1, "Feburary" -> 2, "March" -> 3)
    println(months)
    //和java互操作
    import scala.collection.JavaConversions.{mapAsScalaMap, mapAsJavaMap, propertiesAsScalaMap}
    val map: mutable.Map[String, Int] = new util.TreeMap[String, Int]()
    //转换java.util.Properties到Map[String,String]
    val properties: mutable.Map[String, String] = System.getProperties
    //scala映射传递给预期是java映射的方法
    import  java.awt.font.TextAttribute._
    val attrs=Map(FAMILY->"Serif",SIZE->12)
    val font=new Font(attrs)
  }
}
