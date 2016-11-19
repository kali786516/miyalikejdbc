package contract.usecase

import adapter.dto.ConnectDBDto
import contract.{PushPort, Usecase}

trait ConnectDBUsecase extends Usecase with PushPort[ConnectDBDto, Unit]
