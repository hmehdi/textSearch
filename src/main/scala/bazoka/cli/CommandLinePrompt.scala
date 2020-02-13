package bazoka.cli

import java.util.Scanner

import bazoka.models.WordIndexer
import bazoka.service.searchTextService
import bazoka.utils.Constants
import bazoka.utils.Constants._

import util.control.Breaks._

object CommandLinePrompt {


  def execute(dir: String): Unit = {
    executeCli(new Scanner(System.in), dir)
  }

  private def executeCli(input: Scanner, dir: String) {

    // Index all files in indexable Directory
    val inMemoryFiles = WordIndexer.getInMemoryFilesContent(dir)

    System.out.println(PRINTCOUNTFILE.format(inMemoryFiles.keys.size, dir))
    System.out.print(PROMPT)
    breakable {
      while (input.hasNext()) {
        val line = input.nextLine()

        if (line.equals(QUIT)) {
          break
        }

        System.out.println(SEARCHING + line + " ...")
        val wordsToSearch: List[String] = line.trim().split(Constants.SPLIT_TOKENS).toList
        val searchResults: Map[String, Double] = searchTextService.getFilesOccurencePercentage(inMemoryFiles, wordsToSearch)
        printResult(searchResults)
        System.out.print(PROMPT)
      }
    }
  }

  def printResult(searchResules: Map[String, Double]): Unit = {

    val results = searchResules.filter(_._2 > 0.0)
    if (results.isEmpty) {
      System.err.println(NO_MATCHES)
    } else {
      results.foreach(x => System.out.println("%s: %.2f".format(x._1, x._2) + "%"))
    }
  }


}
