import java.sql.Connection
import javax.inject.Inject

import domain.{DBAccessWorker, DBConnection}
import play.api._
import play.api.cache.CacheApi

class Global @Inject()(cache: CacheApi) extends GlobalSettings {

  override def onStop(app: Application) {
    Logger.info("Application shutdown...!!!!!!!!!!!!!!!!")
    val con = cache.get[Connection](DBConnection.CACHE_KEY).get
    con.close
  }

}
