package chapt16


import scala.io.Source
import scala.xml.parsing.XhtmlParser
import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.xml.{Node, _}

/**
  * Created by zjjfly on 16/7/6.
  */
object Exercise {
  def main(args: Array[String]) {
    //1
    val fred= <fred/>
    println(fred(0))//fred本身
    println(fred(0)(0))//还是fred本身
    //2 使用双{和双}
    val ul= <ul>
        <li>Closing bracker:]</li>
        <li>Opening bracker:[</li>
        <li>Opening brace:{{</li>
        <li>Closing brace:}}</li>
        </ul>
    println(ul)
    //3 因为嵌入表达式中的字符串会转成Atom[String]
    val li1= <li>Fred</li>
    val li2= <li>{"Fred"}</li>
    li1 match {
      case <li>{Text(t)}</li>=>println(t)
      case _=>println(li1)
    }
    li2  match {
      case <li>{Text(t)}</li> => println(t)
      case _=>println(li2)
    }

    //4
    val parser=new XhtmlParser(Source.fromFile("myxhtml.xhtml"))
    val document: Document = parser.initialize.document()
    val xhtml: Node = document.docElem
    val imgs: NodeSeq = xhtml \\ "img"
    println(imgs)
    println(imgs.filter(_.attribute("alt")==None))

    //5
    imgs.map(_ \ "@src" text).foreach(println)

    //6
    val as: NodeSeq = xhtml \\ "a"
    as.map(n=>n.text+" "+(n.attribute("href").getOrElse(""))).foreach(println)

    //7
    val map=Map("A"->"1","B"->"2")
    println(map2dl(map))

    //8
    val elem= <dl><dt>n</dt><dd>1</dd><dt>m</dt><dd>2</dd><dt>b</dt><dd>3</dd><dt>p</dt></dl>
    println(dl2map(elem))

    //9
    val rule=new RewriteRule {
      override def transform(n:Node)= n match {
        case e @ <img></img> if (e.attribute("alt")==None) => e.asInstanceOf[Elem] %  Attribute(null,"alt","TODO",Null)
        case _=> n
      }
    }
    val transform: Seq[Node] = new RuleTransformer(rule).transform(xhtml)
    println(transform)

    //10

  }
  //7
  def map2dl(m:Map[String,String])={
    <dl>{for((k,v)<-m) yield <dt>{k}</dt><dd>{v}</dd>}</dl>
  }
  //8
  def dl2map(e:Elem)={
    val dts: NodeSeq = e \ "dt"
    val dds: NodeSeq = e \ "dd"
    val all: Seq[(Node,Node)] = dts.zipAll(dds, <dt/> , <dd/>)
    all.map(s=>(s._1.text,s._2.text)).toMap
  }
}
