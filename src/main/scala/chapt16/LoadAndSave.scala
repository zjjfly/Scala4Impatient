package chapt16

import java.io.{File, FileInputStream, InputStreamReader}
import java.net.URL

import scala.io.Source
import scala.xml.dtd.{DocType, IntDef, ParsedEntityDecl, PublicID}
import scala.xml.parsing.XhtmlParser
import scala.xml._

/**
  * Created by zjjfly on 16/7/5.
  */
object LoadAndSave {
  def main(args: Array[String]) {
    //从文件加载xml
    val root=XML.loadFile("test.xml")
    println(root)//不包含注释
    //从InputStream,Reader,或URL加载
    val root2=XML.load(new FileInputStream("test.xml"))
    val root3=XML.load(new InputStreamReader(new FileInputStream("test.xml"),"utf-8"))
//  val root4=XML.load(new URL("http://www.baidu.com"))
    //scala还提供了一种解析器,可以保留注释,CDATA和空白
    import scala.xml.parsing.ConstructingParser
    val parser=ConstructingParser.fromFile(new File("test.xml"),preserveWS = true)
    val doc: Document = parser.document//得到一个类型是Document的节点
    val root1: Node = doc.docElem
    println(root1)//包含注释

    //解析xhtml,可以使用XhtmlParser
    val parser1=new XhtmlParser(Source.fromFile("test.xhtml"))
    val document: Document = parser1.initialize.document()
    val elem: Node = document.docElem
    println(elem)
    val parser2=ConstructingParser.fromFile(new File("test1.xml"),preserveWS = true)
    //如果不是xhtml,要把实体添加到解析器的实体映射中,否则会把实体转成注释,例如<!-- unknown entity nbsp; -->
    parser2.ent ++= List(
      "nbsp"->ParsedEntityDecl("nbsp",IntDef("\u00A0"))
    )
    val doc1: Document = parser2.document//得到一个类型是Document的节点
    val root5: Node = doc1.docElem
    println(root5)

    //保存xml
    val c= <html><body><p>ad</p></body></html>
    //save方法的enc,xmlDecl,和doctype是可选参数
    XML.save("my.xml",c,
      enc = "utf-8",//编码,默认ISO-8859-1
      xmlDecl = true,//是否需要在最开始生成xml声明(<?xml...?>),默认false
      doctype = DocType("html",
        PublicID("-//W3C//DTD XHTML 1.0 Strict//EN","http://www.w3c.org/TR/xhtml1/DTD/xhtml1-strict.dtd"),
        Nil))
    //如果需要把没有内容的元素写成自结束标签,需要这么做:
    val content= <img src="1.jpg"></img>
    val str=xml.Utility.serialize(content,minimizeTags = MinimizeMode.Always)
    println(str)
    //如果想要排版更美观,可以这样:
    val printer=new PrettyPrinter(width = 100,step = 4)
    val ss=printer.format(c)
    println(ss)

    val scope:Elem= <html xmlns:svg="http://www.w3.org/2000/svg"><svg:svg width="100" heigt="100"></svg:svg></html>
    println(scope.scope.uri)
    //编码方式产生xml时,要手动设置scope和prefix
    val sp=new NamespaceBinding("svg","http://www.w3.org/2000/svg",TopScope)
    val attrs=Attribute(null,"width","100",Attribute(null,"height","100",Null))
    val elems=Elem(null,"body",Null,TopScope,Elem("svg","svg",attrs,sp))
    println(elems)
  }
}
