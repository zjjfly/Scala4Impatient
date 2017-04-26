package chapt5

import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/5/20.
  */
object NestedClass {
  def main(args: Array[String]) {
    val chatter = new NetWork
    val myFace = new NetWork
    val fred = chatter.join("Fred")
    fred.contacts += chatter.join("Wilma")
    //fred.contacts+=myFace.join("Barney")这行不能编译通过,因为chatter.Member和myFace.Member是两个不同的类
    //如果不希望这种结果,有两个方法,一是把Member类放到Network的伴生对象中
    //或者使用类型投影,语法是Network#Member,意思是任意Network的Member

  }
}

class NetWork {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
    def get():ArrayBuffer[Member]={
      //访问外部类的this引用,和java一样
      NetWork.this.members
    }
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}
class Hill(val name:String){
  //给外部类的this引用指定一个别名outer
  outer=>
  class Stone(val name:String){
    def desciption=name+" on "+ outer.name
  }
}

