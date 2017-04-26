/**
  * Created by zjjfly on 16/5/26.
  */
package object chapt7 {
    //实际上包对象在编译的时候会变成一个类,使用包对象的成员的地方会变为对这个类的成员和方法的调用
    //注意到,它的文件顶部没有包声明,所以它虽然位于chapt7文件下,但它其实是在chapt7的父包中声明的
    val deFaultName="jjzi"
}
