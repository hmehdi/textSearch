package bazoka.utils

import java.io.File

import scala.io.Source

object FileUtils {

  def findFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(f => f.isFile).toList
    } else {
      List[File]()
    }
  }

  def readFile(filename: String): String = {
    val source = scala.io.Source.fromFile(filename)
    val lines = try source.mkString finally source.close()
    lines
  }

}
