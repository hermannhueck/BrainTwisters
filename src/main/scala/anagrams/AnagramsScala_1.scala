package anagrams

/*
    Scala port of AnagramsJava8_1b
    This solution does not filter out words which literally occur in the dictionary.
 */
object AnagramsScala_1 extends App {

  implicit val dictionary = Seq("abcde", "uidsfk")
  val wordsToTest = Seq("uierjjfdskfju", "CDABE", "abcde")

  anagrams(wordsToTest).foreach(println)


  def anagrams(words: Seq[String], isDictionarySorted: Boolean = false)(implicit dictionary: Seq[String]) = {
    val dict = getDict(dictionary, isDictionarySorted)
    words.filter(isAnagram(_, dict, isDictionarySorted = true))
  }

  def isAnagram(word: String, dictionary: Seq[String], isDictionarySorted: Boolean = false) =
    getDict(dictionary, isDictionarySorted) contains word.sorted.toLowerCase

  def getDict(dictionary: Seq[String], isDictSorted: Boolean) =
    if (isDictSorted) dictionary else dictionary map { _.sorted.toLowerCase }
}
