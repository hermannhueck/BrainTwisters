package anagrams.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
    Further improved anagram solution in Java 8
    This solution does not filter out words which literally occur in the dictionary.
 */
public class AnagramsJava8_1b {

    public static void main(String[] args) {

        String[] dictionary = {"abcde", "uidsfk"};
        String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};

        anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary)).forEach(System.out::println);
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary) {
        return anagrams(wordsToTest, dictionary, false);
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary, boolean isDictionarySorted) {
        return wordsToTest.stream()
                .filter(str -> isAnagram(str, isDictionarySorted ? dictionary : sortedLowercaseWords(dictionary), true))
                .collect(Collectors.toList());
    }

    public static boolean isAnagram(String word, List<String> dictionary) {
        return isAnagram(word, dictionary, false);
    }

    public static boolean isAnagram(String word, List<String> dictionary, boolean isDictionarySorted) {
        String wordSorted = sortString(word).toLowerCase();
        return isDictionarySorted ?
                dictionary.contains(wordSorted) :
                sortedLowercaseWords(dictionary).contains(wordSorted);
    }

    private static List<String> sortedLowercaseWords(List<String> words) {
        return words.stream()
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
