package controllers

import java.sql.{ResultSet, Statement}
import javax.inject._

import play.api.db._
import play.api.mvc._

import scala.collection.immutable.IndexedSeq


@Singleton
class MainController @Inject() (db: Database) extends Controller {

  def generalLog = Action {

    val conn = db.getConnection()

    try {
      val stmt: Statement = conn.createStatement
      val rs: ResultSet = stmt.executeQuery("SELECT * from general_log ")
      val columnCnt: Int = rs.getMetaData.getColumnCount

      val columns: IndexedSeq[String] = 1 to columnCnt map rs.getMetaData.getColumnName

      val dataList: Iterator[IndexedSeq[String]] = Iterator.continually(rs).takeWhile(_.next()).map{ rs =>
        columns map rs.getString
      }
      Ok(views.html.show(columns, dataList))
    } finally {
      conn.close()
    }
  }
}
