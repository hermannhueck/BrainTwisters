package suggestions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SuggestionsTemplate {

    private static int MAX_WORDS = 3;


    private static class Suggestion {
        private final String text;

        private Suggestion(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }

    private static List<Suggestion> makeSuggestions(Iterator<String> streamIterator,
                                                    Set<String> stopWords) {
        return null;
    }


    //Input: {"The", "moon", "is", "not", "a", "planet", "and", "also", "not", "a", "star", ".",
    // "But", ",", "I", "digress", "we", "people", "are", "so", "irrational","."}

    //stop words: {"The", "a", "is"}

    //Output:
    //    moon
    //    moon not
    //    moon not planet
    //    not
    //    not planet
    //    not planet and
    //    planet
    //    planet and
    //    planet and also
    //    and
    //    and also
    //    and also not
    //    also
    //    also not
    //    also not star
    //    not
    //    not star
    //    not star But
    //    star
    //    star But
    //    star But I
    //    But
    //    But I
    //    But I digress
    //    I
    //    I digress
    //    I digress we
    //    digress
    //    digress we
    //    digress we people
    //    we
    //    we people
    //    we people are
    //    people
    //    people are
    //    people are so
    //    are
    //    are so
    //    are so irrational

}
