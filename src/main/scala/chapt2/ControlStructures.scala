package chapt2

import java.text.MessageFormat

import scala.util.control.Breaks._

/**
  * Created by zjjfly on 16/5/12.
  */
object ControlStructures {
  def main(args: Array[String]) {
    //scala退出循环的方法
    //1.使用布尔型的控制变量
    //2.使用嵌套函数 可以从函数中return
    //3.使用Breaks对象中的break方法,它实际是抛出一个异常,所以,当响应时间很重要的时候,避免用这种做法
    breakable(
      for (i<-1 to 10){
        if(i==3){
          break()
        }
      }
    )
    //for推倒式和它的第一个生成器是兼容的
    println(for(c<-"hello";i<-0 to 1) yield (c+i).toChar)
    println(for(i<-0 to 1;c<-"hello") yield (c+i).toChar)

    //:_*可以告诉编译器希望这个参数当做参数序列处理
    def sum(args:Int*)={
      args.toList.sum
    }
    println(sum(1 to 100:_*))
    //当调用变长参数且参数类型是Object的java方法时,需要手工对基本类型进行转换
    val str=MessageFormat.format("The answer to {0} is {1}","everthing",42.asInstanceOf[AnyRef])
    println(str)
    val s=""
    //throw语句的类型是Nothing,它是所有类的子类,所以ifelse分支一个是Nothing类型,那么整个表达式的类型是另一个分支的类型
    val da: String = if (s isEmpty)  "da" else throw new scala.IllegalArgumentException("str can\'t be empty")
    println(da)
  }
}
