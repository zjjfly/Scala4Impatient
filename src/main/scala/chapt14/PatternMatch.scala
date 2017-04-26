package chapt14

import java.util.regex.{Matcher, Pattern}


/**
  * Created by zjjfly on 16/6/17.
  */
object PatternMatch {
  def main(args: Array[String]) {
    //值匹配
    import scala.math._
    val x=0
    x match {
      case Pi=>println("ad")//scala是通过大写开头知道Pi是常量的。变量必须小写字母开头
      case ca=>println(ca)
    }
    import java.io.File._
    val str=":"
    println(pathSeparator)
    str match {
      case `pathSeparator`=>println("oo")//匹配小写字母开头的常量要套反引号
      case _=>println("d")
    }
    //类型匹配
    val m=Map(1->"1",2->"2")
    m match {
      case _:Map[Int,String]=>println(m)
      case _=>println("")
    }

    //数组、列表、元组匹配
    val arr=Array(0,1,2,3)
    val s1: String = arr match {
      case Array(0) => "0"
      case Array(x, y) => x + " " + y
      case Array(0, _*) => "0 ..."
      case _ => "something else"
    }
    println(s1)
    val lst=List(1,2,3,4)
    val s2=lst match {
      case 0::Nil=>"0"
      case x::y::Nil=>x+""+y
      case 1::tail=>"1 ..."
      case _ =>"something else"
    }
    println(s2)
    val pair=(1,0)
    val s3=pair match {
      case (0,_)=>""
      case (y,0)=>y+" 0"
      case _=>"neither is 0"
    }
    println(s3)

    //正则表达式匹配
    val pattern="([0-9]+) ([a-z]+)".r
    "99 bottles" match {
      case pattern(num,item)=>println("I have "+num+" "+item)
    }

    //变量声明中的模式
    val (a,b)=(1,2)
    println(a+","+b)
    val (q,r)=BigInt(19) /% 3//"/%"返回商和余数的对偶
    println(q+","+r)
    val Array(first,second,_*)=Array(1,2,3,4,5)
    println(first)

    //for循环中的模式
    import scala.collection.JavaConversions.propertiesAsScalaMap
    for((k,"")<-System.getProperties) println(k)//只遍历值是空的Property
  }
}
