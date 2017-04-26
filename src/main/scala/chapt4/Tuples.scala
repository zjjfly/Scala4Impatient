package chapt4

/**
  * Created by zjjfly on 16/5/19.
  */
object Tuples {
  def main(args: Array[String]) {
    //使用模式匹配获取元组的组元
    val t=(1,3.14,"Fred")
    val (first,second,_)=t
    //拉链操作
    val symbols=Array("<","-",">")
    val counts=Array(2,10,2)
    val zip: Array[(String, Int)] = symbols.zip(counts)
    println(zip.toList)
    //然后一起处理
    for((s,c)<-zip) print(s*c)
    println()
    //用toMap可以吧对偶的集合转成映射
    val toMap: Map[String, Int] = zip.toMap
    println(toMap)
  }
}
