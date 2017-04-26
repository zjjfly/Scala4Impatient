package chapt17
package LimitedType

/**
  * Created by zjjfly on 16/7/8.
  */
object LimitedType {
  def main(args: Array[String]) {
    val pair = new Pair(new Person(""),new Person("2"))
    println(pair.smaller)
    val m=Map("1"->1)
    val maybeInt1: Option[Int] = m.get("2")
//    val i=maybeInt1.orNull//报错,由于orNull中有一个限定Null<:<A,所以A不能是值类型
//    firstLast(List(1,2,3)) //报错,由于类型推断无法判断A是什么类型
    println(firstLast(List(1,2,3)))
  }
//  def firstLast[A,C <:Iterable[A]](it:C)=(it.head,it.last)
  //类型约束的另一个作用是改进类型推断,下面的方法可以推断出A,C的类型,而上面的不行
  def firstLast[A,C](it:C)(implicit ev:C <:< Iterable[A])=(it.head,it.last)
}
class Pair[T](val first: T, val second: T){
  //类型约束的一个作用是,让我们可以在泛型类中定义只能在特定条件下使用的方法
  //"<:<"表示T是否是Ordered[T]的子类,ev是隐式类型证明参数
  def smaller(implicit ev: T <:< Ordered[T])=
    if(first < second) first else second
}

class Person(val name:String) extends Ordered[Person]{
  override def compare(that: Person): Int = name.compareTo(that.name)
}
//关于类型约束,可以参考:http://hongjiang.info/scala-type-contraints-and-specialized-methods/