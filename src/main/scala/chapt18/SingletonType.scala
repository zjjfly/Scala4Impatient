package chapt18
package SingletonType
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zjjfly on 16/7/11.
  */
object SingletonType {
  def main(args: Array[String]) {
    val book: Book = new Book
    book.setTitle("scala").addChapter("chapt1")
    println(book)
    val movie: Movie = new Movie("","")
    movie set Title to "scala" set Author to "jjzi"
    println(movie)
  }
}

class Document{
  var title:String=_

  var author:String=_

  def setTitle(title:String):this.type ={this.title=title;this}

  def setAuthor(author:String):this.type ={this.author=author;this}
}
class Book extends Document{
  var chapter:ArrayBuffer[String]=ArrayBuffer()

  def addChapter(chapter:String):this.type ={this.chapter+=chapter;this}

}
object Title
object Author

case class Movie(var title:String ,var author:String){

  private var useNextArgAs:Any=null

  def set(obj:Title.type ):this.type ={useNextArgAs=obj;this}
  def set(obj:Author.type ):this.type ={useNextArgAs=obj;this}

  def to (arg:String):this.type =if(useNextArgAs==Title) {title=arg;this} else {author=arg;this}
}