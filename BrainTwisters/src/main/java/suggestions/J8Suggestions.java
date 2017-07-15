package suggestions;

import java.util.*;
import java.util.stream.Collectors;

public class J8Suggestions {

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

    private static String[] INPUT = {"The", "moon", "is", "not", "a", "planet", "and", "also", "not", "a", "star", ".",
            "But", ",", "I", "digress", "we", "people", "are", "so", "irrational","."};

    private static String[] STOPWORDS = {"The", "a", "is"};

    public static void main(String[] args) {

        List<Suggestion> output = makeSuggestions(
                Arrays.asList(INPUT).iterator(),
                new HashSet(Arrays.asList(STOPWORDS)));

        output.forEach(suggestion -> System.out.println(suggestion.toString()));
    }

    private static List<Suggestion> makeSuggestions(Iterator<String> streamIterator,
                                                    Set<String> stopWords) {
        List<String> list = new ArrayList<>();
        streamIterator.forEachRemaining(list::add);
        System.out.println("------ list.size() = " + list.size());

        List<String> purged = list
                .stream()
                .filter(e -> !stopWords.contains(e))
                .filter(e -> e.matches("\\w+"))
                .collect(Collectors.toList());

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
