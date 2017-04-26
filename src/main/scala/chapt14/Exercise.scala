package chapt14

import scala.reflect.ClassTag

/**
  * Created by zjjfly on 16/6/27.
  */
object Exercise extends App {
  //2
  def swap(t: (Int, Int)): (Int, Int) = t match {
    case (a, b) => (b, a)
    case _ => null
  }

  println(swap((1, 2)))

  //3
  //用泛型指定数组的类型的初始化类型的时候,需要给泛型T指定指定为ClassTag,用来在运行时获取类型信息。
  def swap[T: ClassTag](array: Array[T]): Array[T] = array match {
    case Array(a, b, c@_*) => Array(b, a) ++ c
    case _ => array
  }

  println(swap(Array(1, 2, 3, 4)).toBuffer)

  //4
  def price(i: Item): Double = i match {
    case Article(_, price) => price
    case Bundle(_, disc, items@_*) => items.map(price _).sum - disc
    case Multiple(n, i) => n * price(i)
  }

  val item = Multiple(2, Bundle("", 10, Article("", 50), Multiple(10, Article("", 6))))
  println(price(item))

  //5
  def leafSum(lst: List[Any]): Int = lst match {
    case c :: list => {
      val h = c match {
        case i: Int => i
        case l: List[Any] => leafSum(l)
      }
      h + leafSum(list)
    }
    case Nil => 0
  }

  println(leafSum(List(List(5, 8), 2, 3)))

  //6
  def leafSum(root: BinaryTree): Int = root match {
    case BinaryNode(l, r) => leafSum(l) + leafSum(r)
    case Leaf(i) => i
    case Node("+",children @ _*)=>children.map(leafSum _).sum
    case Node("*",children @ _*)=>children.map(leafSum _).product
    case Node("-",children @ _*)=>children.map(leafSum _).reduceLeft(_-_)
    case Node("/",children @ _*)=>children.map(leafSum _).reduceLeft(_/_)
  }
  val tree1=BinaryNode(Leaf(1),Leaf(2))
  val tree=BinaryNode(tree1,Leaf(5))
  println(leafSum(tree))
  //7,8
  val node=Node("+",Leaf(2),Node("/",Leaf(9),Leaf(3)),Leaf(5),Node("*",Leaf(3),Leaf(9)))
  println(leafSum(node))
  //9
  def sum(list: List[Option[Int]])={
    list.map(_.getOrElse(0)).sum
  }
  println(sum(List(Some(1),Some(1),Some(1),None,Some(1))))
  //10
  import scala.math._
  def compose(f1:Double=>Option[Double],f2:Double=>Option[Double])=(d:Double)=>{
    f1(d) match {
      case a:Some[Double]=>f2(d) match {
        case b:Some[Double]=>a
        case _ =>None
      }
      case _ =>None
    }
  }
  def f(x:Double)=if (x>0) Some(sqrt(x)) else None
  def g(x:Double)=if (x!=1) Some(1 /(x-1)) else None
  println(compose(f,g)(0))
}

case class Multiple(count: Int, i: Item) extends Item

sealed abstract class BinaryTree

case class Leaf(value: Int) extends BinaryTree

case class BinaryNode(first: BinaryTree, right: BinaryTree) extends BinaryTree

case class Node(operator:String,children:BinaryTree*) extends BinaryTree
