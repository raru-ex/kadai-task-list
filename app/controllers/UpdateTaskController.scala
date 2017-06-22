package controllers

import java.time.ZonedDateTime
import javax.inject.Inject

import forms.TaskForm
import models.Task
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }
import scalikejdbc.AutoSession

/**
  * Created by raru on 2017/06/23.
  */
class UpdateTaskController @Inject()(val messagesApi: MessagesApi)
    extends Controller
    with I18nSupport
    with TaskControllerSupport {

  def index(taskId: Long): Action[AnyContent] = Action { implicit request =>
    val task = Task.findById(taskId).get
    Ok(views.html.edit(form.fill(TaskForm(task.id, task.content))))
  }

  def update: Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.edit(formWithErrors)), { model =>
          implicit val session = AutoSession
          val result = Task
            .updateById(model.id.get)
            .withAttributes(
              'content   -> model.content,
              'updatedAt -> ZonedDateTime.now
            )

          if (0 < result) {
            Redirect(routes.GetTasksController.index)
          } else {
            InternalServerError(Messages("UpdateTaskError"))
          }
        }
      )
  }
}
