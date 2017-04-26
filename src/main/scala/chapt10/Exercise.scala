package chapt10

import java.awt.Point
import java.awt.geom.Ellipse2D
import java.beans.{PropertyChangeEvent, PropertyChangeListener, PropertyChangeSupport}
import java.io.InputStream

import scala.io.Source


/**
  * Created by zjjfly on 16/6/6.
  */
object Exercise {
  def main(args: Array[String]) {
    //1
    val egg = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
    println(egg.getCenterX + "," + egg.getCenterY)
    egg.translate(10, -10)
    println(egg.getCenterX + "," + egg.getCenterY)
    println(egg.getWidth + "," + egg.getHeight)
    egg.grow(10, 20)
    println(egg.getWidth + "," + egg.getHeight)
    //2
    val point1: OrdedPoint = new OrdedPoint(1, 2)
    val point2: OrdedPoint = new OrdedPoint(1, 3)
    val point3: OrdedPoint = new OrdedPoint(2, 3)
    val point4: OrdedPoint = new OrdedPoint(2, 3)
    println(point1 < point2)
    println(point2 < point3)
    println(point4 == point3)

    val logger = new  CryptoLogger
    logger.log("zjj")
  }
}

trait RectangleLike {
  this: Ellipse2D =>
  def translate(dx: Double, dy: Double) = {
    setFrame(getX + dx, getY + dy, getWidth, getHeight)
  }

  def grow(w: Double, h: Double) = {
    setFrame(getX, getY, getWidth + w, getHeight + h)
  }
}

class OrdedPoint(x: Int, y: Int) extends Point(x, y) with Ordered[Point] {
  override def compare(that: Point): Int = getX > that.getX match {
    case true => 1
    case false if getX == that.getX => (getY - that.getY).signum
    case _ => -1
  }
}

class CryptoLogger(i:Int=3) {

   def log(msg: String) = {
    println(encrypt(msg,i))
  }

  def encrypt(str: String,i:Int):String ={
    str.map(parse(_, i)).toString
  }

  def parse(c: Char, k: Int): Char = {
    val cc = c < 'A' match {
      case true => c + 26
      case false if c <= 'Z' => c + (k % 26)
      case false if c < 'a' => c - 26
      case false if c <= 'z' => c + (k % 26)
      case false if c > 'z' => c - 26
    }
    cc.toChar
  }
}

//trait PropertyChange extends PropertyChangeSupport
//
//class myPoint(x:Int,y:Int) extends Point(x,y) with PropertyChange

//8
trait Buffered{
  private val default_size=8192

  private var position=0

  private var buffer=new Array[Byte](default_size)

  def read()={
    readBuffer(1)
  }

  def readBuffer(length:Int)={
    if(position==default_size-1) fill()
    if(position+length>default_size-1){
      val l=position+length-default_size
      var b=buffer.slice(position,default_size-1)
      fill()
      b ++ buffer.slice(position,l)
    }else{
      buffer.slice(position,position+length+1)
    }
    position = position+1
  }

  def readSource(default_size: Int): Array[Byte]

  def fill(): Unit ={
    position=0
    buffer=readSource(default_size)
  }
}

//10

class IterableInputStream extends InputStream with Iterable[Byte]{
  override def read(): Int = iterator.next()

  override def iterator: Iterator[Byte] = {
    Source.fromInputStream(this).getLines().mkString.getBytes.toIterator

  }
}