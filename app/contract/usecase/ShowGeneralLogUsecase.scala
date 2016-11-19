package contract.usecase

import adapter.dto.ShowGeneralLogDto
import contract.{PushPort, Usecase}
import domain.SqlResults

trait ShowGeneralLogUsecase extends Usecase with PushPort[ShowGeneralLogDto, SqlResults]
