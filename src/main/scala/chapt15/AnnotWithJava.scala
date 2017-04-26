package chapt15

import java.io.IOException

import scala.annotation.{varargs, strictfp}
import scala.beans.{BooleanBeanProperty, BeanProperty}
import scala.collection.mutable
import scala.io.Source

/**
  * Created by zjjfly on 16/6/28.
  */
class AnnotWithJava {
  @volatile var done = false //和java的volatile关键字用法相同

  @transient var recentLookups = new mutable.HashMap[String, String]//和java的transient关键字用法相同

  @strictfp def calculate(x:Double)=x*x
  //和java的strictfp关键字作用相同,让方法使用IEEE的double值进行浮点计算,而不是使用80哦日扩展精度。
  //计算结果会更慢,但可移植性更高

  @native def win32RegKeys(root:Int,path:String):Array[String]//和native关键字用法相同
}

//scala使用注解@remote而不是Remote接口来标记可被远程的对象
@remote class Employee

//可序列化的类,用注解来指定序列号版本
@SerialVersionUID(11411212)
class Employer extends Serializable{

}


class Book{
  //受检查异常
  //没有这个注解,Test.java里无法捕获到IOException这个异常
@throws(classOf[IOException])  def read(filename:String):String={
    val string: String = Source.fromFile(filename).getLines().mkString
    string
  }

  //变长参数
  //scala编译器会把下面的变长参数变成Seq[String],这样java调用起来很麻烦
  //加上@varargs,编译器会生成一个void process(String... args)的java方法,这个方法会把args数组包装成seq,然后调用实际的那个scala方法
  @varargs def process(args:String*)={args.mkString}

  //javabean
  //@BeanProperty可以生成javabean风格的setter和getter方法
  @BeanProperty var name:String=_
  //@BooleanBeanProperty生成带is前缀的getter方法,用于Boolean
  @BooleanBeanProperty var borrowed:Boolean=_
}
