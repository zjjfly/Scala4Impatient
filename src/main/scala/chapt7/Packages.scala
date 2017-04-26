package chapt7
package test

import com.horstmann.impatient.people.Devil

//文件顶部标记法,是最常用的方法,chapt7下的常用是可见的,和包嵌套的规则一致


/**
  * Created by zjjfly on 16/5/26.
  */
object Packages {
  def main(args: Array[String]): Unit = {
    new com.zijj.Level()//scala的包名是相对的
    new Man
    Devil.kill()
//    Devil.desc 无法调用
//    Devil.say 无法调用
  }
}


package com {
  class Princess{}
  package collection {

    object Exp {
    }

  }

}

package com {
  package zijj {

    class Level {}
    package scalas {

      class Hero {
        def getLv() = {
          new Level() //包可以嵌套,可以访问上层包的类
          new _root_.scala.collection.mutable.ArrayBuffer[Int]()//绝对包名,以_root_开始
          new Princess//com.下的成员都可见
        }
      }

    }

  }

}

package com.horstmann.impatient{//串联式包名,其实和在文件顶部的package语句是等价的

  package people{

    object Devil{
      def kill() ={
//        new Princess 这句话会报错,因为com和com.horstmann的成员在这里不可见
      }
//      限定了只对people包下的成员(包括子包)可见
      private[people] def desc="haha"
      //可以将可见度延展到上层包
      private[com] def say="no"
    }
  }

}