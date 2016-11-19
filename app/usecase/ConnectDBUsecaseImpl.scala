package usecase

import javax.inject.{Inject, Singleton}

import play.api.cache._

import contract.usecase.ConnectDBUsecase
import domain.DBConnection

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ConnectDBUsecaseImpl @Inject()(cache: CacheApi) extends ConnectDBUsecase {

  override protected def call(dto: In)(implicit ec: ExecutionContext): Future[Out] = {
    (cache.set(DBConnection.CACHE_KEY, DBConnection(dto).getConnection))
    Future(Unit)
  }
}
