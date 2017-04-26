package chapt13

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/6/12.
  */
object Operators {
  def main(args: Array[String]) {
    val digits = Set(1, 7, 2, 9)
    //集的并 交 差操作,返回的都是一个新的集合
    val primes = Set(2, 3, 5, 7)
    println(digits union primes) //并集
    println(digits | primes) //并集
    println(digits ++ primes) //并集
    println(digits intersect primes) //交集
    println(digits & primes) //交集
    println(digits diff primes) //差集 调用这个方法的对象有而参数对象没有的元素的集合
    println(digits &~ primes) //差集
    println(digits -- primes) //差集

    //集合的操作,下面返回的都是一个新的集合
    val l = List[Int](1, 2)
    println(l :+ 3)//加到末尾,这个方法只有Seq类型的集合才有
    println(3 +: l)//加到头部,这个方法只有Seq类型的集合才有
    val map=Map(1->"a" ,2->"b")
    println(primes + 0)//把元素加入集合,这个方法只有Set和Map类型的集合有
    println(map + (3->"c"))
    println(primes + (1,2,3))//把多个元素加入集合,这个方法只有Set和Map类型的集合有

    println(primes - 2)//从集合中去除某个元素,这个方法只有Set、Map和ArrayBuffer类型的集合有
    println(map - 2)
    println(primes - (1,2,3))//把多个元素从集合去除,这个方法只有Set、Map和ArrayBuffer类型的集合有

    println(l ++ Set(3, 4))   //把一个集合的元素加入源集合,所有集合都支持这个操作
    println(l ++: Set(3, 4)) //它与++功能基本相同,但有右操作数决定返回的集合的类型,不推荐使用,所有集合都支持这个操作

    println(primes -- Set(2,3))//从集合中减去给定的集合中的元素,支持Set,Map和ArrayBuffer类型的集合
    println(map -- Set(2,3))

    println(3::l)//把某个元素加到一个List头部相当于+:
    println(List(3,4):::l)//把某个List的所有元素加到另一个List头部,相当于++:
    println(List(3,4)++:l)

    //只有可变集合支持的操作,返回的是原来的集合
    val ml=mutable.Set(1,3,5)
    ml += 7//加一个元素
    println(ml)
    ml += (1,3,4)//加入多个元素
    println(ml)
    ml ++= l//加入某个集合的所有元素
    println(ml)
    ml -= 7//减去一个元素
    println(ml)
    ml -= (1,3,4)//减去多个元素
    println(ml)
    ml --= l//减去某个集合的所有元素
    println(ml)

    //ArrayBuffer特有的操作,返回的是原来的集合,但精良不要用这几个操作符
    val array=new ArrayBuffer[Int]
    1 +=: array//给ArrayBuffer头部加一个元素
    println(array)
    List(3,4) ++=: array//给ArrayBuffer头部加一个集合中的所有元素
    println(array)

    //不可变集合,可以在var上使用+=和:+=
    var numbers =Set(1,2,3)
    numbers += 5
    println(numbers)
    var numberVec=Vector(1,2,3)
    numberVec :+= 5
    println(numberVec)
  }
}
