package chapt9

import java.io.File
import java.net.URL

/**
  * Created by zjjfly on 16/6/1.
  */
object Shells {
  def main(args: Array[String]) {
    import sys.process._
    //结果会被打印到标准输出
    val i: Int = "ls -al .." !;
    //返回0表示成功执行
    println(i)
    //返回结果的字符串
    val re:String="ls -al .." !!;
    println(re)
    //管道操作
    "ls -al .." #| "grep Learn" !;
    //输出到文件
    "ls -al .." #> new File("output.txt") !;
    //追加到文件尾部
    "ls -al .." #>> new File("output.txt") !;
    //把文件内容作为输入
    "grep Learn" #< new File("output.txt") !;
    //从url重定向输入
    "grep Scala" #< new URL("http://horstmann.com/index.html") !;
    //用Process的apply方法构造ProcessBuilder
    val p=Process("ls",new File("/Users/zjjfly/Desktop"),("LANG","en_US"))
    p !;
  }
}
