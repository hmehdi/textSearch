package bazoka.basicSearch

import bazoka.cli.CommandLinePrompt
import bazoka.models.WordIndexer
import bazoka.service.searchTextService

object TextSearcher extends App {
  if (args.length == 0)
    throw new IllegalArgumentException("Documents directory must be passed as parameter")

  else
    println(s"Directory Index is : ${args(0)}")

  val dir = args(0)
  CommandLinePrompt.execute(dir)


}

