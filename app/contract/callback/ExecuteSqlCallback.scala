package contract.callback

import contract.Callback

import scala.collection.immutable.IndexedSeq

trait ExecuteSqlCallback extends Callback[(IndexedSeq[String], Iterator[IndexedSeq[String]])]
