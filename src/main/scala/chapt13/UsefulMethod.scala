package chapt13

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/6/12.
  */
object UsefulMethod {
  def main(args: Array[String]) {
    //Iterable特质的常用方法
    val list = List("one,1", "two,2", "three,3")
    val l = List(2, 1, 4, 3, 5)
    val m = List(("one", 1), ("two", 2), ("three", 3))
    println(list.head)
    println(list.headOption)
    println(list.last)
    println(list.lastOption)
    println(list.tail)
    println(list.init) //除最后一个元素外的其余部分
    println(list.length)
    println(list.isEmpty)
    println(list.isEmpty)
    println(list.map(_ + 1))
    list.foreach(print)
    println(list.flatMap(_.split(",")))
    println(list.collect { case x if x.length > 6 => x }) //传入一个偏函数,作用和map类似
    //以给定顺序将二元操作应用到所有元素
    println(list.reduceLeft(_.length + "," + _.length))
    println(list.reduceRight(_.length + "," + _.length))
    println(list.foldLeft("21")((a, b) => a.length + "," + b.length))
    println(list.foldRight("21")((a, b) => a.length + "," + b.length))
    //以非特定顺序将二元操作应用到所有元素
    println(list.reduce(_ + "," + _))
    println(list.fold("1")(_ + "," + _))
    //foldLeft/Right函数用于应对第一次二元操作产生的值无法匹配下一次二元操作的情况
    //例如list.reduce(_.length+_.length)这样的代码就会报错,因为二元操作产生的是int类型,这个类型没有length方法,无法进行下一次二元操作
    println(list.foldLeft(0)(_+_.length).toString)
    //aggregate一般用于并行集合中
    println(list.aggregate(0)(_ + _.length, _ + _))
    //sum,product使用的前提是元素可以被隐式转换为带Numeric特质的类
    println(list.map(_.length).sum)
    println(list.map(_.length).product)
    //max,min的前提是元素类型可以转换成带Ordered特质的类
    println(list.map(_.length).max)
    println(list.map(_.length).min)
    println(list.count(_.length > 6)) //返回满足条件的元素个数
    println(list.forall(Set("one,1"))) //所以元素都满足条件返回true,否则false
    //这里的Set会转换成一个predict,原理是scala.collection.GenSetLike这个特质的混入了Function1特质,并实现了apply方法
    //apply方法里面调用contains方法判断元素是否在set中
    println(list.exists(Set("one,1")))

    println(l.filter(_ > 2))
    println(l.filterNot(_ > 2))
    println(l.partition(_ > 2))

    println(l.takeWhile(_ < 4)) //满足表达式的元素(知道遇到第一个不满足的)
    println(l.dropWhile(_ < 4)) //遇到的第一个不满足的元素和它之后的所有元素
    println(l.span(_ < 4)) //上面两种情况的对偶

    println(l take 1)
    println(l drop 1)
    println(l splitAt 1)

    println(list takeRight 2)
    println(list dropRight 2)

    println(l.slice(1, 4)) //index从1到4这个区间内的所以元素,不包含4

    println(list zip List("一", "二", "三"))
    println(list.zipAll(l, "a", "1")) //两个集合的所以元素进行拉练操作,如果长度不一致,用缺省值补上
    println(list.zipWithIndex)

    val grouped: Iterator[List[Int]] = l.grouped(2) //产生一个迭代器,第一个元素是下标0 until n的元素集合,第二个是n until 2n,以此类推
    println(grouped.toList)
    val sliding: Iterator[List[Int]] = l.sliding(2)
    println(sliding.toList) //产生一个迭代器,第一个元素是下标0 until n的元素集合,第二个是1 until n+1,以此类推

    println(list mkString("<", ",", ">"))
    println(list addString(new StringBuilder, "<", ",", ">")) //将该集合变为字符串并放入字符串构造器,返回这个构造器

    println(list.toIterable)
    println(list.toSeq)
    println(list.toIndexedSeq)
    println(list.toArray.toBuffer)
    println(list.toList)
    println(list.toStream)
    println(list.toSet)
    println(m.toMap) //不是什么集合都能转map,需要元素是Tuple2类型的

    val array: Array[Int] = new Array(10)
    l.copyToArray(array)
    println(array.toBuffer)
    l.copyToArray(array, 5, 5)
    println(array.toBuffer)
    val buffer = ArrayBuffer[Int]()
    l.copyToBuffer(buffer.asInstanceOf[mutable.Buffer[Int]])
    println(buffer)

    //seq特质在Iterable特质基础上添加的额外方法
    val seq = Seq(1, 2, 3, 0)
    println(seq.contains(2))
    //containsSlice是否包含指定序列
    println(seq.containsSlice(Seq(2, 1))) //顺序不对,false
    println(seq.containsSlice(Seq(1, 2))) //true
    println(seq.startsWith(Seq(1)))
    println(seq.endsWith(Seq(3)))

    println(seq.indexOf(2))
    println(seq.indexOf(4))
    println(seq.lastIndexOf(2))
    println(seq.indexOfSlice(Seq(1, 2)))
    println(seq.indexOfSlice(Seq(2, 3)))
    println(seq.lastIndexOfSlice(Seq(2, 3)))
    println(seq.indexWhere(_ < 3, 1)) //满足条件的第一个元素的下标,第二个参数是检查起始下标

    println(seq.prefixLength(_ < 3)) //满足条件的最长元素序列(必须是连续的)的长度
    println(seq.segmentLength(_ < 3, 3)) //和上面的功能一样,但多了一个起始下标参数,

    println(seq.padTo(10, 1)) //将第二个参数追加到当前序列,直到序列的长度达到第一个参数值,返回的是一个新的序列

    println(seq intersect Seq(2, 3)) //交集
    println(seq diff Seq(2, 3)) //差集

    println(seq.reverse)

    println(seq.sorted)
    println(seq.sortWith(_ > _))
    println(seq.sortBy(_ % 2))

    println(seq.permutations.toList)//permutations 返回这个序列的所有可能排列的迭代器
    println(seq.combinations(2).toList)//返回指定长度的组合(子序列,不区分顺序)的迭代器
  }
}
