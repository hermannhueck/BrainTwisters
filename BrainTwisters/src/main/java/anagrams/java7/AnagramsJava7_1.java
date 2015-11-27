package anagrams.java7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Back port of AnagramsJava8_1b to Java 7

    This solution does not filter out words which literally occur in the dictionary.
 */
public class AnagramsJava7_1 {

    public static void main(String[] args) {

        String[] dictionary = {"abcde", "uidsfk"};
        String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};

        for (String anagram : anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary))) {
            System.out.println(anagram);
        }
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary) {
        return anagrams(wordsToTest, dictionary, false);
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary, boolean isDictionarySorted) {
        List<String> newList = new ArrayList<>();
        for (String word : wordsToTest) {
            if (isAnagram(word, sortedLowercaseWords(dictionary), true)) {
                newList.add(word);
            }
        }
        return newList;
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
        List<String> newList = new ArrayList<>();
        for (String word : words) {
                newList.add(sortString(word).toLowerCase());
        }
        return newList;
    }

    private static String sortString(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
