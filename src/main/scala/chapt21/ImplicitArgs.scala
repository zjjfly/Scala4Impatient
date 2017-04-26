package chapt21

import chapt11.Fraction
import chapt6.Point

/**
  * Created by zjjfly on 16/7/15.
  */
object ImplicitArgs {
  def main(args: Array[String]) {
    println(smaller(1,3))
    //Ordered的伴生类中,有一个隐式转换可以把任意类型转换成Ordered类型
    // 但需要一个隐式参数的Ordering[T],所以要实现smaller函数可以传入Fraction对象一种方法是在Fraction伴生类中加一个隐式val,该对象类型是Ordering[Fraction]
    // 而Ordering对象也继承了一个隐式转换方法,可以把任意的可以隐式转换成Comparable的类型A转换成对应的Ordering[A]类型,所以还有一个办法是让Fraction类实现Comparable[Fraction]特质
    //还有一个隐式方法可以寻找类型是Comparator[A]的隐式参数,如果有,会把它转成Ordering[A],所以另一个办法是在Fraction伴生对象加一个隐式val,该对象类型是Comparator[Fraction]
    println(smaller(Fraction(1,2),Fraction(1,1)))
    
    val pair: Pair[Int] = new Pair(13,11)
    println("pair.smaller1 = " + pair.smaller1)

    val pair1: Pair[Point] = new Pair( Point(1,3), Point(2,4))
    pair1.smaller1
  }

  def smaller[T](a:T,b:T)(implicit order:T=>Ordered[T]): T ={
    if(a < b) a else b//order不仅是隐式参数,还是一个隐式转换,所以可以在函数体中略去对order的显示调用
  }


}

class Pair[T:Ordering](val first:T,val second:T){
  def smaller1(implicit ord:Ordering[T])={
    if(ord.compare(first,second)<0) first else second
  }
  def smaller2={
    //另一种实现方式,implicitly这个方法在Predef中定义,作用就是捕获隐式对象并返回
    if(implicitly[Ordering[T]].compare(first,second)<0) first else second
  }
  def smaller3={
    import Ordered._//引入Ordered中的Ordering到Ordered的隐式转换
    if(first<second) first else second
  }
}
