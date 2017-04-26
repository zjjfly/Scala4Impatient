package chapt5

import java.util.Date

import scala.beans.BeanProperty

/**
  * Created by zjjfly on 16/5/20.
  */
object ClasseDefine{
  def main(args: Array[String]) {
    val myCounter=new Counter
    myCounter.increase()
    println(myCounter.current)

    val fred1=new Person1
    fred1.age=21//调用的是age_=方法
    println(fred1.age)//调用age方法

    //使用Person2,但看上去没有变化
    val fred2=new Person2
    fred2.age=31//调用的是age_=方法
    fred2.age=21//不能变年轻
    println(fred2.age)

    val jjzi: Person = new Person
    //同样会生产name和name_=方法
    jjzi.name="jjzi"
    println(jjzi.name)
    //还会生产javabean风格的getter、setter方法
    jjzi.setName("zijj")
    println(jjzi.getName)
  }
}
class Counter{
  private var value=0
  //改值器带括号
  def increase()={value+=1}
  //取值器不带括号
  def current=value

  //scala中,方法可以访问该类的所有对象的私有字段
  def isLess(other:Counter)=value<other.value
}

class Person1{
  //age字段编译的时候会生成get和set方法
  //在scala中,age的get和set方法分别叫age和age_=
  //scala类中的字段都是私有的
  var age=0
}
class Person2{
  //可以自定义setter和getter方法
  private var privateAge=0

  //变为私有的并改名
  def age=privateAge
  def age_=(newValue:Int): Unit ={
    if(newValue>privateAge)privateAge=newValue//不能变年轻
  }
}
class Message{
  //如果需要一个只读属性,有getter而没有setter,可以把属性声明为val
  val timeStamp=new Date
}

class Counter1{
  //这样声明,这个类的方法就只能访问当前对象的value字段了
  private[this] var value=0
  //改值器带括号
  def increase()={value+=1}
  //取值器不带括号
  def current=value
}

//让scala生产符合javabean规范的getter和setter方法
class Person{
  @BeanProperty var name:String=_
}