package suggestions

object SSuggestions extends App {

  private val MAX_WORDS = 10

  case class Suggestion private(text: String) {
    override def toString: String = text
  }

  private val INPUT = Seq("The", "moon", "is", "not", "a", "planet", "and", "also", "not", "a", "star", ".",
    "But", ",", "I", "digress", "we", "people", "are", "so", "irrational", ".")

  private val STOPWORDS = Set("The", "a", "is")

  val maxWords = args.toList.headOption.map(_.toInt).getOrElse(MAX_WORDS).max(1)
  makeSuggestions(maxWords, INPUT, STOPWORDS) foreach println

  private def makeSuggestions(maxWords: Int, input: Seq[String], stopWords: Set[String]) = {

    val purged = input
      .filterNot(stopWords.contains)
      .filter(_.matches("\\w+"))
      .toList
    println(s"----- maxWords = $maxWords")
    println(s"----- purged.length = ${purged.length}")
    println(s"----- purged = $purged")

    suggestions(maxWords.min(purged.length), purged)
  }

  private def suggestions(maxWords: Int, words: List[String]) = suggestions3(maxWords, words)

  private def suggestions1(maxWords: Int, words: List[String]) = {
    // here we use a mutable var   :-(
    // must be improved!! --> see 2nd solution
    var suggestions = List.empty[String]
    for (i <- 0 until words.length - maxWords + 1) {
      val combinations = createCombinations(i until i + maxWords, words)
      suggestions = suggestions ::: combinations
    }

    suggestions.map(Suggestion)
  }

  private def suggestions2(maxWords: Int, words: List[String]): List[Suggestion] = {
    val indexes = (0 until words.length - maxWords + 1).toList
    indexes
      .flatMap { index =>
        createCombinations(index until index + maxWords, words)
      }
      .map(Suggestion)
  }

  private def suggestions3(maxWords: Int, words: List[String]): List[Suggestion] = {
    for {
      index <- (0 until words.length - maxWords + 1).toList
      combination <- createCombinations(index until index + maxWords, words)
      suggestion = Suggestion(combination)
    } yield suggestion
  }

  private def createCombinations(range: IndexedSeq[Int], words: List[String]) =
    range
      .map { i => words(i) }
      .foldLeft(List.empty[String])((acc, word) => {
        acc match {
          case Nil => word :: Nil
          case head :: tail => head + " " + word :: acc
        }
      })
      .reverse
}
