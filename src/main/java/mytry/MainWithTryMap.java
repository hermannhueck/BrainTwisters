package mytry;

import mytry.mytry.Try;

import static java.lang.System.err;
import static java.lang.System.out;

@SuppressWarnings("Convert2MethodRef")
public class MainWithTryMap extends MainBase {

    public static void main(String[] args) {

        out.println("=====>> " + MainWithTryMap.class.getSimpleName());

        new MainWithTryMap();

        out.println("<<===== " + MainWithTryMap.class.getSimpleName());
    }

    private MainWithTryMap() {

        Try
                .of("Enter 2 integer numbers to divide: ", prompt -> readInput(prompt))
                .tryMap(input -> parse(input))
                .tryMap(pair -> divide(pair))
                .onComplete(quotient -> out.println("Quotient: " + quotient),
                        throwable -> err.println("Error: " + throwable.toString()));
    }
}
