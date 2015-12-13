package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def rpnPage() = Action {
    Ok(views.html.rpnPage("RPN Evaluation", "Enter your RPN formula to evaluate : "))
  }
}
