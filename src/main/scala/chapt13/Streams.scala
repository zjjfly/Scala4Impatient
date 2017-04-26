package chapt13

/**
  * Created by zjjfly on 16/6/16.
  */
object Streams {
  def main(args: Array[String]) {
    val tenOrMore=numsFrom(10)
    println(tenOrMore)
    //stream的方法是懒方法
    val squares=numsFrom(1).map(x=>x*x)
    println(squares)
    //需要调用tail对下一个元素求值
    println(squares.tail)
    //调用take和force得到多个值
    println(squares.take(5).force)
    println(squares(5))
    println(squares)
  }
  //stream类似clojure的lazysequcence
  def numsFrom(n:BigInt):Stream[BigInt]=n #:: numsFrom(n+1)


}
