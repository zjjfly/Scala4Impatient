package chapt4

import java.util.{Properties, Calendar}

import scala.beans.BeanProperty
import scala.collection.mutable

/**
  * Created by zjjfly on 16/5/19.
  */
object Exercise {
  def main(args: Array[String]) {
    //1
    val equipments = Map("sword" -> 100, "gun" -> 200)
    val discounted1: Map[String, Double] = equipments.map(s => (s._1, s._2 * 0.9))
    val discounted2 = for ((k, v) <- equipments) yield (k, v * 0.9)
    println(discounted1)
    println(discounted2)
    //2,3,4,5
    val content: String = io.Source.fromFile("words.txt").getLines().mkString
    val words: Array[String] = content.split("""\W+""")
    println(words.toList)
    val by: Map[String, Int] = words.groupBy(w => w).map(s => (s._1, s._2.size))
    println(by)

    //6
    val weekdays: mutable.LinkedHashMap[String, Int] = new mutable.LinkedHashMap[String, Int]
    weekdays += ("Sunday" -> Calendar.SUNDAY) += ("Monday" -> Calendar.MONDAY) += ("Tuesday" -> Calendar.TUESDAY) += ("Wensday" -> Calendar.WEDNESDAY)
    for ((k, v) <- weekdays) println(k + "=" + v)

    //7
    import scala.collection.JavaConversions.propertiesAsScalaMap
    val properties: mutable.Map[String,String] = System.getProperties
    val maxLength: Int= properties.map(s=>s._1+"|"+s._2).maxBy(_.length).length
    println(maxLength)
    //8
    def minmax(values:Array[Int])={
      values.min->values.max
    }
    println(minmax(Array(1,4,5,7,9)))
    //9
    def Iteqgt(values:Array[Int],v:Int)={
      val sorted: Array[Int] = values.sorted
      val lt: Int = sorted.count(_<v)
      val gt: Int = sorted.count(_>v)
      val eq: Int = sorted.count(_==v)
      (lt,eq,gt)
    }
    println(Iteqgt(Array(1,4,5,7,6,9),6))
    //10,因为字符传可以看做是Char[]
    println("Hello" zip "World")
  }
}
