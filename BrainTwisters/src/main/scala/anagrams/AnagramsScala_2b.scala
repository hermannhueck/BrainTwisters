package anagrams

/*
    Scala port of AnagramsJava8_2b (a little bit different from AnagramsScala_2a)

    This solution filters out words which literally occur in the dictionary; i.e. "abcde" is not an anagram of "abcde".

    This solution has the drawback, that the dictionary must be sorted for each word to test,
    as we remove the word to test from the dictionary first and then we sort the words of the dictionary.
 */
object AnagramsScala_2b extends App {

  implicit val dictionary = Seq("abcde", "uidsfk")
  val wordsToTest = Seq("uierjjfdskfju", "CDABE", "abcde")

  anagrams(wordsToTest).foreach(println)


  private def anagrams(words: Seq[String] /*, isDictionarySorted: Boolean = false */)(implicit dictionary: Seq[String]) =
    words.filter(isAnagram(_)(dictionary))

  private def isAnagram(word: String)(implicit dictionary: Seq[String]) =
    !dictionary.contains(word) && dictionary.map(_.sorted.toLowerCase).contains(word.sorted.toLowerCase)
}
