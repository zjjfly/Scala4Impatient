package chapt10

import java.io.{IOException, PrintWriter}
import java.util.Date
import javax.swing.JFrame

import chapt9.Exercise.Person

/**
  * Created by zjjfly on 16/6/3.
  */
object Traits {
  def main(args: Array[String]): Unit = {
    //构造对象的时候混入具体实现
    val person: Person with ConsoleLog = new Person with ConsoleLog
    person.say()
    //叠加多个特质
    val p1=new Person with ConsoleLog with TimestampLogger with ShortLogger
    p1.say()
    val p2=new Person with ConsoleLog with ShortLogger with TimestampLogger
    p2.say()
  }
}

trait Logged {
  def log(msg: String) {}
  //工具方法可以依赖抽象方法,然后在混入这个特质的类中具体实现抽象方法。这种做法很常用
  def info(msg: String){log("INFO:"+msg)}
  def warn(msg: String){log("WARN:"+msg)}
  def severe(msg: String){log("SEVERE:"+msg)}
}


trait ConsoleLog extends Logged {
  override def log(msg: String) = {
    println(msg)
  }
}

class Person extends Logged {
  def say() = {
    log("i am not a good person")
  }
}


trait TimestampLogger extends Logged {
  override def log(msg: String): Unit = {
    super.log(new Date() + " " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength=15
  override def log(msg: String): Unit = {
    super.log(if (msg.length<=maxLength) msg else  msg.substring(0,maxLength-3)+"...")
  }
}

//在特质中重写抽象方法
trait DoBefore{
  def excute() //抽象方法
}

trait DoPrintBefore extends DoBefore{
  //excute还是一个抽象方法,需要混入具体的实现,所以需要加上abstract关键字
 abstract override def excute()={
    println("start...")
    super.excute()
  }
}

trait FileLogger extends Logged{
  val out=new PrintWriter("app.log")//这是特质构造器的一部分
  out.println("# "+new Date())//也是特质构造器的一部分

  override def log(msg:String)={out.println(msg);out.flush()}

  //构造器的执行顺序:超类->特质构造器(从左到右)->子类,如果特质有父特质,则先调用父特质的构造器
}

//特质继承类,这个类会成为所有混入该特质的类的超类
trait LoggerException extends Exception with Logged{
  def log(){log(getMessage)}
}
//下面的类UnhappyException,超类是Exception,并实现了一个LoggerException接口
class UnhappyException extends  LoggerException{
  override def getMessage: String = "arggh!"
}
//如果happyException已经扩展了一个类,只要这个类是特质的超类的一个子类就可以混入该特质
class HappyException extends IOException with LoggerException

//scala还有另一种方法可以保证混入该特质的类都是特质扩展的类的子类:自身类型
trait LoggedException extends Logged{
  this:Exception=>
  def log(){log(getMessage)}
}
//下面的代码会报错,因为不符合自身类型要求的类
//class se extends JFrame with LoggedException

//自身类型和带有超类的特质相比,更灵活,它可以解决特质间的循环依赖,它还可以处理结构类型
//结构类型指的是一种只给出类必须拥有的函数或者字段而不是类名的类型
trait LogException extends Logged{
  //自身类型必须有返回值是String的getMessage方法已经一个名为s的String成员
  this:{def getMessage():String;val s:String}=>
  def log(){log(getMessage()+s)}
}

class test extends LogException{
  val s=""
  def getMessage()=""
}
