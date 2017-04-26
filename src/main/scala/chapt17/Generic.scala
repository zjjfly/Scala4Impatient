package chapt17

/**
  * Created by zjjfly on 16/7/7.
  */
object Generic {
  def main(args: Array[String]) {
    val pair: Pair[Int, String] = new Pair(42,"string")
    println(getMiddle(Array("a","b","c")))
  }

  //泛型方法
  def getMiddle[T] (a:Array[T]):T=a(a.length/2)
}
//泛型类
class Pair[T,S](val first:T,val second:S)