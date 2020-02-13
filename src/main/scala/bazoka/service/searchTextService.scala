package bazoka.service

import bazoka.utils.Constants

import scala.collection.immutable.ListMap


object searchTextService {


  def getFilesOccurencePercentage(inMemoryFiles: Map[String, Map[String, Int]], wordsToSearch: List[String]): ListMap[String, Double] = {

    val FilesOccurence: Map[String, Double] = inMemoryFiles.map { case (key: String, value: Map[String, Int]) =>
      val fileName: String = key
      val fileOccurencePercentage = getFileOccurencePercentage(wordsToSearch.foldLeft(0) { case (acc, w) =>
        if (value.keys.toList.contains(w)) acc + 1 else acc
      }, wordsToSearch.length)

      fileName -> fileOccurencePercentage
    }

    ListMap(FilesOccurence.toSeq.sortWith(_._2 > _._2): _*)
  }

  def getFileOccurencePercentage(wordsFoundInFile: Int, wordsToSearchSize: Int): Double = {
    val occurencePercentage = (wordsFoundInFile * 100.00) / wordsToSearchSize
    occurencePercentage
  }

}
