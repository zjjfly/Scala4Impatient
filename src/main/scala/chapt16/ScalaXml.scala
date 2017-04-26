package chapt16

import scala.xml.{Text, Node, NodeBuffer, NodeSeq}

/**
  * Created by zjjfly on 16/6/30.
  */
object ScalaXml {
  def main(args: Array[String]) {
    val xml = <a href="http://scala-lang.org">The<em>Scala</em> language</a>//xml类型是Elem,表示一个xml元素
    val items = <li>Fred</li><li>Wilma</li>//items表示一系列节点,类型是NodeSeq
    println()
    println(items)
    println(xml.label)
    println(xml.child)
    val seq: NodeSeq = items \ "_"
    println(seq)

    //构建节点序列
    val item=new NodeBuffer
    item += <li>Fred</li>
    item += <li>Wilma</li>
    val nodes:NodeSeq=item//NodeBuffer可以被隐式的转换成NodeSeq
    item += <li>da</li>//转换之后,不要在修改它了,因为转换得到的NodeSeq也会跟着改变,XML节点序列应该是不可变的
    println(item)

    //元素属性
    val elem = <a href="http://scala-lang.org" alt="scala official website">The Scala language</a>
    val url: Seq[Node] = elem.attributes("href")//返回类型不是String是因为可能有实体引用
    println(url)
    println(elem.attributes("href").text)//如果确定没有实体引用,可以这样写
    //attributes方法如果要查询的属性不存在,返回null,不喜欢处理null的话可以使用get方法
    println(elem.attributes.get("jd"))
    println(elem.attributes.get("jd").getOrElse(Text("no")))
    //遍历属性
    for(attr<-elem.attributes) println(attr.value.text)
    //或者把MetaData转换成一个Map
    val attrMap: Map[String, String] = elem.attributes.asAttrMap
    println(attrMap)

  }
}
