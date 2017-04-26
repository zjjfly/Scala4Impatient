package chapt5

import java.io.FileReader
import java.util.Properties

/**
  * Created by zjjfly on 16/5/20.
  */
object Constructors {
  def main(args: Array[String]) {
    //调用三个不同的构造器初始化对象
    val p1=new People
    val p2=new People("jjzi")
    val p3=new People("jjzi",26)

    val city=new City("Suzhou",2500)

//    new Animal("dd")//这行代码会报错
  }
}
class People {
  private var name = ""
  private var age=0
  //辅助构造器名称都是this
  def this(name:String){
    //辅助构造器必须以一个先前已定义的其他辅助构造器或主构造器的调用为开始
    this()
    this.name=name
  }
  def this(name:String,age:Int){
    this(name)
    this.age=age
  }
}
//主构造器,和类定义交织在一起
class City(val name:String,private var age:Int){
  //类中的除了字段和方法之外的语句都会在主构造器调用的时候执行
  //这个特性对于需要在构造的时候配置某个字段的情况很有用
  println("just constructed a person")
}
//不同的主构造器参数对应的生成的字段和方法可以看书中的表5-2

//可以在类名后加private使主构造器私有
class Animal private(val name :String)