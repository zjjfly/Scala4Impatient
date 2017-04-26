package chapt9

import java.io.{File, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by zjjfly on 16/6/2.
  */
object Exercise {
  def main(args: Array[String]): Unit = {
    //1
//    reverse("words.txt")
    //2
    writeFile("test",List("131.8\t244.3\t114.7\n141.5\t473.6\t798.1"))
    val replaced = Source.fromFile("test").getLines().toList.map(_.replaceAll("\\t"," "))
    writeFile("test",replaced)
    //3
    //第一种解法,划分单词的正则有点问题,不如第二种
    val filter1: List[String] = Source.fromFile("words.txt").getLines().toList.map(_.trim).flatMap(_.split("\\s+")).filter(_.length>12)
    println(filter1)
    //第二种解法
    val wordsPattern="[\\w\\-]{12,}".r
    val filter2: List[String] = Source.fromFile("words.txt").getLines().toList.flatMap(wordsPattern.findAllIn(_))
    println(filter2)
    //4
    val decimalPattern="\\d+\\.?\\d+".r
    val decimals: List[Double] = Source.fromFile("test").getLines().toList.flatMap(decimalPattern.findAllIn(_)).map(_.toDouble)
    println(decimals.sum)
    println(decimals.max)
    println(decimals.min)
    //6
    val p="""\".*(\\\".*\\\")+.*\"""".r
    println(p.findAllIn("\"maybe \\\"ada\\\"\",\\\"wrwr\\\"").toList)
    //7
    println("14141.131".matches("\\d+[.]{1}\\d+"))
    //8
    val html=Source.fromURL("http://www.baidu.com").getLines().mkString
    val imageSrc="<img[^>]+(src\\s*=\\s*\"[^>*^\"]+\")[^>]*>".r
    for (imageSrc(src) <- imageSrc.findAllIn(html)) {
      println(src)
    }
    //9
    val files: Iterator[File] = listFiles(new File("/Users/zjjfly/idea works/ImpatientScala"))
    println(files.filter(_.getName.endsWith(".class")).map(_.getName).toList)
  }
  //9
  def listFiles(dir:File):Iterator[File]={
    val children=dir.listFiles.filter(_.isFile)
    children.toIterator ++ dir.listFiles.filter(_.isDirectory).toIterator.flatMap(listFiles)
  }
  //10
  @SerialVersionUID(42L)class Person extends Serializable{
    private val friends:ArrayBuffer[Person]=new ArrayBuffer[Person]

    def makeFriend(p:Person)={
      friends += p
    }
  }
  //1
  def reverse(s:String)={
    val content=Source.fromFile(s)
    val reversed: List[String] = content.getLines().toList.reverse
    content.close()
    val writer: PrintWriter = new PrintWriter(s)
    for(s<-reversed) writer.println(s)
    writer.close()
  }
  //2
  def writeFile(s:String,c:List[String])={
    val writer: PrintWriter = new PrintWriter(s)
    for(s<-c) writer.println(s)
    writer.close()
  }
}
