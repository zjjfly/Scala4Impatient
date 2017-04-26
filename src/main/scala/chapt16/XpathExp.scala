package chapt16

import scala.xml.NodeSeq

/**
  * Created by zjjfly on 16/6/30.
  */
object XpathExp {
  def main(args: Array[String]) {
    val list= <dl  alt="fv"><dt alt="ad"><em>Java</em></dt><dd>Gosling</dd><dt  alt="vc"><em>Scala</em></dt><dd>Odersky</dd></dl>
    val languages: NodeSeq = list \ "dt"//"\"作用是定位某个节点或节点序列的和传入的字符串同名的直接后代
    println(languages)
    println(list \ "_")//通配符可以匹配任意元素
    println(list \\ "em")//"\\"作用是定位任何深度的和传入的字符串同名的后代
    println(list \ "@alt")//@开头的字符串定位属性,前面如果是"\"则定位节点的属性
    println(list \\ "@alt")//前面是"\\"则定位所以元素的所有属性

    println(list \\ "dt " \ "@alt")//这样的用法不对,不能用单个\从多个节点提取属性

    println(list \ "dd" text)//对\或\\的结果调用text方法,会把所有结果序列中的文本串联起来
  }
}
