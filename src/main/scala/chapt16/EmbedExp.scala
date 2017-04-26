package chapt16

import scala.xml._

/**
  * xml内嵌表达式
  * Created by zjjfly on 16/6/30.
  */
object EmbedExp {
  def main(args: Array[String]) {
    val array=Array(1,3,4)
    val xml= <ul><li>{array(0)}</li><li>{array(1)}</li></ul>
    val seq: NodeSeq = xml \ "li"
    //内嵌表达式如果产生一个节点序列,会直接加到xml中,但如果是其他值,会被放入Atom[T]中,可以通过data属性取回值
    println(seq.flatMap(_.child).map(_.isAtom))
    println(seq.flatMap(_.child).map(_.asInstanceOf[Atom[Int]].data))

    //表达式中还可以包含XML字面量,就像chapt11/Exercise中展示的那样
    val items=List("ps3","ps4","pc")
    val nodes= <ul>{for(i<-items) yield <li>{{{i}}}</li>}</ul>//要在XML字面量中包含花括号,只要连续写两个就可以{{、}}
    println(nodes)
    //可以在属性中使用表达式
    val url="http://test.jpg"
    val img= <img src={url}></img>//不要带"",不然不会解析成表达式
    println("img = " + img)
    //如果表达式返回null或None,属性不会被设置
    val desc:String=null
    val aa = <a src={if (desc=="TODO") null else desc}></a>
    println("a = " + aa)

    //使用PCData处理CDATA标签
    var js= <script><![CDATA[if (temp<0) alert("Cold!")]]></script>//这样行不通
    println(js)
    val code="""if (temp<0) alert("Cold!")"""
    js= <script>{PCData(code)}</script>
    println("js = " + js)
    //可以使用nparsedU节点包含任意的文本,但不推荐这么做,因为这样很容易出现xml格式错误
    val n1= <xml:unparsed><a></a></xml:unparsed>
    val n2= <p>{Unparsed("<&>")}</p>
    println("n1 = " + n1)
    println("n2 = " + n2)

    //可以把一个NodeSeq放入一个Group中
    val g1= <xml:group><li>Item 1</li><li>Item 2</li></xml:group>
    val g2=Group(Seq(<li>Item1</li>, <li>Item2</li>))
    //遍历组节点时,它们会被自动解开
    val seq1: NodeSeq = for(n<-g1) yield n
    println(seq1.size)
    val seq2: NodeSeq = for(n<- <ol><li>a</li><li>b</li></ol>) yield n
    println(seq2.size)
  }
}
