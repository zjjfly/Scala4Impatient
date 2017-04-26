package chapt7

import scala.util.Random


/**
  * Created by zjjfly on 16/5/16.
  */
object Exercise {
  def main(args: Array[String]) {
    println(random.random.nextInt())
    Random.setSeed(13)
    //6,7
    import collection.mutable.HashMap
    val map: HashMap[String, String] = new HashMap[String, String]()
    import java.util.{HashMap => JavaHashMap}
    val javaMap = new JavaHashMap[String, String]()
    javaMap.put("1", "1")
    javaMap.put("2", "2")
    javaMap.put("3", "3")
    for (key <- javaMap.keySet().toArray) {
      map += key.toString->javaMap.get(key)
    }
    println(map)
    //8 不是一个好主意,它们下面没有类
    //9
    import java.lang.System
    val userName: String = System.getProperty("user.name")
    print("password:")
    import io.{StdIn}
    val password: String = StdIn.readLine()
    if (password=="secret") println(s"hello,$userName") else System.err.println("password is not correct")
    println(password)
    //10 基本类型的封装类

  }
}

//1
package cn {

  class AnHui
  package JiangSu {

    class Suzhou {
      def nb = new AnHui
    }

  }

}

package cn.JiangSu {
  //和上面的区别是,cn下的成员不再可见,需要引入
  import chapt7.cn.AnHui

  class Nanjing {
    def nb = new AnHui
  }

}

//2
package com {

}

//3
package random {


  object random {

    var seed: Int = _ //这里的占位符表示默认值,int的默认值是0

    val a = BigDecimal(1664525)

    val b = BigDecimal(1013904223)

    val n = 32


    def nextInt(): Int = {
      val temp = (seed * a + b) % BigDecimal(2).pow(n)
      seed = temp.toInt
      seed
    }

    def nextDouble(): Double = {
      val temp = (seed * a + b) % BigDecimal(2).pow(n)
      seed = temp.toInt
      temp.toDouble
    }
  }

}

//4 jvm不支持

//5 限定giveRaise方法对于com包中的成员可见
