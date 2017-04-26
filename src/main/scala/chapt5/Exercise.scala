package chapt5

import scala.beans.BeanProperty

/**
  * Created by zjjfly on 16/5/20.
  */
object Exercise {
  def main(args: Array[String]) {
    //6
    val jjzi: PersonX = new PersonX(12)
    println(jjzi.age)
    val ji: PersonX = new PersonX(-12)
    println(ji.age)
    //7
    val name: PersonName = new PersonName("junjie zi")
    println(name.firstName+name.lastName)
  }
}

//1
class Count {
  private var value = 0

  def increase(): Unit = if (value < Int.MaxValue) value += 1 else value

  def current = value
}

//2
class BankAccount(val balance: Long) {
  def deposit() {}

  def wirthdraw() {}
}

//3
class Time1(val hours: Int, val minutes: Int) {
  def before(other: Time1) = hours < other.hours || (hours == other.hours && minutes < other.minutes)

  override def toString = {
    hours + ":" + minutes
  }
}

//4
class Time2(val hours: Int, val minutes: Int) {
  def before(other: Time2) = hours < other.hours || (hours == other.hours && minutes < other.minutes)

  override def toString = {
    hours * 60 + minutes + ""
  }
}

//5
class Student(@BeanProperty name: String, @BeanProperty var id: Long)

//6
class PersonX(a: Int) {
  var age: Int = 0
  if (a < 0) this.age = 0 else this.age = a
}
//7
class PersonName(name:String){
  val firstName=name.split(" ")(0)
  val lastName=name.split(" ")(1)
}

//8
class Car(val marker:String,val typeName:String,val year:Int = -1,var number:String="")

//10
class Employee(){
  val name:String=name
  val salary:Double=salary
}