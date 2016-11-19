package contract

trait Callback[Result] {
  def onSuccess(result: Result): Unit

  def onFailure(t: Throwable): Unit
}
