import sbt._

class MyProject(info: ProjectInfo) extends DefaultProject(info) {
  lazy val hello = task { println("Hello!!"); None }
}