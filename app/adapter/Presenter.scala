package adapter

import contract.Callback
import play.api.mvc.{Result, Results}

import scala.concurrent.{ExecutionContext, Future}

trait Presenter[C <: Callback[_]] extends Results {

  type UsecaseExecutor = C => Unit

  type Rendered = Result

  def response(call: UsecaseExecutor)(implicit ec: ExecutionContext): Future[Rendered]

}
