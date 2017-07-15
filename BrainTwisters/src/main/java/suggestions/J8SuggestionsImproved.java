package suggestions;

import java.util.*;
import java.util.stream.Collectors;

public class J8SuggestionsImproved {

    private static int MAX_WORDS = 10;

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

    private static String[] INPUT = {"The", "moon", "is", "not", "a", "planet", "and", "also", "not", "a", "star", ".",
            "But", ",", "I", "digress", "we", "people", "are", "so", "irrational","."};
    private static String[] STOPWORDS = {"The", "a", "is"};

    public static void main(String[] args) {

        List<Suggestion> output = makeSuggestions(
                Arrays.asList(INPUT),
                new HashSet(Arrays.asList(STOPWORDS)));

        output.forEach(suggestion -> System.out.println(suggestion.toString()));
    }

    private static List<Suggestion> makeSuggestions(List<String> strings,
                                                    Set<String> stopWords) {
        List<String> purged = strings
                .stream()
                .filter(e -> !stopWords.contains(e))
                .filter(e -> e.matches("\\w+"))
                .collect(Collectors.toList());

        int size = purged.size();
        List<String> l1 = purged.subList(0, size - 2);
        List<String> l2 = purged.subList(1, size - 1);
        List<String> l3 = purged.subList(2, size);

        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < purged.size() - 2; i++ ) {
            suggestions.add(purged.get(i));
            suggestions.add(purged.get(i) + " " + purged.get(i+1));
            suggestions.add(purged.get(i) + " " + purged.get(i+1) + " " + purged.get(i+2));
        }

        return suggestions
                .stream()
                .map(e -> new Suggestion(e))
                .collect(Collectors.toList());
    }
}
