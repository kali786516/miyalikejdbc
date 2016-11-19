package adapter

import javax.inject.{Inject, Singleton}

import adapter.dto.ShowGeneralLogDto
import contract.usecase.ShowGeneralLogUsecase

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

@Singleton
class ShowGeneralLogController @Inject()(usecase: ShowGeneralLogUsecase, presenter: ShowGeneralLogPresenter) extends Controller {

  val showGeneralLogForm = Form(
    mapping(
      "tableName" -> text
    )(ShowGeneralLogDto.apply)(ShowGeneralLogDto.unapply)
  )

  implicit val ec = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def showGeneralLog() = Action.async { implicit request =>
    showGeneralLogForm.bindFromRequest().fold(_ => Future.successful(BadRequest("aaa")),
      (dto: ShowGeneralLogDto) => presenter.response(usecase.execute(dto))
    )
  }
}
