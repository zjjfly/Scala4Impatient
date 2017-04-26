package chapt9

import java.io.PrintWriter
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, SimpleFileVisitor, Path, Files}
import scala.io.{StdIn, Source}

/**
  * Created by zjjfly on 16/6/1.
  */
object Sources {
  def main(args: Array[String]) {
    val source=Source.fromFile("words.txt","UTF-8")
    val lineIterator=source.getLines()//getLines返回的是一个迭代器
    //可以使用for循环逐行处理
//    for (l <- lineIterator) {
//      println(l)
//    }
    //也可以对迭代器应用toArray和toBuffer方法,放入数组或数组缓冲
//    val lines=lineIterator.toBuffer
//    println(lines)
    //或者直接把文件内容转成一个字符串
//    println(source.mkString)//
    //source继承自Iterator[Char],所以可以用for循环每一个字符
//    for(c<-source) println(c)
    //如果想查看某个字符但不处理它,先调用source的buffered方法,然后用head方法查看下一个字符
    val buffered: BufferedIterator[Char] = source.buffered
    while (buffered.hasNext)
      if(buffered.head =='a') {
        val next: Char = buffered.next()
        println(next)
      }else{
        print("")
        buffered.next()
      }

    source.close()//使用完source要关闭
    val numbers=Source.fromFile("numbers.txt")
    //获取以空格和换行符分隔的字符串
    val tokens: Array[String] = numbers.mkString.split("\\W+")
    val doubles: Array[Double] = for(w<-tokens) yield w.toDouble
    println(doubles.toList)
    println(tokens.map(_.toDouble).toList)
    //读取控制台输入
//    StdIn.readInt()
    numbers.close()

    //从非文件源读取
    val source1=Source.fromURL("http://www.baidu.com","utf-8")
    val source2=Source.fromString("hello,world")
    val source3=Source.stdin
    println(source1.getLines().mkString)
    source1.close()
    source2.close()
    source3.close()

    //读取二进制文件,使用java的InputStream

    //写文件,使用PrintWriter
    val out=new PrintWriter("numbers.txt")
    //使用printf时,要注意传入的参数要转成AnyRef,或者使用String的format方法
    for(i<-1 to 100) out.printf("%d",i.asInstanceOf[AnyRef])
    out.println()
    for(i<-1 to 100) out.printf("%d".format(i))
    out.close()

    //访问目录
    //遍历某目录的所有子目录
    import java.io.File
    def subdirs(dir:File):Iterator[File]={
      val children=dir.listFiles.filter(_.isDirectory)
      children.toIterator ++ children.toIterator.flatMap(subdirs)
    }
    val current: File = new File("/Users/zjjfly/Desktop")
    subdirs(current).foreach(println)
    println
    //jdk 7 可以使用下面的方式
    implicit  def makeFileVistor(f:(Path)=>Unit):SimpleFileVisitor[Path]=new SimpleFileVisitor[Path]{
      override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
        f(file)
        FileVisitResult.CONTINUE
      }
    }
    Files.walkFileTree(current.toPath,(f:Path)=>println(f))
  }

  //可序列化的类
   @SerialVersionUID(42L)class Man extends Serializable

}

