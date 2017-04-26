package chapt18

import java.awt.{Point, Rectangle, Shape}
import java.io.Serializable

import scala.collection.Map
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/7/11.
  */
object MixType {
  type x[A,B]=(A,B)
  def main(args: Array[String]) {
    val image=new ArrayBuffer[Shape with Serializable]()//复合类型
    val rect=new Rectangle(5,10,20,40)
    image += rect
//    image += new Area(rect) 错误,Area是Shape但不是Serializable
    println(shapcontain(rect,new Point(0,5)))
    println(dos("add",1))
  }
  def dos[A,B](p:A x B)=p._1//中置表达式

  def maps(m:String Map Int)=m.map(_._1)//中置表达式
  //复合类型和结构类型一起使用
  def shapcontain(shape:Shape with Serializable{def contains(p:Point):Boolean},p:Point)={
    shape.contains(p)
  }
}
