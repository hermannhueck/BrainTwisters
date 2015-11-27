package anagrams;

import anagrams.java7.AnagramsJava7_1;
import anagrams.java7.AnagramsJava7_2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class AnagramsJava7_Test {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    private static final String[] dictionary = {"abcde", "uidsfk"};
    private static final String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};


    @Test
    public void testAnagramsJava7_1() {

        List<String> result = AnagramsJava7_1.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(2, result.size());
        assertTrue(result.contains("CDABE"));
        assertTrue(result.contains("abcde"));
    }

    @Test
    public void testAnagramsJava7_2() {

        List<String> result = AnagramsJava7_2.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(1, result.size());
        assertTrue(result.contains("CDABE"));
    }
}
