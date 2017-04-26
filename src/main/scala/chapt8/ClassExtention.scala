package chapt8

/**
  * Created by zjjfly on 16/5/27.
  */
object ClassExtention {
  override def toString: String = super.toString+"ClassExtention"

  def main(args: Array[String]) {
    //如果一个对象是null,调用isInstanceOf返回false,调用asInstanceOf返回null
    val obj=null
    val b: Boolean = obj.isInstanceOf[Person]
    println(b)
    println(obj.asInstanceOf[Person])
    val emp: Employee = new Employee("jjzi")
    //isInstanceOf会把子类的对象认为是父类型的对象
    println(emp.isInstanceOf[Person])
    //classOf返回对象所属的类,所以要严格的检查某个对象的类型时,用classOf
    println(emp.getClass==classOf[Person])
    //相比类型检查,模式匹配更好用
    emp match {
      case s:Employee=>println(true)
      case _=>println(false)
    }

    val agent: SecretAgent = new SecretAgent("ad")
    println( agent.name)

    //匿名对象
    val cat=new Cat{//实际上产生了一个结构类型的对象,类型是Cat{def greet:String}
      def greet="miao miao"
    }
    def meet(c:Cat{def greet:String}): Unit ={
      println(c.greet)
    }
    meet(cat)
  }
}

class Person(s:String){
  protected[this] val name=s
}

class Employee(s:String) extends Person(s){//只有主构造器可以访问超类的构造器
//  def isSameName(p:Employee)=name==p.name 报错,protect[this]限制了无法访问别的Employee对象的name属性
}

class SecretAgent(codename:String) extends Person(codename){
  //可以用同名的val字段冲洗一个val或不带参数的方法
  override val name="secret"

  //使用val重写抽象def
  override val toString="secret"
}
abstract class Animal{
  def id:Int
  var s:String
}
//使用val重写抽象def
class Dog(override val id:Int)extends Animal{
  //var只能重写另一个抽象的var
  override var s="d"
}

class Cat()

