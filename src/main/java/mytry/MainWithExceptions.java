package mytry;

import static java.lang.System.err;
import static java.lang.System.out;

public class MainWithExceptions extends MainBase {

    public static void main(String[] args) {

        out.println("=====>> " + MainWithExceptions.class.getSimpleName());

        new MainWithExceptions();

        out.println("<<===== " + MainWithExceptions.class.getSimpleName());
    }

    private MainWithExceptions() {

        try {
            String input = readInput("Enter 2 integer numbers to divide: ");
            IntPair numbers = parse(input);
            Integer quotient = divide(numbers);
            out.println(numbers.first + " / " + numbers.second + " = " + quotient);
        } catch (Throwable t) {
            err.println("Error: " + t.toString());
            // t.printStackTrace();
        }
    }
}
