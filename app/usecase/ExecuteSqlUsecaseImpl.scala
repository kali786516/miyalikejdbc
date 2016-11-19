package usecase

import java.sql.Connection
import javax.inject.{Inject, Singleton}

import contract.usecase.ExecuteSqlUsecase
import domain.{DBAccessWorker, DBConnection}
import play.api.cache.CacheApi

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ExecuteSqlUsecaseImpl @Inject()(cache: CacheApi) extends ExecuteSqlUsecase {

  override protected def call(dto: In)(implicit ec: ExecutionContext): Future[Out] = {
    val connection = cache.get[Connection](DBConnection.CACHE_KEY).get

    val accessWorker = DBAccessWorker(connection, dto.sql)
    val statisticsResult = accessWorker.executeStatistics()
    val sqlResult = accessWorker.execute()

    Future((sqlResult, statisticsResult))
  }
}
