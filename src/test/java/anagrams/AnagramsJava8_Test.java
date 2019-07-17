package anagrams;

import anagrams.java7.AnagramsJava7_1;
import anagrams.java7.AnagramsJava7_2;
import anagrams.java8.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class AnagramsJava8_Test {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    private static final String[] dictionary = {"abcde", "uidsfk"};
    private static final String[] wordsToTest = {"uierjjfdskfju", "CDABE", "abcde"};


    @Test
    public void testAnagramsJava8_1b() {

        assertFalse( AnagramsJava8_1b.isAnagram(wordsToTest[0], Arrays.asList(dictionary)) );
        assertTrue( AnagramsJava8_1b.isAnagram(wordsToTest[1], Arrays.asList(dictionary)) );
        assertTrue( AnagramsJava8_1b.isAnagram(wordsToTest[2], Arrays.asList(dictionary)) );

        List<String> result = AnagramsJava8_1b.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(2, result.size());
        assertTrue(result.contains("CDABE"));
        assertTrue(result.contains("abcde"));
    }

    @Test
    public void testAnagramsJava8_2a() {

        assertFalse( AnagramsJava8_2a.isAnagram(wordsToTest[0], Arrays.asList(dictionary)) );
        assertTrue( AnagramsJava8_2a.isAnagram(wordsToTest[1], Arrays.asList(dictionary)) );
        assertFalse( AnagramsJava8_2a.isAnagram(wordsToTest[2], Arrays.asList(dictionary)) );

        List<String> result = AnagramsJava8_2a.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(1, result.size());
        assertTrue(result.contains("CDABE"));
    }

    @Test
    public void testAnagramsJava8_2b() {

        assertFalse( AnagramsJava8_2b.isAnagram(wordsToTest[0], Arrays.asList(dictionary)) );
        assertTrue( AnagramsJava8_2b.isAnagram(wordsToTest[1], Arrays.asList(dictionary)) );
        assertFalse( AnagramsJava8_2b.isAnagram(wordsToTest[2], Arrays.asList(dictionary)) );

        List<String> result = AnagramsJava8_2b.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(1, result.size());
        assertTrue(result.contains("CDABE"));
    }

    @Test
    public void testAnagramsJava8_2c() {

        assertFalse( AnagramsJava8_2c.isAnagram(wordsToTest[0], Arrays.asList(dictionary)) );
        assertTrue( AnagramsJava8_2c.isAnagram(wordsToTest[1], Arrays.asList(dictionary)) );
        assertFalse( AnagramsJava8_2c.isAnagram(wordsToTest[2], Arrays.asList(dictionary)) );

        List<String> result = AnagramsJava8_2c.anagrams(Arrays.asList(wordsToTest), Arrays.asList(dictionary));
        assertEquals(1, result.size());
        assertTrue(result.contains("CDABE"));
    }
}
