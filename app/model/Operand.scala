package model

/**
  * Created by Hervé Darritchon on 11/12/2015.
  *
  */
case class Operand(value: Int) extends Token {

  override def toString() = this.value.toString

}
