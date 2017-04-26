package chapt15


import scala.annotation.meta.beanGetter
import scala.beans.BeanProperty
/**
  * Created by zjjfly on 16/6/28.
  */
object Annotation {
  def main(args: Array[String]) {
    val m=Map(1->"1")
    //表达式加注解
    (m.get(1): @unchecked) match {
      case Some("1")=>println("1")
    }
  }
//  注解参数,如果参数名是value,可以省略
  @SuppressWarnings(Array())
  def say=""


}

//主构造器添加注解,需要把注解放在主构造器之前,并加上一对圆括号
class Credentials @Deprecated()(var userName:String,var password:String)

//类型参数加注解
class MyContainer[@specialized T]{
  import javax.persistence.Id
  //利用元注解控制注解附在别处,下面的这个方法,把@Id应用到了getId方法
  @(Id @beanGetter) @BeanProperty var id=0
}
