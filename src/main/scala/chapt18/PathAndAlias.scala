package chapt18
package PathAndAlias
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/7/11.
  */
object PathAndAlias {
  def main(args: Array[String]) {
    val  chatter=new Network
    val  myFace=new Network
    val fred=chatter.join("Fred")
    val barney=myFace.join("Barney")
    val chatterMember= new chatter.Member("jjzi")
    //chatter.Member就是路径,路径的组成可以是包,对象,val,this,super,super[S],C.this,C.sper,C.super[S]
    //但不能是var和类
    fred.contacts += chatterMember
    //fred.contacts += barney
    //类型不匹配,不同Network对象的Member类是不一样的,把contacts的元素的类型改为"Network#Member"就可以
  }
}
class Network{
  import scala.collection.mutable._
  type Index=mutable.HashMap[String,(Int,Int)]//类型别名

  val indexes:Index=new Index

  class Member(val name:String){
    val contacts=new ArrayBuffer[Member]
  }
  private val member=new ArrayBuffer[Member]

  def join(name:String)={
    val m=new Member(name)
    member += m
    m
  }
}