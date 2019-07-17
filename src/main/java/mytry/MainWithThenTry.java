package mytry;

import java.util.function.Consumer;

import static java.lang.System.err;
import static java.lang.System.out;
import static mytry.mytry.Try.startTry;

@SuppressWarnings("Convert2MethodRef")
public class MainWithThenTry extends MainBase {

    public static void main(String[] args) {

        out.println("=====>> " + MainWithThenTry.class.getSimpleName());

        new MainWithThenTry();

        out.println("<<===== " + MainWithThenTry.class.getSimpleName());
    }

    private MainWithThenTry() {

        String prompt = "Enter 2 integer numbers to divide: ";
        Consumer<Integer> handleSuccess = quotient -> out.println("Quotient: " + quotient);
        Consumer<Throwable> handleFailure = throwable -> err.println("Error: " + throwable.toString());

        startTry(prompt, p -> readInput(p))
                .thenTry(input -> parse(input))
                .thenTry(pair -> divide(pair))
                .onComplete(handleSuccess, handleFailure);
    }
}
