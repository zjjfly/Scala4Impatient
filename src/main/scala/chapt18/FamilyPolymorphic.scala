package chapt18

import scala.collection.mutable.ArrayBuffer
import scala.xml.{Atom, Node, NodeSeq}

/**
  * Created by zjjfly on 16/7/12.
  */
//C[_]表示一个类型构造器,不这么写C后面没办法跟[F]
trait Iter[E,C[_]]{
  def map[F](f:(E)=>F):C[F]
}
object FamilyPolymorphic {
//家族多态用于对那些跟着一起变化的类型家族建模,同时共用代码,保证类型安全
  def main(args: Array[String]): Unit = {
    import ButtonModule._
    val b=new Button
    b.add(new ButtonListener {
      override def occurred(e: ButtonEvent): Unit = println("aa")
    })
    b.click()
  val strings: Atom[String] = new Atom[String]("")
  val map= strings.map(_+"")
  }
}

trait ListenerSupport{
  type S <:Source
  type E <:Event
  type L <:Listener
  trait Event{var Source:S=_}
  trait Listener{def occurred(e:E):Unit}
  trait Source{
    this:S=>
    private val listeners=new ArrayBuffer[L]
    def add(l: L){listeners += l}
    def remove(l: L){listeners -= l}
    def fire(e:E): Unit ={
      e.Source=this
      for(l<-listeners)l.occurred(e)
    }
  }
}

object ButtonModule extends  ListenerSupport{
  type S=Button
  type E=ButtonEvent
  type L=ButtonListener

  class ButtonEvent extends Event
  trait ButtonListener extends Listener
  class Button extends Source{
    def click()={fire(new ButtonEvent)}
  }
}

