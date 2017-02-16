package anagrams

import org.scalatest._

class AnagramsSpec extends FlatSpec with Matchers {

  implicit val dictionary = Seq("abcde", "uidsfk")
  val wordsToTest = Seq("uierjjfdskfju", "CDABE", "abcde")


  "AnagramsScala_1" should "return \"CDABE\" and \"abcde\"" in {

    AnagramsScala_1.anagrams(wordsToTest) should be(Seq("CDABE", "abcde"))
  }

  "AnagramsScala_2a" should "return \"CDABE\"" in {

    AnagramsScala_2a.anagrams(wordsToTest) should be(Seq("CDABE"))
  }

  "AnagramsScala_2b" should "return \"CDABE\"" in {

    AnagramsScala_2b.anagrams(wordsToTest) should be(Seq("CDABE"))
  }
}
