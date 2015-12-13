package controllers

import model._
import play.api.http.Writeable
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class Api extends Controller {

  val result: Int = 0

  def parseTerm(t: String): Token = t.matches("^\\d*$") match {
    case true => new Operand(t.toInt)
    case false => new Operator(t)
  }

  implicit object JobWrites extends Writes[model.Job] {
    def writes(j: Job) = Json.obj(
      "name" -> Json.toJson(j.name),
      "expression" -> Json.toJson(j.expression),
      "result" -> Json.toJson(j.result)
    )
  }

  def computeFormulae = Action { implicit request =>
    val job : JsValue = request.body.asJson.get
    val name: String = (job \ "name").as[String]
    val expression: String = (job \ "expression").as[String]
    val result: Double = (job \ "result").as[Double]
    val terms: Array[Token] = expression.replace(" ","").split("(?<=[-+*/])|").map(parseTerm(_))
    val jobProcessed: Job = new Job(name, terms.mkString(" | "), result.toInt + 1)
    Ok(Json.toJson(jobProcessed))
  }

}