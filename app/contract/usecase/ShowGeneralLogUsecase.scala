package contract.usecase

import adapter.dto.ShowGeneralLogDto
import contract.{PushPort, Usecase}

import scala.collection.immutable.IndexedSeq

trait ShowGeneralLogUsecase extends Usecase with PushPort[ShowGeneralLogDto, (IndexedSeq[String], Iterator[IndexedSeq[String]])]
