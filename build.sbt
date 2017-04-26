name := "ImpatientScala"

version := "1.0"

scalaVersion := "2.12.1"


libraryDependencies +="org.scala-lang.modules" %% "scala-xml" % "1.0.6"
libraryDependencies +="org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5"
libraryDependencies += "javax.persistence" % "persistence-api" % "1.0.2"
//下面的这一行作用是给scalac加入一个参数,这个参数用于判断省略哪些可省略方法
//这样配可以把工程中所有的@elidable的参数小于等于2001的方法忽略,不编译到class文件中
//scalacOptions ++= Seq("-Xelide-below", "2001")
//scalacOptions ++= Seq("-deprecation","")//编译的时候打印过时消息