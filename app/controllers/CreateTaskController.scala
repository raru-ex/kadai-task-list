package controllers

import java.time.ZonedDateTime
import javax.inject.{ Inject, Singleton }

import models.Task
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }
import scalikejdbc.AutoSession

/**
  * Created by raru on 2017/06/22.
  */
@Singleton
class CreateTaskController @Inject()(val messagesApi: MessagesApi)
    extends Controller
    with I18nSupport
    with TaskControllerSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.create(form))
  }

  def create: Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.create(formWithErrors)), { model =>
          implicit val session = AutoSession
          val now              = ZonedDateTime.now
          if (0 < Task.create(Task(None, model.content, now, now))) {
            Redirect(routes.GetTasksController.index)
          } else {
            InternalServerError(Messages("CreateTaskError"))
          }
        }
      )
  }
}
