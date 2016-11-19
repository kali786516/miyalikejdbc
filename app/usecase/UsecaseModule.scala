package usecase

import com.google.inject.AbstractModule
import contract.usecase.{ConnectDBUsecase, ExecuteSqlUsecase, ShowGeneralLogUsecase}

class UsecaseModule extends AbstractModule {

  def configure(): Unit = {
    bind(classOf[ConnectDBUsecase]).to(classOf[ConnectDBUsecaseImpl])
    bind(classOf[ExecuteSqlUsecase]).to(classOf[ExecuteSqlUsecaseImpl])
    bind(classOf[ShowGeneralLogUsecase]).to(classOf[ShowGeneralLogUsecaseImpl])
  }
}
