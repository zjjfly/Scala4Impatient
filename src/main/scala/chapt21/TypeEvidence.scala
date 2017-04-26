package chapt21

/**
  * Created by zjjfly on 16/7/15.
  */
object TypeEvidence {
  def main(args: Array[String]) {
    println(firstLast("dada" toList))
  }
  //实际上,<:<是一个类,所以C<:<Iterable[A]使用类中置类型语法,实际类型是<:<[C,Iterable[A]]
  //这个类型定义在Predef中,它继承了Function1特质,它的伴生对象还有一个隐式方法,可以生成一个<:<[A,A]类型的对象
  //由于这个类的类型参数是From和To,From是逆变的,To是协变的,所以如果From是To的子类,<:<[From,From]可以看成是<:<[From,To]的实例

  //以上是旧版的实现,2.10的实现是,在Predef中构造一个单例的<:<[Any,Any]对象,还有一个隐式方法:
  //implicit def $conforms[A]: A <:< A = singleton_<:<.asInstanceOf[A <:< A]
  //这个方法是会把单例对象转换成一个<:<[A,A]对象,比如下面这个,那它就会转换成一个<:<[C,C],而基于同样的原因,它可以看出<:<[C,Iterable[A]]
  def firstLast[A,C](it:C)(implicit ev: C <:< Iterable[A])={
    (it.head,it.last)
  }
}
