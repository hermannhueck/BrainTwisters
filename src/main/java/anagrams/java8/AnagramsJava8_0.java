package anagrams.java8;

import java.util.Arrays;
import java.util.List;

/*
    Original anagram solution in Java 8

    This solution does not filter out words which literally occur in the dictionary.
 */
public class AnagramsJava8_0 {

    public static void main(String[] args) {

        List<String> dictionary = Arrays.asList(new String[]{"abcde", "uidsfk"});
        List<String> wordsToTest = Arrays.asList(new String[]{"uierjjfdskfju", "CDABE", "abcde"});

        dictionary.replaceAll(s -> sortString(s));
        wordsToTest.replaceAll(s -> sortString(s));

        dictionary.stream().forEach(
                dictEntry -> wordsToTest.stream().filter(word -> dictEntry.equalsIgnoreCase(word))
                        .forEach(System.out::println));
    }

    private static String sortString(String s){
        return s.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
