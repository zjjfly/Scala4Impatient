package chapt17
package Variant


/**
  * Created by zjjfly on 16/7/8.
  */
object Variant {
  def main(args: Array[String]) {
    val p1:Student = new Student("jjzi")
    val p2:Student= new Student("zjj")
    val pair: Pair[Student] = new Pair(p1,p2)
    makeFriends(pair)

    val jjzi: Student1 = new Student1
    val fred=new Person1
    makeFriendWith(jjzi,fred)
  }
  def makeFriends(p : Pair[Person])=println("make friend")

  def makeFriendWith(s:Student1,f:Friend[Student1]){f.befriend(s)}
}

//协变,说明该类型和T按统一的方向型变,例如,A是B的子类,则Pair[A]是Pair[B]的子类
class Pair[+T](val first: T, val second: T)

trait Friend[-T]{
  def befriend(some:T)
}
class Person(val name:String)
class Student(name:String) extends Person(name)

class Person1 extends Friend[Person1]{
  override def befriend(some: Person1): Unit = println("make person friend")
}
class Student1 extends Person1{
  override def befriend(some: Person1): Unit = println("make student friend")
}