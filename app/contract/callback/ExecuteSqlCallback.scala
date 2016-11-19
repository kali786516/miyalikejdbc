package contract.callback

import contract.Callback
import domain.{SqlResults, Statistics}

trait ExecuteSqlCallback extends Callback[(SqlResults, Statistics)]
