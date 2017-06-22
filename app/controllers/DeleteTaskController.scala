package controllers

import javax.inject.{ Inject, Singleton }

import models.Task
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }
import scalikejdbc.AutoSession

/**
  * Created by raru on 2017/06/23.
  */
@Singleton
class DeleteTaskController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def delete(taskId: Long): Action[AnyContent] = Action {
    implicit val session = AutoSession
    if (0 < Task.deleteById(taskId)) {
      Redirect(routes.GetTasksController.index())
    } else {
      InternalServerError(Messages("DeleteTaskError"))
    }
  }
}
