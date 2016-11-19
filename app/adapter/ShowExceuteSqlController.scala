package adapter

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

@Singleton
class ShowExecuteSqlController @Inject() extends Controller {

  implicit val ec = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def showExecuteSqlForm = Action.async { Future(Ok(views.html.exec())) }
}
