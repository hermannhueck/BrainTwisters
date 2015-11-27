package anagrams.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Improved anagram solution in Java 8

    This solution does not filter out words which literally occur in the dictionary.
 */
public class AnagramsJava8_1a {

    public static void main(String[] args) {

        String[] dictionary = {"abcde", "uidsfk"};
        List<String> dictWithSortedWords = sortedWords(dictionary);

        String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};
        List<String> sortedWords = sortedWords(wordsToTest);

        dictWithSortedWords.stream().forEach(dictWord ->
                isAnagram(dictWord, sortedWords)
                        .forEach(System.out::println));
    }

    public static Stream<String> isAnagram(String str, List<String> dictWithSortedWords) {
        return dictWithSortedWords.stream().filter(str::equalsIgnoreCase);
    }

    private static List<String> sortedWords(String[] words) {
        return Arrays.stream(words)
                .map(s -> sortString(s))
                .collect(Collectors.toList());
    }

    private static String sortString(String s){
        return s.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
