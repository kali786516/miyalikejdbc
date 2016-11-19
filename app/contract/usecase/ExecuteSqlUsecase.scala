package contract.usecase

import adapter.dto.ExecuteSqlDto
import contract.{PushPort, Usecase}
import domain.{SqlResults, Statistics}

trait ExecuteSqlUsecase extends Usecase with PushPort[ExecuteSqlDto, (SqlResults, Statistics)]
