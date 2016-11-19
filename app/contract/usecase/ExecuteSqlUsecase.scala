package contract.usecase

import adapter.dto.ExecuteSqlDto
import contract.{PushPort, Usecase}

import scala.collection.immutable.IndexedSeq

trait ExecuteSqlUsecase extends Usecase with PushPort[ExecuteSqlDto, (IndexedSeq[String], Iterator[IndexedSeq[String]])]
