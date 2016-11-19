package adapter

import javax.inject.Singleton

import contract.callback.ConnectDBCallback

import scala.concurrent.{ExecutionContext, Future, Promise}

@Singleton
class ConnectDBPresenter extends Presenter[ConnectDBCallback] {

  override def response(call: UsecaseExecutor)(implicit ec: ExecutionContext): Future[Rendered] = {
    val callback = new CallbackImpl
    call(callback)
    callback.promise.future
  }

  private class CallbackImpl extends ConnectDBCallback {
    val promise = Promise[Rendered]()
    override def onSuccess(result: Unit) = promise.success(Ok(views.html.main()))

    override def onFailure(throwable: Throwable): Unit = promise.failure(throwable)
  }
}
