package chapt14

/**
  * Created by zjjfly on 16/6/26.
  */
object SealedClass extends App{
  val color:TrafficLightColor=Red
  val c=color match {
    case Red=>"stop"
    case Yellow=>"hurry"
    case Green=>"go"
  }
  println(c)
  //自带的Option类
  val scores=Map("Alice"->99,"Tom"->88)
  private val option: Option[Int] = scores.get("Alice")
  option match {
    case Some(score)=>println(score)
    case None=>println("No score")
  }
  //上面这样比较繁琐,使用getOrElse更好
  println(option.getOrElse("No score"))
  println(scores.getOrElse("Alice","No score"))
}
sealed abstract class TrafficLightColor

case object Red extends TrafficLightColor

case object Yellow extends TrafficLightColor

case object Green extends TrafficLightColor
