package chapt21

/**
  * Created by zjjfly on 16/7/15.
  */
class Delimiters(val left:String,val right: String)

object Delimiters{
  implicit val delims=new Delimiters("\"","\"")
}