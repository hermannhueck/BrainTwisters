package mytry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

abstract class MainBase {

    @SuppressWarnings("SameParameterValue")
    String readInput(String prompt) {
        try {
            out.print(prompt);
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    IntPair parse(final String line) {
        if (line == null)
            throw new IllegalArgumentException("Input is null");
        String input = line.trim();
        if (input.isEmpty())
            throw new IllegalArgumentException("Input is empty");
        String[] words = input.split("\\s");
        if (words.length < 2)
            throw new IllegalArgumentException("Input with 2 numbers expected");
        int first = Integer.parseInt(words[0]); // possibly throws NumberFormatException
        int second = Integer.parseInt(words[1]); // possibly throws NumberFormatException
        return new IntPair(first, second);
    }

    Integer divide(IntPair pair) {
        return pair.first / pair.second;
    }
}
