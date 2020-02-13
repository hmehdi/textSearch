package bazoka.models

import java.io.File

import bazoka.utils.{Constants, FileUtils}

object WordIndexer {

  def getInMemoryFilesContent(file: String): Map[String, Map[String, Int]] = {
    FileUtils.findFiles(file).flatMap(f => getFileContent(f)).toMap
  }

  private def getFileContent(file: File): Map[String, Map[String, Int]] = {
    val lines = FileUtils.readFile(file.getPath)

    getWordsInFile(file.getName, lines)
  }

  private def getWordsInFile(fileName: String, lines: String): Map[String, Map[String, Int]] = {
    //splitting String data with white space and calculating the number of occurrence of each word in the file
    val counts = lines.split(Constants.WORDPATTERN).groupBy(x => normalizeWord(x)).mapValues(x => x.length).toMap
    Map(fileName -> counts)
  }

  private def normalizeWord(word: String): String = {
    word.trim.toLowerCase()
  }
}
