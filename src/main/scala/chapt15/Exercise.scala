package chapt15

import scala.annotation.varargs

/**
  * Created by zjjfly on 16/6/29.
  */
object Exercise {
  var flag:Boolean=_
  def main(args: Array[String]): Unit = {
    val thread1=new Thread1()
    val thread2=new Thread2()
    new Thread(thread2).start()
    new Thread(thread1).start()

    List(1,13).foreach(_+1)
  }
}
class Exercise{
  @varargs def sum( i:Int*)=i.sum

}

class Thread1() extends Runnable{
  override def run(): Unit = {
    Thread.sleep(3000)
    Exercise.flag=true
    println("flag become true")
  }
}
class Thread2() extends Runnable{
  override def run(): Unit = {
    while (!Exercise.flag){
      Thread.sleep(1500)
    }
    println("thread2 finished")
  }
}