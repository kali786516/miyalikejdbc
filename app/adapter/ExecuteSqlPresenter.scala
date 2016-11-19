package adapter

import javax.inject.Singleton

import contract.callback.ExecuteSqlCallback

import scala.collection.immutable.IndexedSeq
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
    override def onSuccess(result: (IndexedSeq[String], Iterator[IndexedSeq[String]])) = promise.success(Ok(views.html.show(result._1, result._2)))

    override def onFailure(throwable: Throwable): Unit = promise.failure(throwable)
  }
}
