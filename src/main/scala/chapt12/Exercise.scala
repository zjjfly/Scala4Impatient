package chapt12

/**
  * Created by zjjfly on 16/6/8.
  */
object Exercise {
  def main(args: Array[String]) {
    println(values(x => x * x, -5, 5))

    //1
    val list = Array(1, 2, 32, 4, 5)
    val max: Int = list.reduceLeft((x, y) => if (x > y) x else y)
    println(max)
    println(factorial(10))
    println(fac(0))
    println(largest1(x=>10*x-x*x,1 to 10))
    println(largest2(x=>10*x-x*x,1 to 10))

    //7
    val pairs=(1 to 10) zip (11 to 20)
    println(pairs.map(adjust(_+_)))

    //8
    val l1 = Array(1, 2, 3)
    val strs=Array("a","ab","abc")
    println(strs.corresponds(l1)(_.length==_))
    val arrays1: Arrays[Int] = new Arrays[Int](l1)
    val arrays2: Arrays[String] = new Arrays[String](strs)
    //9,不用柯里化,类型推断不能用,要显示的声明函数的传入参数的类型
    println(arrays1.corresponds(arrays2,(x:Int,y:String)=>x==y.length))

    //10
    unless(1+1!=2){
      println("hh")
    }
  }

  //1
  def values(fun: Int => Int, low: Int, high: Int) = {
    require(low < high, "low can't bigger than high")
    for (i <- low to high) yield (i, fun(i))
  }

  //3
  def factorial(i: Int) = {
    (1L to i) reduceLeft (_ * _)
  }
  //4
  def fac(i: Int) = {
    (1L to i).foldLeft(1L)((x,y)=>x * y)
  }
  //5
  def largest1(f:Int=>Int,input:Seq[Int])={
    f(input.reduce((a, b)=>if(f(a)>f(b)) a else b))
  }
  //6
  def largest2(f:Int=>Int,input:Seq[Int])={
    input.reduce((a, b)=>if(f(a)>f(b)) a else b)
  }

  def adjust(f:(Int,Int)=>Int)(pair:(Int,Int))={
    f(pair._1,pair._2)
  }

  //10
  def unless(b:Boolean)(block: => Unit )={
    if(!b) block
  }

}
//9
class Arrays[A](val a:Array[A]){
  def corresponds[B](that:Arrays[B],f:(A,B)=> Boolean):Boolean={
    if (a.length != that.a.length) false else{
      a.zip(that.a).forall(x=>f(x._1,x._2))
    }
  }
}

