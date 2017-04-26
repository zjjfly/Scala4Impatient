package chapt3

import java.io.{File, InputStreamReader, BufferedReader}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting

/**
  * Created by zjjfly on 16/5/16.
  */
object Arrays {
  def main(args: Array[String]) {
    //定长数组
    val a = new Array[String](10)
    val s = Array("hello", "world")
    s(0) = "good bye"
    s.foreach(println)
    //变长数组,一般在你需要一个数组,但不知道数组长度的时候使用
    val b = ArrayBuffer[Int]()
    b += 1
    println(b)
    b +=(1, 2, 6, 7, 5)
    println(b)
    b ++= Array(9, 12, 41)
    println(b)
    //移除最后五个元素
    b.trimEnd(5)
    println(b)
    //dropRight功能类似,但是不改变原对象,而是返回一个新的对象
    b.dropRight(3)
    println(b)

    //插入和删除
    b.insert(2, 6)
    println(b)
    b.insert(2, 7, 8, 9)
    println(b)
    b.remove(2)
    println(b)
    b.remove(2, 3)
    println(b)

    //遍历
    for (i <- 0 until b.length)
      println(i + ":" + b(i))
    //反序遍历
    for (i <- 0 until b.length reverse)
      println(i + ":" + b(i))
    //不需要数组下标的时候
    for (elem <- b)
      println(elem)

    val c = ArrayBuffer(2, 4, 6, 5, 7, 1)
    val sorted: ArrayBuffer[Int] = c.sorted
    val sortWith: ArrayBuffer[Int] = c.sortWith(_<_)
    println(sortWith)
    println(sorted)
    //可以直接对数组排序,但不能对数组缓冲排序
    val d=Array(1,4,6,2,9)
    Sorting.quickSort(d)
    println(d mkString " ")
    d.max
    //min max quickSort方法,元素的类型必须支持比较操作,即含义Ordered特质的类型

    //多维数组,类型是Array[Array[A]]
    //构造一个多维数组
    val matrix=Array.ofDim[Double](3,4)
    //访问多维数组的元素
    matrix(0)(0)=1.2
    //创建不规则的多维数组
    val triangle=new Array[Array[Char]](10)
    for(i<-0 until triangle.length)
      triangle(i)=("*"*(i+1)).toArray
    triangle.foreach{
      array=>println(array mkString "")
    }

    //和java互操作
    import scala.collection.JavaConversions.bufferAsJavaList
    import scala.collection.JavaConversions.asScalaBuffer
    val command=ArrayBuffer("ls","-al")
    val pb=new ProcessBuilder(command)
    pb.directory(new File("/Users/zjjfly"))
    val cmd:mutable.Buffer[String]=pb.command
    println(cmd)
    println(cmd==command)
    val process = pb.start(); // 启动进程
    val is = process.getInputStream(); // 获得输入流
    val isr = new InputStreamReader(is, "utf-8");// 创建输入读流，编码方式为GBK
    val br = new BufferedReader(isr); // 创建读缓冲对象
    var line=""
    while (line != null) {// 循环读取数据
      System.out.println(line);
      line =br.readLine()
    }
  }
}
