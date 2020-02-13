package bazoka.exceptions

object exceptions {

  sealed trait ReadFileError

  case object MissingPathArg extends ReadFileError

  case class NotDirectory(error: String) extends ReadFileError

  case class FileNotFound(t: Throwable) extends ReadFileError


  case class BadParameters(t: Exception) extends RuntimeException

}
