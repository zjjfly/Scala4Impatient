package chapt8

/**
  * Created by zjjfly on 16/5/27.
  */
object Abstracts {
  def main(args: Array[String]) {
    println(new Ant().env.length)//0
    //得到0的原因是,超类的env初始化用的range是实际是调用的range的getter方法
    //而当它调用的时候,子类的range还没有初始化,是默认值0,所以得到是一个长度为0的数组

    println(new Bird().env.length)

    //编译器运行把任何值替换为()
    printAny("Hello")
    printAny(())
    printUnit("Hello")
  }

  def printAny(x:Any){println(x)}
  def printUnit(x:Unit){println(x)}
}

abstract class Boy{
  //编译后,会产生id和name的getter方法,以及一个name的setter方法,但没有这两个字段
  val id:Int
  var name:String
}
class Prince extends Boy{
  //重写超类的方法和字段可以不加override
   val id: Int = 0
   var name: String = ""
}

class Creature{
  def range:Int=10
  val env:Array[Int]=new Array[Int](range)
}

class Ant extends Creature{
   override val range=2
}

//可以使用提前定义语法解决问题
class Bird extends {
  override val range=5
} with Creature

//对象比较需要实现equals和hashCode方法
class Item(val desc:String,val price:Double){

  def canEqual(other: Any): Boolean = other.isInstanceOf[Item]

  override def equals(other: Any): Boolean = other match {
    case that: Item =>
      (that canEqual this) &&
        desc == that.desc &&
        price == that.price
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(desc, price)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
