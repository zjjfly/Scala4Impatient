package chapt1

import scala.util.Random

/**
  * Created by zjjfly on 16/5/12.
  */
object Basis {
  def main(args: Array[String]): Unit = {
    println("vev"(1))
    println(BigInt(1313)>1)
    val i: Int = "dada" count(_=='d')
    println(i)
    //随机产生素数
    println(BigInt.probablePrime(10,Random))
    //bigint转换成36进制的字符串,是创建随机文件的方法之一
    println((BigInt(Random.nextInt)*BigInt(Random.nextInt)).toString(36))
    //安全的获取字符串的首尾
    println("adad".headOption getOrElse(null))
    println("ad".lastOption getOrElse(null))
  }
}
