package adapter

import javax.inject._

import adapter.dto.ConnectDBDto
import contract.usecase.ConnectDBUsecase
import play.api.data.Form
import play.api.mvc._
import play.api.data.Forms._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

@Singleton
class ConnectDBController @Inject()(usecase: ConnectDBUsecase, presenter: ConnectDBPresenter) extends Controller {

  val connectForm = Form(
    mapping(
      "dbName" -> text,
      "host" -> text,
      "port" -> text,
      "user" -> text,
      "pass" -> optional(text)
    )(ConnectDBDto.apply)(ConnectDBDto.unapply)
  )

  def connect() = Action.async { implicit request =>
    connectForm.bindFromRequest().fold(_ => Future.successful(BadRequest("aaa")),
      (dto: ConnectDBDto) => presenter.response(usecase.execute(dto))
    )
  }

}
