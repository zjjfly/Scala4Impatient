package chapt18

import java.io.FileWriter

/**
  * Created by zjjfly on 16/7/11.
  */
object StructuralType {
  def main(args: Array[String]) {
    val writer: FileWriter = new FileWriter("1.txt")
    appendLine(writer,List("1","ad"))
    writer.flush()
    writer.close()
  }
  //结构类型,任何带有append方法的类都可以调用这个方法,这比定义一个特质更灵活
  //实现使用的是反射。一般只在需要抓住一些无法共享一个特质的类的共通方法的时候才使用结构类型
  def appendLine(target:{def append(atr:CharSequence):Any},lines:Iterable[String])={
    for(l<-lines) {target.append(l);target.append("\n")}
  }
}
