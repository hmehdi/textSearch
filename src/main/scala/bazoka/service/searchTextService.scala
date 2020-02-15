package bazoka.service

import bazoka.utils.Constants

import scala.collection.immutable.ListMap


object searchTextService {

  def getFilesOccurencePercentage(inMemoryFiles: Map[String, Map[String, Int]], wordsToSearch: List[String]): ListMap[String, Double] = {

    val FilesOccurence: Map[String, Double] = inMemoryFiles.map { case (key: String, value: Map[String, Int]) =>
      val countWords = wordsToSearch.map(w => value.get(w)).map {
        case Some(v) => 1
        case None => 0
      }.sum
       key -> getFileOccurencePercentage(countWords,wordsToSearch.length)
    }

    ListMap(FilesOccurence.toSeq.sortWith(_._2 > _._2): _*)
  }

  def getFileOccurencePercentage(wordsFoundInFile: Int, wordsToSearchSize: Int): Double = {
    val occurencePercentage = (wordsFoundInFile * 100.00) / wordsToSearchSize.toDouble
    occurencePercentage
  }

}
