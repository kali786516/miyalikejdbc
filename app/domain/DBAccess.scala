package domain

import java.sql.{Connection, ResultSet, Statement}

import scala.collection.immutable.IndexedSeq

case class DBAccessWorker(connection: Connection, sql: String) {

  def execute(): (IndexedSeq[String], Iterator[IndexedSeq[String]]) = {
    val stmt: Statement = connection.createStatement
    val rs: ResultSet = stmt.executeQuery(sql)
    val columnCnt: Int = rs.getMetaData.getColumnCount

    val columns: IndexedSeq[String] = 1 to columnCnt map rs.getMetaData.getColumnName

    val dataList: Iterator[IndexedSeq[String]] = Iterator.continually(rs).takeWhile(_.next()).map{ rs =>
      columns map rs.getString
    }

    (columns, dataList)
  }
}
