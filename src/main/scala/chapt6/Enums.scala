package chapt6

/**
  * Created by zjjfly on 16/5/23.
  */
//scala产生枚举需要继承Enumeration这个类
object Enums  extends Enumeration{
  //定义一个扩展了Enumeration类的对象并调用Value方法初始化枚举中的所以可选值
//  val Red,Yello,Green=Value
  //Value函数也可以传入ID和名称
  val Red=Value(0,"Stop")
  val Yellow=Value(10)
  val Green=Value("Stop")
  type TrafficLight=Value
  def main(args: Array[String]) {
    //如果枚举的对象的名称太长,可以用import 对象名._
//    import chapt6.Enums._
    //枚举的类型实际上是所属对象中的Value类
    println(Green.isInstanceOf[Enums.Value])
    //如果想让枚举的类型更具含义,可以用别名
    println(Green.isInstanceOf[Enums.TrafficLight])

    println(Test.doWhat(Green))

    //枚举的ID可以通过id方法返回,名称通过toString方法返回
    println(Red)
    println(Red.id)
    //还可以通过名称和id查找定位
    println(Enums(10))
    //如果名称相同,返回第一个是此名称的枚举
    println(Enums.withName("Stop").id)
  }
}

object Test extends App{
  //需要import才能使用类型别名
  import Enums._
  def doWhat(color:TrafficLight)=color match {
    case Red=>"stop"
    case Yellow=>"hurry up"
    case _=>"go"
  }
}