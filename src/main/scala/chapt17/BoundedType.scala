package chapt17
package BoundedType

import scala.reflect.ClassTag

/**
  * 类型界定
  * Created by zjjfly on 16/7/7.
  */
object BoundedType {
  def main(args: Array[String]) {
    val p = new Pair("Fred", "Brooks")
    println(p.smaller)
    val pair1: Pair1[Student] = new Pair1(new Student("1"), new Student("2"))
    val replaced: Pair1[Person] = pair1.replaceFirst(new Person("3"))
    val pair2 = new Pair2(1, 2)
    println(new Pair2("a","1").smaller)
    val pair4: Pair4[Int] = new Pair4(1,3)
    println(pair4.smaller)

    val pair5 = new Pair5(new Studentee(""),new Studentee(""))
  }
}

class Pair[T <: Comparable[T]](val first: T, val second: T) {
  //"<:"表示上界,这样有一个缺陷,Int,Long这样的类型不能作为类型参数,因为它们不是Comparable的子类
  def smaller = if (first.compareTo(second) < 0) first else second

}

class Pair1[T](val first: T, val second: T) {
  //这里只能传把R指定为T的上界,因为second可以转成它的父类,但不能转成它的子类
  def replaceFirst[R >: T](newFirst: R) = new Pair1[R](newFirst, second)
}

class Pair2[T <% Comparable[T]](val first: T, val second: T) {
  //"<%"是视图界定,表示T可以通过隐式转换成为Comparable类型。
  //这个类和Pair不同,可以传入Int类型的值做参数,不会报错,因为Int可以隐式转换成RichInt
  def smaller = if (first.compareTo(second) < 0) first else second
}
//使用Ordered特质更好,它在Comparable基础上额外提供了关系操作符
class Pair3[T <% Ordered[T]](val first: T, val second: T) {
  //这个类就可以传入Int类型的值做参数,不会报错
  def smaller = if (first < second) first else second
}

//上下文界定,语法T:M
class Pair4[T:Ordering](val first: T, val second: T) {
  //这个类就可以传入Int类型的值做参数,不会报错
  def smaller = if (implicitly[Ordering[T]].compare(first,second)<0) first else second
}
//同时有上界下界
class Pair5[T >:Studentess <: Person](val first: T, val second: T)

//要求T实现多个特质
class Pair6[T <:Comparable[T] with Serializable with Cloneable](val first: T, val second: T)

//多个视图界定
class Pair7[T <%Comparable[T] <% String](val first: T, val second: T)

//多个上下文界定
class Pair8[T :Ordering :ClassTag](val first: T, val second: T)

class Person(val name: String)

class Student(name: String) extends Person(name)

class Studentess(name:String) extends Student(name)

class Studentee(name:String) extends Studentess(name)
