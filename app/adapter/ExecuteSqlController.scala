package adapter

import javax.inject.{Inject, Singleton}

import adapter.dto.ExecuteSqlDto
import contract.usecase.ExecuteSqlUsecase

import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._

import scala.concurrent.Future

@Singleton
class ExecuteSqlController @Inject() (usecase: ExecuteSqlUsecase, presenter: ExecuteSqlPresenter) extends Controller {

  val executeSqlForm = Form(
    mapping(
      "sql" -> text
    )(ExecuteSqlDto.apply)(ExecuteSqlDto.unapply)
  )

  implicit val ec = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def executeSql() =  Action.async { implicit request =>
    executeSqlForm.bindFromRequest().fold(_ => Future.successful(BadRequest("aaa")),
      (dto: ExecuteSqlDto) => presenter.response(usecase.execute(dto))
    )
  }
}
