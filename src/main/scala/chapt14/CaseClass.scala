package chapt14

/**
  * Created by zjjfly on 16/6/24.
  */
object CaseClass {
  def main(args: Array[String]) {
    val amt: Amount = Dollar(1)
    val s: String = amt match {
      case Dollar(v) => "$" + v
      case Currency(_, u) => "Oh noes,I got " + u
      case Nothingmore => ""
    }
    println(s)
    val c=Currency(23.13,"EUR")
    val copy: Currency = c.copy()
    println(copy)
    val price: Currency = c.copy(unit="CHF")//copy可以改变参数
    println(price)
    //如果unapply产生的一个对偶,那么可以在case语句中使用中置表达式
    c match {case a Currency u=>println(a+" "+u)}
    //这个特性主要是用来匹配序列的,最典型是::样例类匹配List
    List(1,2,3) match {
      case h :: t=>println("head:"+h+"\n"+"tail:"+t)//这里的::不是List中的::函数,而是scala.collection.immutable.::样例类。
    }

    List(1,3,9) match {
      case 1 +: 3 +: 9 +: Nil=>println("yes")
    }

    //嵌套匹配
    val b:Item=Bundle("PS4",299,Article("Killzone",49.99),Article("Uncharted4",49.99))
    b match {
      case Bundle(_,_,Article(descr,_),_*)=>println(descr)
    }
    b match {
        //@可以把嵌套的值绑到变量
      case Bundle(desc @ _,_,art @ Article(_,_),rest @ _*)=>println(desc+","+rest)
    }
  }
}

abstract class Amount

case class Dollar(value: Double) extends Amount

case class Currency(value: Double, unit: String) extends Amount

case object Nothingmore extends Amount

class Person(var name: String)

//利用case的中置表达式来析构列表
case object +:{
  def unapply[T](input:List[T])={
    if (input.isEmpty) None else Some((input.head,input.tail))
  }
}

abstract class Item

case class Article(desc:String,price:Double) extends Item

case class Bundle(desc:String,disc:Double,item:Item*) extends Item





