package controllers

import forms.TaskForm
import models.Task
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.Controller

/**
  * Created by raru on 2017/06/23.
  */
trait TaskControllerSupport { this: Controller =>

  protected val form = Form(
    mapping(
      "id"      -> optional(longNumber),
      "content" -> nonEmptyText(maxLength = 255)
    )(TaskForm.apply)(TaskForm.unapply)
  )

}
