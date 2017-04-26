package chapt18

import java.io.{FileReader, FileWriter, PrintWriter}
import java.util.Properties

import chapt10.FileLogger

/**
  * Created by zjjfly on 16/7/11.
  */
object DependenceInjection {
  def main(args: Array[String]) {

  }
}
//1 scala实现一般的依赖注入
trait Auth{this:chapt10.Logged=>
  def login(id:String,password:String):Boolean
}
trait App{
  this:chapt10.Logged with Auth=>
  def doSomething(): Unit ={
    log("do some")
    login("jjzi","12345")
  }
}
trait MockAuth extends Auth{
  this:chapt10.Logged=>
  def login(id:String,password:String):Boolean={
    true
  }
}

object MyApp extends App with FileLogger with MockAuth

//2 蛋糕模式进行依赖注入
trait LoggerComponent{
  trait Logger{def log(msg:String):Unit}
  val logger:Logger
  class FileLogger(file:String) extends Logger{
    val out=new PrintWriter(new FileWriter(file))
    override def log(msg: String): Unit = out.println(msg);out.flush()
  }
}
trait AuthComponent{
  trait Auth{def login(id:String,password:String):Boolean}
  val auth:Auth
  class MockAuth(file:String) extends Auth{
    private val properties: Properties = new Properties()
    properties.load(new FileReader(file))
    import scala.collection.JavaConversions.propertiesAsScalaMap
    import scala.collection.mutable
    val p:mutable.Map[String,String]=properties

    override def login(id: String, password: String): Boolean = p.get("user")==id&&p.get("password")==password
  }
}

object AppComponent extends LoggerComponent with AuthComponent{
   val logger=new FileLogger("test.log")
   val auth=new MockAuth("user.property")
}


