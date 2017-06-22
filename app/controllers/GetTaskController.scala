package controllers

import javax.inject.{ Inject, Singleton }

import models.Task
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }

/**
  * Created by raru on 2017/06/23.
  */
@Singleton
class GetTaskController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index(taskId: Long): Action[AnyContent] = Action { implicit request =>
    val task = Task.findById(taskId).get
    Ok(views.html.show(task))
  }
}
