import bazoka.service.searchTextService
import org.scalatest.{FlatSpec, FunSuite}

class SearchTextServiceTest extends FunSuite {
  val FILE22 = "file2"
  val FILE12 = "file1"
  val WORLD = "world"
  val HELLO = "Hello"

  val helloWordsToSearch = List(HELLO)
  val worldWordsToSearch = List(WORLD)

  val bothWordsToSearch: List[String] = List(HELLO, WORLD)

  val emptyInMemoryFiles: Map[String, Map[String, Int]] = Map.empty[String, Map[String, Int]]

  val emptyFile1: Map[String, Int] = Map.empty[String, Int]
  val emptyFile2: Map[String, Int] = Map.empty[String, Int]

  val emptyContentInMemoryFiles = Map(FILE12 -> emptyFile1, FILE22 -> emptyFile2)


  val file1: Map[String, Int] = Map(HELLO -> 1, WORLD -> 1)
  val file2: Map[String, Int] = Map(HELLO -> 1)
  val inMemoryFiles: Map[String, Map[String, Int]] = Map(FILE12 -> file1, FILE22 -> file2)

  test("test empty In Memory Files") {
    val filesOccurencePercentage1 = searchTextService.getFilesOccurencePercentage(emptyInMemoryFiles, bothWordsToSearch)
    assert(filesOccurencePercentage1.isEmpty)
  }
  test("test empty Content In Memory Files") {
    val filesOccurencePercentage2 = searchTextService.getFilesOccurencePercentage(emptyContentInMemoryFiles, bothWordsToSearch)
    assert(filesOccurencePercentage2.size == 2)

    val values2 = filesOccurencePercentage2.values.toList
    values2.foreach(println(_))
    assert(values2.contains(0.0))
    assert(!values2.contains(100.0))
    assert(!values2.contains(50.0))
  }

  test("test In Memory Files") {
    val filesOccurencePercentage3 = searchTextService.getFilesOccurencePercentage(inMemoryFiles, bothWordsToSearch)
    assert(filesOccurencePercentage3.size == 2)
    val values3 = filesOccurencePercentage3.values.toList
    assert(values3.contains(100.0))
    assert(values3.contains(50.0))
  }

  test("test helloWordsToSearch In Memory Files") {
    val filesOccurencePercentage4 = searchTextService.getFilesOccurencePercentage(inMemoryFiles, helloWordsToSearch)
    assert(filesOccurencePercentage4.size == 2)
    val values4 = filesOccurencePercentage4.values.toList
    assert(values4.contains(100.0))
    assert(!values4.contains(50.0))
    assert(!values4.contains(00.0))
  }

  test("test worldWordsToSearch In Memory Files") {
    val filesOccurencePercentage5 = searchTextService.getFilesOccurencePercentage(inMemoryFiles, worldWordsToSearch)
    assert(filesOccurencePercentage5.size == 2)
    val values5 = filesOccurencePercentage5.values.toList
    assert(values5.contains(100.0))
    assert(values5.contains(00.0))
  }

}
