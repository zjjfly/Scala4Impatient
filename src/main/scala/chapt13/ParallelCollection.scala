package chapt13

import scala.collection.GenTraversable
import scala.collection.parallel.immutable.{ParRange, ParSeq}

/**
  * Created by zjjfly on 16/6/16.
  */
object ParallelCollection {
  def main(args: Array[String]) {
    val sum: Long = (1L to 1000000).par.sum //par方法会产生当前集合的并行实现,该实现会尽可能的并行执行集合方法
    println(sum)
    //下面代码打出的数字是按照作用于该任务的线程产出的顺序输出的
    for (i <- (1 to 1000) par) println(i + "" + Thread.currentThread())

    //for yield循环中,结果是依次组装的
    val strings: ParSeq[String] = for (i <- (0 to 100) par) yield i + ""
    println(strings)

    //如果并行计算修改一个共享的值,结果会无法预知
    var count = 0
    for (c <- (1 to 1000) par) {
      if (c % 2 == 0) count += 1
    }
    println(count)//应该是500,但实际求出来的不对
    //从并行集合转回一般的集合
    val toTraversable: GenTraversable[Int] = (1 to 100).par.toList
    println(toTraversable)
  }
}
