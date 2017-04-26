package chapt17
package Exercise

/**
  * Created by zjjfly on 16/7/10.
  */
object Exercise {
  def main(args: Array[String]) {
    val pair: Pair[Int, String] = new Pair(1, "2")
    println(middle("world"))

    val p1: Pair5[String, Int] = new Pair5("ad", 1)
    //println(p1.swap)
    val p2: Pair5[String, String] = new Pair5("ad", "")
    println(p2.swap)
  }

  def middle[C](c: Iterable[C]): C = c.drop(c.size / 2).head
}

//1
class Pair[T, S](val first: T, val second: S) {
  def swap() = new Pair(second, first)
}

//2
class Pair1[T](val first: T, val second: T) {
  def swap() = new Pair(second, first)
}

//3
class Pair3[T, S] {
  def swap(p: (T, S)) = (p._2, p._1)

}

//4 因为Student是Pair的子类型,可以直接转成Person

//5 RichInt是为了增强Int类型,不是取代,Int更通用

//6 很多函数参数和返回类型中用到了。函数参数中的协变点是反过来的。

//8 这样编译不过,会说协变类型T处在逆变点上,并且newFirst是first的父对象,无法转成first的类型
//class Pair4[+T](var first:T,var second:T){
//  def replaceFirst[R >: T](newFirst:R){this.first =newFirst}
//}

//9
class Pair4[+T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new Pair4[R](newFirst, second)
}

//class NastyDoublePair(first:Double, second:Double) extends Pair4[Double](first ,second){
//  override def replaceFirst(newFirst:Double)={//这里的double实际上只是一个类型符号,和T一样
//    import scala.math._
//    new NastyDoublePair[Double](sqrt(newFirst),second)}
//}

case class Pair5[S, T](val first: S, val second: T) {
  def swap(implicit ev1: S =:= T) = new Pair5(second, first)
}