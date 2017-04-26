package chapt9

/**
  * Created by zjjfly on 16/6/2.
  */
object Regexs {
  def main(args: Array[String]) {
    val numPattern = "[0-9]+" r
    val wsnumwsPattern = """\s+[0-9]+\s+""".r
    //使用findAllIn返回遍历所有匹配项的迭代器
    for(ms<- numPattern findAllIn "99 bottles, 98 bottles")
      println(ms)
    //或者把迭代器转为数组
    val matchs: Array[String] = wsnumwsPattern.findAllIn("99 bottles, 98 bottles").toArray
    //找首个匹配项
    val m1=wsnumwsPattern findFirstIn "99 bottles, 98 bottles"
    println(m1)
    //检查开始部分是否匹配
    val m2: Option[String] = numPattern findPrefixOf "99 bottles, 98 bottles"
    println(m2)
    val m3: Option[String] = wsnumwsPattern findPrefixOf "99 bottles, 98 bottles"
    println(m3)
    //可以替换首个匹配项或全部匹配项
    val rf: String = numPattern replaceFirstIn ("99 bottles, 98 bottles","11")
    println(rf)
    val ra: String = numPattern replaceAllIn ("99 bottles, 98 bottles","11")
    println(ra)

    //正则表达式组
    val numitemPattern="([0-9]+) ([a-z]+)".r
    val numitemPattern(num,item)="99 bottles"
    println(num)
    println(item)
    //从多个匹配项中提取分组内容
    for(numitemPattern(num,item)<-numitemPattern.findAllIn("99 bottles,98 bottles")) {
      println(num)
      println(item)
    }

  }
}
