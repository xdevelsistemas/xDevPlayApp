
import play.api._

import com.google.inject.{Guice, AbstractModule}

import dao.Bootstrap.{startUp,fakeStartUp}


object Global extends GlobalSettings {

  val injector = Guice.createInjector(new AbstractModule {
    protected def configure() {
      //see: https://github.com/typesafehub/play-guice

      // This would be useful in case we inject (for example) configuration settings onto controllers.
      //TODO: bind(classOf[Settings]).to(classOf[DefaultSettings])
    }
  })

  /**
   * Controllers must be resolved through the application context. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = injector.getInstance(controllerClass)

  override def onStart(app: Application) {
    Logger.info("Application started")
    val records:Int = if (app.mode == Mode.Prod) startUp else fakeStartUp
    Logger.info(s"Database initialization succeeded : $records record(s) saved")
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

//FIXME
//  override def onError(request: RequestHeader, ex: Throwable) = {
//    InternalServerError(
//      views.html.errorPage(ex)
//    )
//  }

//FIXME
//  override def onActionNotFound(request: RequestHeader) = {
//    NotFound(
//      views.html.notFoundPage(request.path)
//    )
//  }

//FIXME
//  override def onBadRequest(request: RequestHeader, error: String) = {
//    BadRequest("Bad Request: " + error)
//  }

}
