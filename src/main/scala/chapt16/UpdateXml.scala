package chapt16

import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.xml._

/**
  * Created by zjjfly on 16/7/5.
  */
object UpdateXml {
  def main(args: Array[String]) {
    //scala的XML节点和节点序列是不可变的,所以要修改一个节点,需要新建一个拷贝,给出需要的修改,然后拷贝其他未修改的部分
    val list = <ul><li>Fred</li><li>Wilma</li></ul>
    val list2: Elem = list.copy(label = "ol")//修改标签名
    println(list2)
    val list3: Elem = list.copy(child = list.child ++ <li>Another Item</li>)//添加子节点
    println(list3)
    val image= <img src="hamster.jpg"></img>
    val image2: Elem = image % Attribute(null,"alt","An image of hamster",Null)//增加或修改属性
    println(image2)
    val image3: Elem = image % Attribute(null,"alt","An image of hamster",Attribute(null,"src","frog.jpg",Null))//添加或修改多个属性
    println(image3)

    //如果要重写满足某个特定条件的节点,使用RuleTransformer类,它可以把若干个RewriteRule实例应用到某个节点及其后代
    //把所以的ul节点变成ol节点
    val l= <body><ul>ada</ul><ul>vaaf</ul></body>
    val rule1=new RewriteRule {
      override def transform(n:Node)= n match {
        case e @ <ul>{_*}</ul> => e.asInstanceOf[Elem].copy(label = "ol")
        case _=> n
      }
    }
    val rule2=new RewriteRule {
      override def transform(n:Node)= n match {
        case e @ <ol>{Text(x)}</ol> => <ol>hehe</ol>
        case _=> n
      }
    }
    val transformed1: Seq[Node] = new RuleTransformer(rule1).transform(l)//将规则应用到某棵xml树
    println(transformed1)
    val transformed2: Seq[Node] = new RuleTransformer(rule1,rule2).transform(l)//将多个规则应用到某棵xml树
    println(transformed2)
    //transform方法会遍历给定节点的所以后代
  }
}
