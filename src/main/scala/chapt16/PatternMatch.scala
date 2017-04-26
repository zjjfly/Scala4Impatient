package chapt16

/**
  * Created by zjjfly on 16/7/1.
  */
object PatternMatch {
  def main(args: Array[String]) {
    val node= <img src="ca"></img>
    node match {
      case <img/> => println("img")//可以匹配没有子节点的带有任意属性的img元素
      case _=>println("no")
    }
    val img= <img src=""><em>d</em></img>
    img match {
      case <img>{_}</img> => println("img")//可以匹配单个子节点的带有任意属性的img元素
      case <img>{c}</img> => println(c)//上面的另一种写法
      case _=>println("no")
    }
    val i= <img src="ca"><em>d</em><em>c</em></img>
    i match {
      case <img>{_*}</img> => println("img")//可以匹配多个子节点的带有任意属性的img元素
      case <img>{c @ _*}</img> => println(c)//上面的另一种写法,可以使多个子节点和变量绑定
      case _=>println("no")
    }
    node match {
      case n @ <img/> if n.attributes("src").text=="ca"=>println("img")//匹配属性
      case _=>println("no")
    }
  }
}
