package chapt13

import scala.collection.mutable

/**
  * Created by zjjfly on 16/6/16.
  */
object ThreadSafeCollection {
  def main(args: Array[String]) {
    val scores=new mutable.HashMap[String,Int] with mutable.SynchronizedMap[String,Int]
    //这些同步的特质已经过时,考虑使用java的同步集合
  }
}
