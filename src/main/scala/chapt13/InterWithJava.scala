package chapt13

import scala.collection.mutable

/**
  * Created by zjjfly on 16/6/16.
  */
object InterWithJava {
  def main(args: Array[String]) {
    import scala.collection.JavaConversions.propertiesAsScalaMap
    val props:mutable.Map[String,String]=System.getProperties
  }
}
