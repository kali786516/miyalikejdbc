package adapter

import com.google.inject.AbstractModule

class AdapterModule extends AbstractModule {

  def configure(): Unit = {
    bind(classOf[ConnectDBPresenter])
    bind(classOf[ExecuteSqlPresenter])
    bind(classOf[ShowGeneralLogPresenter])
  }
}
