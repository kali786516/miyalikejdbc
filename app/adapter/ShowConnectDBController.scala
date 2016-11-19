package adapter

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

@Singleton
class ShowConnectDBController @Inject() extends Controller {

  implicit val ec = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def showConnectForm = Action.async { Future(Ok(views.html.index())) }

}
