package adapter

import javax.inject.Singleton

import contract.callback.ExecuteSqlCallback
import domain.{SqlResults, Statistics}

import scala.concurrent.{ExecutionContext, Future, Promise}

@Singleton
class ExecuteSqlPresenter extends Presenter[ExecuteSqlCallback] {

  override def response(call: UsecaseExecutor)(implicit ec: ExecutionContext): Future[Rendered] = {
    val callback = new CallbackImpl
    call(callback)
    callback.promise.future
  }

  private class CallbackImpl extends ExecuteSqlCallback {
    val promise = Promise[Rendered]()
    override def onSuccess(res: (SqlResults, Statistics)) = {
      promise.success(Ok(views.html.show(res._2.columns, res._2.results, res._1.columns, res._1.results)))
    }

    override def onFailure(throwable: Throwable): Unit = promise.failure(throwable)
  }
}
