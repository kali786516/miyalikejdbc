package contract

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

trait PushPort[Arg, Result] {
  self: Usecase =>

  override final type In = Arg

  override final type Out = Result

  def execute[T <: Callback[Result]](arg: Arg)(callback: T)(implicit ec: ExecutionContext): Unit = {
    call(arg).onComplete {
      case Success(result) => callback.onSuccess(result)
      case Failure(t) => callback.onFailure(t)
    }
  }

}
