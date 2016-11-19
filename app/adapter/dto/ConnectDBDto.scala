package adapter.dto

/**
  * ConnectDB用DTO
  *
  * @param dbName 接続先データベース名
  * @param host 接続先ホスト
  * @param port 接続先ポート
  * @param user 接続先ユーザ
  * @param pass 接続先パスワード
  */
case class ConnectDBDto private (dbName: String, host: String, port: String, user: String, pass: Option[String])
