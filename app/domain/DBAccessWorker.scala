package domain

import java.sql.{Connection, ResultSet, Statement}

import scala.collection.immutable.IndexedSeq

case class DBAccessWorker(connection: Connection, sql: String) {

  private def execute(execSql: String) = {
    val stmt: Statement = connection.createStatement
    val rs: ResultSet = stmt.executeQuery(execSql)
    val columnCnt: Int = rs.getMetaData.getColumnCount

    val columns: IndexedSeq[String] = 1 to columnCnt map rs.getMetaData.getColumnName
    val results: Iterator[IndexedSeq[String]] = Iterator.continually(rs).takeWhile(_.next()).map{ rs =>
      columns map rs.getString
    }

    (columns, results)
  }

  def execute(): SqlResults = {
    val (d1, d2) = execute(sql)
    SqlResults(d1, d2)
  }

  def executeStatistics(): Statistics = {
    val (d1, d2) = execute(s"EXPLAIN $sql")
    Statistics(d1, d2)
  }
}

case class SqlResults(columns: IndexedSeq[String], results: Iterator[IndexedSeq[String]])

case class Statistics(columns: IndexedSeq[String], results: Iterator[IndexedSeq[String]])