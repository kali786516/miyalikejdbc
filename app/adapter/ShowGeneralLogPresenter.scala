package adapter

import contract.callback.{ExecuteSqlCallback, ShowGeneralLogCallback}
import domain.SqlResults

import scala.concurrent.{ExecutionContext, Future, Promise}

/**
  * Created by miyahara on 2016/11/20.
  */
class ShowGeneralLogPresenter extends Presenter[ShowGeneralLogCallback] {

  override def response(call: UsecaseExecutor)(implicit ec: ExecutionContext): Future[Rendered] = {
    val callback = new CallbackImpl
    call(callback)
    callback.promise.future
  }

  private class CallbackImpl extends ShowGeneralLogCallback {
    val promise = Promise[Rendered]()
    override def onSuccess(sqlResults: SqlResults) =
      promise.success(Ok(views.html.show_general_log(sqlResults.columns, sqlResults.results)))

    override def onFailure(throwable: Throwable): Unit = promise.failure(throwable)
  }

}
