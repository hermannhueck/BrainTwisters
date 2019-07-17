package suggestions;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static java.util.Arrays.asList;

public class J8SuggestionsImproved {

    private static int MAX_WORDS = 13;

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
            "But", ",", "I", "digress", "we", "people", "are", "so", "irrational", "."};

    private static String[] STOPWORDS = {"The", "a", "is"};

    public static void main(String[] args) {

        List<Suggestion> suggestions = makeSuggestions(
                maxWords(args),
                asList(INPUT),
                new HashSet<String>(asList(STOPWORDS)));
        suggestions.forEach(out::println);
    }

    private static int maxWords(String[] args) {
        if (args.length == 0)
            return MAX_WORDS;
        try {
            int maxWords = Integer.parseInt(args[0]);
            return maxWords < 1 ? 1 : maxWords;
        } catch (NumberFormatException e) {
            return MAX_WORDS;
        }
    }

    private static List<Suggestion> makeSuggestions(int maxWords,
                                                    List<String> words,
                                                    Set<String> stopWords) {
        List<String> purged = words
                .stream()
                .filter(e -> !stopWords.contains(e))
                .filter(e -> e.matches("\\w+"))
                .collect(Collectors.toList());
        maxWords = maxWords > purged.size() ? purged.size() : maxWords;
        out.println("----- maxWords = " + maxWords);
        out.println("----- purged.size = " + purged.size());
        out.println("----- purged = " + purged);

        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < purged.size() - maxWords + 1; i++) {
            suggestions.addAll(combinations(i, purged, maxWords));
        }

        return suggestions
                .stream()
                .map(e -> new Suggestion(e))
                .collect(Collectors.toList());
    }

    private static List<String> combinations(int index, List<String> words, int maxWords) {
        List<String> combinations = new ArrayList<>();
        for (int i = 0; i < maxWords; i++) {
            String word = words.get(index + i);
            String newWord = i == 0 ? word : combinations.get(i - 1) + " " + word;
            combinations.add(newWord);
        }
        return combinations;
    }
}
