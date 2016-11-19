package domain

import java.sql.DriverManager

import adapter.dto.ConnectDBDto

case class DBConnection(driver: String, url: String) {
  def getConnection = {
    Class.forName(driver).newInstance()
    DriverManager.getConnection(url)
  }
}

object DBConnection {
  def apply(dto: ConnectDBDto): DBConnection = dto.dbName match {
    case "MySQL" =>
      DBConnection(
        "com.mysql.jdbc.Driver",
        s"jdbc:mysql://${dto.host}:${dto.port}/?user=${dto.user}&password=${dto.pass.getOrElse("")}"
      )
    case _ => throw new Exception
  }

  val CACHE_KEY = "db.connection"

}
