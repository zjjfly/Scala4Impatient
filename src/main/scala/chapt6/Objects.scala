package chapt6

import scala.util.Random

/**
  * Created by zjjfly on 16/5/23.
  */
class Objects private(val id:Int) {
}

object Objects {
  def apple={new Objects(Random.nextInt)}
  //对象的main函数是应用程序入口,
  def main(args: Array[String]): Unit = {
  }
}
//扩展App特质的对象也是应用程序的入口
object Hello extends App{
  println("Hello")
}

