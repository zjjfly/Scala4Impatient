package chapt15

import java.util.Comparator

import scala.annotation.implicitNotFound
import scala.annotation.unchecked.uncheckedVariance

/**
  * Created by zjjfly on 16/6/29.
  */
object WarnAndErrorAnno {

  //方法过时注解,在编译时会生成警告信息
  @deprecated(message = "User factorial(n:BigInt) instead")
  def factorial(n:Int):Int=if (n==0) 1 else n*factorial(n-1)

  def draw(@deprecatedName('sz) size:Int,style:Int)=size*style
  //@deprecatedName表示该参数之前使用过的名称
  //这个注解的构造器参数是Symbol类型的,这种类型有一个特点:名称相同的Symbol必然是唯一的,所以这比字符串更有效率


  def main(args: Array[String]) {
    factorial(4)
    draw(sz=1,1)//编译会有警告

    val lst=List(1,14)
    (lst: @unchecked) match {//@unchecked用于消除匹配不完整时取消编译时的警告信息
      case head::tail=>""
    }

  }
}
@implicitNotFound(msg="StringFormat not found")//用于某个隐式参数不存在的时候生成有意义的错误提示,在21章中会细讲
class WarnAndErrorAnno[T]{
}

trait Comparator[-T] extends java.util.Comparator[T @uncheckedVariance]//@uncheckedVariance可以取消与型变相关的错误提示,这里是逆变
