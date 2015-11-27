package anagrams.java7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Back port of AnagramsJava8_2a to Java 7

    This solution filters out words which literally occur in the dictionary; i.e. "abcde" is not an anagram of "abcde".

    This solution has the drawback, that the dictionary must be sorted for each word to test,
    as we remove the word to test from the dictionary first and then we sort the words of the dictionary.
 */
public class AnagramsJava7_2 {

    public static void main(String[] args) {

        String[] dictionary = {"abcde", "uidsfk"};
        String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};

        for (String anagram : anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary))) {
            System.out.println(anagram);
        }
    }

    public static List<String> anagrams(List<String> wordsToTest, List<String> dictionary) {
        List<String> newList = new ArrayList<>();
        for (String word : wordsToTest) {
            List<String> newDict = removeSameWord(word, dictionary);
            if (isAnagram(word, sortedLowercaseWords(newDict))) {
                newList.add(word);
            }
        }
        return newList;
    }

    public static boolean isAnagram(String word, List<String> dictionary) {
        String wordSorted = sortString(word).toLowerCase();
        return sortedLowercaseWords(dictionary).contains(wordSorted);
    }

    private static List<String> removeSameWord(String word, List<String> dictionary) {
        if (!dictionary.contains(word)) {
            return dictionary;
        } else {
            List<String> newDict = new ArrayList<>();
            for (String entry : dictionary) {
                if (!entry.equals(word)) {
                    newDict.add(entry);
                }
            }
            return newDict;
        }
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
