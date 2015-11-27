package anagrams.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
    Improvement of AnagramsJava8_2a and alernative to AnagramsJava8_2b
    This solution replaces removeSameWord() of the the previous solution
    by a boolean expression in isAnagram().

    This solution filters out words which literally occur in the dictionary; i.e. "abcde" is not an anagram of "abcde".

    This solution has the drawback, that the dictionary must be sorted for each word to test,
    as we remove the word to test from the dictionary first and then we sort the words of the dictionary.
 */
public class AnagramsJava8_2c {

    public static void main(String[] args) {

        String[] dictionary = {"abcde", "uidsfk"};
        String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};

        anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary)).forEach(System.out::println);
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary) {
        return wordsToTest.stream()
                .filter(word -> isAnagram(word, dictionary))
                .collect(Collectors.toList());
    }

    public static boolean isAnagram(String word, List<String> dictionary) {
        String wordSorted = sortString(word).toLowerCase();
        return !dictionary.contains(word) && sortedLowercaseWords(dictionary).contains(wordSorted);
    }

    private static List<String> sortedLowercaseWords(List<String> dictionary) {
        return dictionary.stream()
                .map(s -> sortString(s).toLowerCase())
                .collect(Collectors.toList());
    }

    private static String sortString(String s){
        return s.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
