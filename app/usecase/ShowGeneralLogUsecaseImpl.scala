package usecase

import java.sql.Connection
import javax.inject.{Inject, Singleton}

import contract.usecase.ShowGeneralLogUsecase
import domain.{DBAccessWorker, DBConnection}
import play.api.cache.CacheApi

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ShowGeneralLogUsecaseImpl @Inject()(cache: CacheApi) extends ShowGeneralLogUsecase {

  override protected def call(dto: In)(implicit ec: ExecutionContext): Future[Out] = {
    val connection = cache.get[Connection](DBConnection.CACHE_KEY).get
    val sql = s"SELECT * FROM mysql.${dto.tableName}"
    Future(DBAccessWorker(connection, sql).execute())
  }

}
