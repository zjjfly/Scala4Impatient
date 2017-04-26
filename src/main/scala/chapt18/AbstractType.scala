package chapt18

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import scala.io.{ Source}

/**
  * Created by zjjfly on 16/7/12.
  */
object AbstractType {
  def main(args: Array[String]) {
    val content = new StringReader().read("words.txt")
    println(content)
    val content1 = new StringReader1().read("words.txt")
    println(content1)
  }
}

trait Reader {
  type Contents

  //抽象类型
  def read(fileName: String): Contents
}

class StringReader extends Reader {
  type Contents = String
  def read(fileName: String) = Source.fromFile(fileName).mkString
}

class ImageReader extends Reader {
  type Contents = BufferedImage

  def read(fileName: String) = ImageIO.read(new File(fileName))
}
//也可以使用类型参数实现相同的功能,但用抽象类型可以避免在有多个类型依赖时使用一长串类型参数
trait Reader1[C] {
  def read(fileName: String): C
}

class StringReader1 extends Reader1[String] {
  def read(fileName: String) = Source.fromFile(fileName).mkString
}

class ImageReader1 extends Reader1[BufferedImage] {
  def read(fileName: String) = ImageIO.read(new File(fileName))
}

trait Listener{
  type Event <: java.awt.event.ActionEvent//抽象类型可以有类型界定,和类型参数一样
}