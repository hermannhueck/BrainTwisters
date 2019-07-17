package anagrams

/*
    Scala port of AnagramsJava8_2b

    This solution filters out words which literally occur in the dictionary; i.e. "abcde" is not an anagram of "abcde".

    This solution has the drawback, that the dictionary must be sorted for each word to test,
    as we remove the word to test from the dictionary first and then we sort the words of the dictionary.
 */
object AnagramsScala_2a extends App {

  implicit val dictionary = Seq("abcde", "uidsfk")
  val wordsToTest = Seq("uierjjfdskfju", "CDABE", "abcde")

  anagrams(wordsToTest).foreach(println)


  def anagrams(words: Seq[String])(implicit dictionary: Seq[String]) =
    words.filter(isAnagram(_)(dictionary))

  def isAnagram(word: String)(implicit dictionary: Seq[String]) =
    dictFor(word, dictionary) contains word.sorted.toLowerCase

  def dictFor(word: String, dictionary: Seq[String]) =
    dictionary.filter(_ != word) map { _.sorted.toLowerCase }
}
