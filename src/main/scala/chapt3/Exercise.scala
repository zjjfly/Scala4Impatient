package chapt3

import java.util
import java.util.TimeZone

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by zjjfly on 16/5/17.
  */
object Exercise {
  def main(args: Array[String]) {
    //1
    val a = randArray(11)
    println(toString(a))
    //2
    var tmp = 0
    for (i <- a.indices by 2 if i < a.length - 1) {
      tmp = a(i)
      a(i) = a(i + 1)
      a(i + 1) = tmp
    }
    println(toString(a))
    //3
    val swaped = for (i <- a.indices) yield i % 2 == 0 match {
      case true if i == a.length - 1 => a(i)
      case true => a(i + 1)
      case false => a(i - 1)
    }
    println(toString(swaped.toArray))

    //4
    val b = Array(1, -3, 2, 0, -5, 4)
    val ints: Array[Int] = b.filter(_ > 0) ++ b.filter(_ == 0) ++ b.filter(_ < 0)
    println(toString(ints))
    //5
    val c = Array(1.3, 3.1, 5.6)
    val aver = BigDecimal(c.sum) / c.length
    println(aver)
    //6
    b.reverse
    val d=ArrayBuffer(1,3,4)
    val reversed: ArrayBuffer[Int] = d.reverse
    //7
    b.distinct
    d.distinct
    //8
    removeArray(b)
    //9
    val ds: Array[String] = TimeZone.getAvailableIDs
    println(toString(ds))
    val americaZone: Array[String] = ds.filter(_ startsWith "America/")
    println(toString(americaZone))
    val sorted: Array[String] = americaZone.map(_ replaceFirst("America/","")).sorted
    println(toString(sorted))
    //
    import java.awt.datatransfer._
    import scala.collection.JavaConversions.asScalaBuffer
    val flavors=SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
    val flavor: mutable.Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
    println(flavor)
  }

  def randArray(n: Int) = {
    val array = new Array[Int](n)
    for (i <- array.indices)
      array(i) = Random.nextInt(n)
    array
  }

  def toString[A](s: Array[A]): String = {
    s mkString("(", " ", ")")
  }

  def removeArray(array: Array[Int]){
    val t = array.toBuffer
    val index = for (i <- 0 until array.length if array(i) < 0) yield i
    val right = index.reverse.dropRight(1)
    right.foreach(t.remove(_))
    print(t)
  }
}
